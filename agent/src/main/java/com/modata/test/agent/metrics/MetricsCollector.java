package com.modata.test.agent.metrics;

import com.modata.test.agent.agency.TaskDto;

import java.util.List;

public interface MetricsCollector {

    List<TaskDto> getTasks();

}
