package com.modata.test.agent.metrics;

import com.modata.test.agent.agency.TaskDto;

import java.util.Collections;
import java.util.List;

public class UnixMetricsCollector implements MetricsCollector {

    @Override
    public List<TaskDto> getTasks() {
        return Collections.emptyList();
    }

}
