package test.agent.metrics;

import lombok.extern.slf4j.Slf4j;
import test.api.TaskDto;

import java.util.ArrayList;
import java.util.List;

import static test.agent.metrics.WindowsMetricsCollector.OS_COMMAND;

@Slf4j
final class WindowsMetricsParser {
    private WindowsMetricsParser() {}

    private static final int K = 1024;

    static List<TaskDto> parse(List<String> outLines) {
        outLines.subList(0, 1).clear();

        List<TaskDto> tasks = new ArrayList<>(outLines.size());
        for (String line : outLines) {
            try {
                String[] columns = line.split("(^\"|\",\"|\"$)");
                tasks.add(TaskDto.builder()
                        .name(columns[1])
                        .pid(Integer.parseInt(columns[2]))
                        .memory(Long.parseLong(columns[5].replaceAll("[,. K]", "")) * K)
                        .cpuTime(columns[8])
                        .build()
                );
            }
            catch (IndexOutOfBoundsException | NumberFormatException e) {
                String message = String.format("Unexpected [%s] output format.", OS_COMMAND);
                log.error(message);
                throw new IllegalArgumentException(message, e);
            }
        }
        return tasks;
    }

}
