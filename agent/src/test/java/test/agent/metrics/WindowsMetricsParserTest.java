package test.agent.metrics;

import org.junit.Test;
import test.api.TaskDto;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WindowsMetricsParserTest {

    @Test
    public void should_parse_sample_data() {
        TaskDto task = TaskDto.builder()
                .name("tasklist.exe")
                .pid(15320)
                .memory(9404L * 1024)
                // TODO: get and parse CPU load
                .cpuTime("0:00:01")
                .build();

        List<TaskDto> result = WindowsMetricsParser.parse(loadResourceToStrings("tasklist-output.txt"));

        assertEquals(6, result.size());
        assertEquals(task, result.get(result.size() - 1));
    }

    private List<String> loadResourceToStrings(String relativePathAndName) {
        URL resource = ClassLoader.getSystemClassLoader().getResource(relativePathAndName);
        if (Objects.isNull(resource)) {
            fail(format("Resource not found, name [%s]", relativePathAndName));
        }
        try {
            return Files.readAllLines(Paths.get(resource.toURI()));
        } catch (Exception e) {
            fail(format("Resource load failed, name [%s], error message [%s]", relativePathAndName, e.getMessage()));
        }
        return Collections.emptyList();
    }

}