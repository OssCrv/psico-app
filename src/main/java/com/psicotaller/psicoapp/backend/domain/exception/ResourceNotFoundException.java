package com.psicotaller.psicoapp.backend.domain.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceType, String keyName, String keyValue) {
        super(resourceType + " with " + keyName + "[" + keyValue + "] not found in the system!");
    }
}
