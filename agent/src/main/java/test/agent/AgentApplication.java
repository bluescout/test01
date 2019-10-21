package test.agent;

import com.sun.jna.Platform;
import lombok.extern.slf4j.Slf4j;
import test.agent.api.AgencyApiFactory;
import test.agent.helpers.AppProperties;
import test.agent.helpers.Hostname;
import test.agent.metrics.JProcessesMetricsCollector;
import test.agent.metrics.MetricsCollector;
import test.agent.metrics.UnixMetricsCollector;
import test.agent.metrics.WindowsMetricsCollector;
import test.api.AgencyApi;
import test.api.MetricsDto;
import test.api.SettingsDto;
import test.api.TaskDto;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
public class AgentApplication {

    public static void main(String[] args) throws InterruptedException {

        final String hostname = Hostname.getHostname();

        final AgencyApi agencyApi = AgencyApiFactory.getAgencyApi();

/*
        // This was my first implementation before I've stumbled upon JProcessesMetricsCollector
        MetricsCollector collector = Platform.isWindows() ?
                new WindowsMetricsCollector() : new UnixMetricsCollector();
*/
        MetricsCollector collector = new JProcessesMetricsCollector();

        // TODO: implement cron or other precise control approach
        Integer interval = AppProperties.getInterval();
        for (; ; ) {
            List<TaskDto> tasks = collector.getTasks();
            Comparator<TaskDto> taskComparator = Comparator.comparingInt(task -> Integer.parseInt(task.getMemory()));
            tasks.sort(taskComparator.reversed());

            SettingsDto newSettings = null;
            try {
                newSettings = agencyApi.putMetrics(
                        hostname,
                        new MetricsDto(new Date(), tasks, new SettingsDto(interval))
                );
            } catch (Exception e) {
                log.warn("Failed to send metrics to Agency: {}", e.getMessage());
            }

            if (nonNull(newSettings) && nonNull(newSettings.getInterval())) {
                interval = newSettings.getInterval();
            }
            Thread.sleep(interval);
        }
    }

}
