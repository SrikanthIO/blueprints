package com.blueprings.parallelprocessing;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public interface ParallelTask {

	public Map<String,Object> execute();
	
}
