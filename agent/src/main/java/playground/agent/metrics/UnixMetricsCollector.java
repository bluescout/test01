package playground.agent.metrics;

import playground.api.TaskDto;

import java.util.Collections;
import java.util.List;

@Deprecated
/**
 * Deprecated, use JProcessesMetricsCollector instead
 * */
public class UnixMetricsCollector implements MetricsCollector {

    @Override
    public List<TaskDto> getTasks() {
        return Collections.emptyList();
    }

}
