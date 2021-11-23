package com.corvendallas.calcservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Interface implementation {@link CalcServiceInterface}
 */
@Service
public class CalcServiceImpl implements CalcServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(CalcServiceImpl.class);

    /**
     * Perform the task unless not allowed
     * @param data1
     * @param data2
     * @param task English(add, substract) Spanish (suma,resta)
     * @return result or exception thrown
     * @throws RuntimeException
     */

    @Override
    public double performTask(double data1, double data2, String task) {
        log.info("performTask START");
        log.debug("Task result {} for : {} {}", task, data1, data2);

        switch (task) {
            case "add":case "sumar":
                return data1+data2;
            case "substract":case "restar":
                return data1-data2;
            default:
                log.error("Task not allowed: {}", task);
                String errorMessage = "Task not allowed: " + task;
                throw new TaskNotAllowedException(errorMessage, new Throwable(errorMessage));
        }
    }


}
