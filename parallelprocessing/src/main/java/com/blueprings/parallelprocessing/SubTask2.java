package com.blueprings.parallelprocessing;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubTask2 implements ParallelTask {
	public static final Logger LOGGER = LoggerFactory.getLogger(SubTask2.class);
	private String id;
	private Map<String, Object> context;

	@Override
	public Map<String, Object> execute() {
		LOGGER.info("***********In Subtask2[" + id + "]************");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// do nothing;
		}
		// execute backend code
		Map<String, Object> data = new HashMap<String, Object>();
		LOGGER.info("***********Completed Subtask2[" + id + "]******");
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getContext() {
		return context;
	}

	public void setContext(Map<String, Object> context) {
		this.context = context;
	}

}
