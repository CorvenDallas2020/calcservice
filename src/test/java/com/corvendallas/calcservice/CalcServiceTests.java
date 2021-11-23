package com.corvendallas.calcservice;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import com.corvendallas.calcservice.services.CalcServiceImpl;
import com.corvendallas.calcservice.services.TaskNotAllowedException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = CalcserviceApplication.class
    )
public class CalcServiceTests {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    CalcServiceImpl calcservice;

    /**
     * GET localhost:/restful/calculator with parameters
     * @param data1
     * @param data2
     * @param task
     * @return
     * @throws URISyntaxException
     */
    private ResponseEntity<Double> performTask(String data1, String data2, String task) 
        throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort +
                "/restful/calculator?data1=" + data1 +
                "&data2=" + data2 + "&task=" + task;
        URI uri = new URI(baseUrl);
        ResponseEntity<Double> result = restTemplate.getForEntity(uri, Double.class);
        return result;
    }

    @Test
    public void testAddOk_EN() throws URISyntaxException {
        ResponseEntity<Double> result = performTask("5", "10", "add");
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(15.0d, result.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testSubstractOk_EN() throws URISyntaxException {

        ResponseEntity<Double> result = performTask("5", "10", "substract");
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(-5.0d, result.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testAddOk_ES() throws URISyntaxException {

        ResponseEntity<Double> result = performTask("5", "10", "sumar");
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(15.0d, result.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testSubstractOk_ES() throws URISyntaxException {

        ResponseEntity<Double> result = performTask("5", "10", "restar");
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(-5.0d, result.getBody().doubleValue(), 0.001d);
    }

    @Test
    public void testAddKO() throws URISyntaxException {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.performTask("a", "10", "add");});
        
        String expectedMessage = "Bad Request";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSubstractKO() throws URISyntaxException {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.performTask("5", "b", "restar");});
        
        String expectedMessage = "Bad Request";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testTaskNotAllowed() throws URISyntaxException {
        Exception exception = assertThrows(TaskNotAllowedException.class, () -> {
            calcservice.performTask(5, 10, "multiplicar");});
        
        String expectedMessage = "Task not allowed: multiplica";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        
    }

}
