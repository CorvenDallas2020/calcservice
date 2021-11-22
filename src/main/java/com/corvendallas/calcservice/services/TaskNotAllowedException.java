package com.corvendallas.calcservice.services;

public class TaskNotAllowedException 
  extends RuntimeException {
    public TaskNotAllowedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
