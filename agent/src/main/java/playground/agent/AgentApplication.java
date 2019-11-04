package playground.agent;

import lombok.extern.slf4j.Slf4j;
import playground.agent.api.AgencyApiFactory;
import playground.agent.helpers.AppProperties;
import playground.agent.helpers.Hostname;
import playground.agent.metrics.JProcessesMetricsCollector;
import playground.agent.metrics.MetricsCollector;
import playground.api.AgencyApi;
import playground.api.MetricsDto;
import playground.api.SettingsDto;
import playground.api.TaskDto;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;
import static playground.agent.helpers.MappingHelper.parseLongSilently;

@Slf4j
public class AgentApplication {

    public static void main(String[] args) throws InterruptedException {

        final String hostname = Hostname.getHostname();
        final AgencyApi agencyApi = AgencyApiFactory.getAgencyApi();

        int interval = AppProperties.getInterval();

/*
        // This was my first implementation before I've found JProcessesMetricsCollector
        MetricsCollector collector = Platform.isWindows() ?
                new WindowsMetricsCollector() : new UnixMetricsCollector();
*/
        MetricsCollector collector = new JProcessesMetricsCollector();

        // TODO: implement cron or other precise control approach
        for (; ; ) {
            List<TaskDto> tasks = collector.getTasks();
            sortTasks(tasks);
            interval = sendTasks(hostname, agencyApi, tasks, interval);
            Thread.sleep(interval);
        }
    }

    private static int sendTasks(String hostname, AgencyApi agencyApi, List<TaskDto> tasks, int interval) {
        SettingsDto newSettings = null;
        try {
            newSettings = agencyApi.putMetrics(
                    hostname,
                    new MetricsDto(new Date(), tasks, new SettingsDto(interval))
            );
        } catch (Exception e) {
            log.warn("Failed to send metrics to Agency: {}", e.getMessage());
        }

        int newInterval = interval;
        if (nonNull(newSettings) && nonNull(newSettings.getInterval())) {
            newInterval = newSettings.getInterval();
        }
        return newInterval;
    }

    private static void sortTasks(List<TaskDto> tasks) {
        Comparator<TaskDto> taskComparator = Comparator.comparingLong(task -> parseLongSilently(task.getMemory(), "memory"));
        tasks.sort(taskComparator.reversed());
    }

}
