package com.corvendallas.calcservice.controllers;

import com.corvendallas.calcservice.services.CalcServiceInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restful")
public class CalcController {
    
	@Autowired
    private CalcServiceInterface calcService;

	private static final Logger log = LoggerFactory.getLogger(CalcController.class);

    @GetMapping("/calculator")
	public ResponseEntity<Double> calculator(
		@RequestParam(value = "data1") double data1,
		@RequestParam(value = "data2") double data2,
		@RequestParam(value = "task") String task) {
			log.info("calculator START");
			double result = this.calcService.performTask(data1, data2, task);
			log.info("calculator END");
			return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
