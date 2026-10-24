package com.example.pos.service;

import com.example.pos.dto.OrderRequest;
import com.example.pos.dto.OrderResponse;
import com.example.pos.entity.Order;
import com.example.pos.entity.OrderItem;
import com.example.pos.entity.Product;
import com.example.pos.entity.LoyaltyCustomer;
import com.example.pos.repository.OrderRepository;
import com.example.pos.repository.OrderItemRepository;
import com.example.pos.repository.ProductRepository;
import com.example.pos.repository.LoyaltyCustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final LoyaltyCustomerRepository customerRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            LoyaltyCustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {

        LoyaltyCustomer customer = customerRepository
                .findById(request.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("Order items are required");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderStatus(
                com.example.pos.entity.TestStatus.COMPLETED);
        order.setTotalAmount(BigDecimal.ZERO);

        order = orderRepository.save(order);

        BigDecimal total = BigDecimal.ZERO;

        for (OrderRequest.OrderItemRequest itemRequest : request.getItems()) {

            if (itemRequest.getQuantity() == null
                    || itemRequest.getQuantity() <= 0) {
                throw new RuntimeException("Quantity must be positive");
            }

            Product product = productRepository
                    .findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Product not found"));

            if (!product.isActive()) {
                throw new RuntimeException("Product is inactive");
            }

            if (product.getAvailableQuantity()
                    < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock");
            }

            BigDecimal price = product.getUnitPrice();
            BigDecimal subtotal = price.multiply(
                    BigDecimal.valueOf(itemRequest.getQuantity()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(price);
            orderItem.setSubTotal(subtotal);

            total = total.add(subtotal);

            product.setAvailableQuantity(
                    product.getAvailableQuantity()
                            - itemRequest.getQuantity());

            productRepository.save(product);

        }

        order.setTotalAmount(total);
        orderRepository.save(order);

        // Example: 1 loyalty coin for each $100 spent
        int coins = total.divideToIntegralValue(
                BigDecimal.valueOf(100)).intValue();

        order.setLoyaltyCoinsEarned(coins);
        customer.setLoyaltyCoins(
                (customer.getLoyaltyCoins() == null
                        ? 0 : customer.getLoyaltyCoins()) + coins);

        customerRepository.save(customer);

        Order savedOrder = orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setTotalAmount(savedOrder.getTotalAmount());
        response.setLoyaltyCoinsEarned(
                savedOrder.getLoyaltyCoinsEarned());
        response.setOrderStatus(
                savedOrder.getOrderStatus().name());

        return response;
    }
}