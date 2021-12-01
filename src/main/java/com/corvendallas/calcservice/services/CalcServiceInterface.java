package com.corvendallas.calcservice.services;

public interface CalcServiceInterface {

    /**
     * Method to perform task with data values
     * @param data1 First value
     * @param data2 Second value
     * @param task Task to perform (add/suma, substract/resta)
     * @return operation result
     */
    double performTask(double data1, double data2, String task);
}
