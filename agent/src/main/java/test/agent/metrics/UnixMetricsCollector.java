package test.agent.metrics;

import test.api.TaskDto;

import java.util.Collections;
import java.util.List;

public class UnixMetricsCollector implements MetricsCollector {

    @Override
    public List<TaskDto> getTasks() {
        return Collections.emptyList();
    }

}
