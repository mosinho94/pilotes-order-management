package com.mmosa.pilotesordermanagement.exception.client;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long clientId) {
        super("Client with ID " + clientId + " not found");
    }
}
