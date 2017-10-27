package com.distributedpipeline.persistence.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.distributedpipeline.persistence.domain.*;

import org.springframework.kafka.core.KafkaTemplate;
@Service
public class PersistenceProducer {
	@Autowired
	private KafkaTemplate<String, Report> kafkaTemplate;
	 
	public void sendMessage(Report report) {
	    kafkaTemplate.send("testrun5", report);
	   
	}
}
