package com.example.pos.constant;

/** Centralized application response and error messages. */
public final class MessageConstants {
    private MessageConstants() {
    }

    public static final String APPLICATION_NAME = "POS Examination Starter";
    public static final String APPLICATION_RUNNING = "The Spring Boot application is running.";
    public static final String TESTS_RETRIEVED_SUCCESSFULLY = "Tests retrieved successfully";
    public static final String TESTS_SEARCHED_SUCCESSFULLY = "Tests searched successfully";
    public static final String TEST_RETRIEVED_SUCCESSFULLY = "Test retrieved successfully";
    public static final String TEST_CREATED_SUCCESSFULLY = "Test created successfully";
    public static final String TEST_UPDATED_SUCCESSFULLY = "Test updated successfully";
    public static final String TEST_DELETED_SUCCESSFULLY = "Test deleted successfully";
    public static final String TEST_NOT_FOUND = "Test not found";
    public static final String UNABLE_TO_RETRIEVE_TESTS = "Unable to retrieve tests";
    public static final String UNABLE_TO_RETRIEVE_TEST = "Unable to retrieve test";
    public static final String UNABLE_TO_CREATE_TEST = "Unable to create test";
    public static final String UNABLE_TO_UPDATE_TEST = "Unable to update test";
    public static final String UNABLE_TO_DELETE_TEST = "Unable to delete test";
    public static final String UNEXPECTED_ERROR = "An unexpected error occurred";
    public static final String REQUEST_VALIDATION_FAILED = "Request validation failed";
    public static final String ENDPOINT_NOT_FOUND = "Requested endpoint was not found";
}
