package playground.agent.metrics;

import playground.api.TaskDto;

import java.util.List;

public interface MetricsCollector {

    List<TaskDto> getTasks();

}
