package test.agent.metrics;

import lombok.extern.slf4j.Slf4j;
import test.api.TaskDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class WindowsMetricsCollector implements MetricsCollector {

    private static final String OS_COMMAND = "tasklist";
    private static final int K = 1024;
    private static final String FAIL_MESSAGE = "Failed to get Windows tasks list";

    @Override
    public List<TaskDto> getTasks() {
        // TODO: use Java 9's ProcessHandle
        try {

            return parseTaskListOutput(execTaskList());

        } catch (InterruptedException | IOException e) {
            log.error(FAIL_MESSAGE, e);
            throw new RuntimeException(FAIL_MESSAGE, e);
        }
    }

    private List<TaskDto> parseTaskListOutput(List<String> outLines) {
        outLines.subList(0, 3).clear();

        List<TaskDto> tasks = new ArrayList<>(outLines.size());
        Pattern p = Pattern.compile("(.*) {2,}(\\d+) +(\\S+) +(\\d+) +(\\d+[,.]{0,1}\\d*[,.]{0,1}\\d*)");

        for (String s : outLines) {
            Matcher m = p.matcher(s);
            if (m.find() && m.groupCount() == 5) {
                tasks.add(TaskDto.builder()
                        .name(m.group(1).trim())
                        .pid(Integer.parseInt(m.group(2)))
                        .memory(Integer.parseInt(m.group(5).replaceAll("[,.]", "")) * K)
                        .build()
                );
            } else {
                log.error("Unexpected [{}] output format.", OS_COMMAND);
                throw new RuntimeException(FAIL_MESSAGE);
            }
        }
        return tasks;
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
