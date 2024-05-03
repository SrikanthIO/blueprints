package com.blueprings.parallelprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ParallelProcessor {

	public static final Logger LOGGER = LoggerFactory.getLogger(ParallelProcessor.class);
	@Autowired
	@Qualifier("parallelTaskExecutor")
	private Executor executor;


	public List<Map<String, Object>> process(List<ParallelTask> tasks) throws Exception {
		Iterator<ParallelTask> ite = tasks.iterator();
		List<CompletableFuture<Map<String, Object>>> futuresList = new ArrayList<CompletableFuture<Map<String, Object>>>();
		// create a completable futrue per task
		while (ite.hasNext()) {
			ParallelTask t = ite.next();
			CompletableFuture<Map<String, Object>> future = CompletableFuture.supplyAsync(() -> t.execute(),
					executor);
			futuresList.add(future);
		}
		CompletableFuture<Void> allFutures = CompletableFuture
				.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));

		CompletableFuture<List<Map<String, Object>>> allCompletableFuture = allFutures.thenApply(future -> {
			return futuresList.stream().map(completableFuture -> completableFuture.join()).collect(Collectors.toList());
		});
		CompletableFuture<List<Map<String, Object>>> completableFuture = allCompletableFuture.toCompletableFuture();
		List<Map<String, Object>> finalList = (List<Map<String, Object>>) completableFuture.get();
		return finalList;
	}
}
