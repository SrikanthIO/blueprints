package com.blueprings.parallelprocessing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParallelProcessController {

	@Autowired
	ParallelProcessor service;

	@GetMapping("/pp/invoke")
	public String invokeParallelProcessTasks() throws Exception {

		SubTask1 s1 = new SubTask1();
		SubTask2 s2 = new SubTask2();
		SubTask3 s3 = new SubTask3();

		s1.setId("subtask1-1");
		s2.setId("subtask2-1");
		s3.setId("subtask3-1");
		List<ParallelTask> tasks = new ArrayList<ParallelTask>();
		tasks.add(s1);
		tasks.add(s2);
		tasks.add(s3);
		for (int i = 2; i < 10; i++) {
			s1 = new SubTask1();
			s2 = new SubTask2();
			s3 = new SubTask3();
			s1.setId("subtask1-"+i);
			s2.setId("subtask2-"+i);
			s3.setId("subtask3-"+i);
			tasks.add(s1);
			tasks.add(s2);
			tasks.add(s3);
		}
		service.process(tasks);
		return "Ok";

	}

}
