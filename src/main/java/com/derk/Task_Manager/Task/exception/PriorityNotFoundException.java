package com.derk.Task_Manager.Task.exception;

public class PriorityNotFoundException extends RuntimeException {
    public PriorityNotFoundException(Integer id) {
        super("No existe una prioridad con id: " + id);
    }
}
