package com.derk.Task_Manager.Task.exception;

public class StatusNotFoundException extends RuntimeException {
    public StatusNotFoundException(Integer id) {
        super("No existe un estado con id: " + id);
    }
}
