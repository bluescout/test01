package test.agent.metrics;

import test.api.TaskDto;

import java.util.List;

public interface MetricsCollector {

    List<TaskDto> getTasks();

}
