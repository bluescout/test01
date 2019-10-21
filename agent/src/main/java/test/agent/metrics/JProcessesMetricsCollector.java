package test.agent.metrics;

import lombok.extern.slf4j.Slf4j;
import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;
import test.api.TaskDto;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JProcessesMetricsCollector implements MetricsCollector {

    @Override
    public List<TaskDto> getTasks() {
        return JProcesses.getProcessList()
                .stream()
                .map(this::toTaskDto)
                .collect(Collectors.toList());
    }

    private TaskDto toTaskDto(ProcessInfo pi) {
        return TaskDto.builder()
                .pid(pi.getPid())
                .name(pi.getName())
                .cpuTime(pi.getTime())
                .memory(pi.getPhysicalMemory())
                .build();
    }
}
