package com.derk.Task_Manager.Task.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(UUID uuid) {
        super("No existe una tarea con el siguiente UUID: " + uuid);
    }
}
