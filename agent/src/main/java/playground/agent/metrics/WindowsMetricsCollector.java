package playground.agent.metrics;

import lombok.extern.slf4j.Slf4j;
import playground.api.TaskDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Deprecated
/**
 * Deprecated, use JProcessesMetricsCollector instead
 * */
public class WindowsMetricsCollector implements MetricsCollector {

    static final String OS_COMMAND = "tasklist /FO CSV /V";
    private static final String FAIL_MESSAGE = "Failed to get Windows tasks list";

    @Override
    public List<TaskDto> getTasks() {
        // Tried to use Java 9's ProcessHandle, and it does not provide memory info
        try {

            return WindowsMetricsParser.parse(execTaskList());

        } catch (InterruptedException | IOException | IllegalArgumentException e) {
            log.error(FAIL_MESSAGE, e);
            throw new RuntimeException(FAIL_MESSAGE, e);
        }
    }

    private List<String> execTaskList() throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec(OS_COMMAND);

        List<String> resultLines = new BufferedReader(new InputStreamReader(proc.getInputStream())).lines().collect(Collectors.toList());

        int exitCode = proc.waitFor();
        if (exitCode != 0) {
            log.error(FAIL_MESSAGE + ": [{}] returned [{}] exit code.", OS_COMMAND, exitCode);
            throw new RuntimeException(FAIL_MESSAGE);
        }

        return resultLines;
    }

}
