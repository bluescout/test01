package com.modata.test.agent;

import com.modata.test.agent.agency.*;
import com.modata.test.agent.helpers.AgencyApiFactory;
import com.modata.test.agent.helpers.Hostname;
import com.modata.test.agent.helpers.AppProperties;
import com.modata.test.agent.metrics.MetricsCollector;
import com.modata.test.agent.metrics.UnixMetricsCollector;
import com.modata.test.agent.metrics.WindowsMetricsCollector;
import com.sun.jna.Platform;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
public class Application {

    public static void main(String[] args) throws InterruptedException {

        final String hostname = Hostname.getHostname();

        final AgencyApi agencyApi = AgencyApiFactory.getAgencyApi();

        MetricsCollector collector = Platform.isWindows() ?
                new WindowsMetricsCollector() : new UnixMetricsCollector();

        // TODO: implement cron or other precise control approach
        Integer interval = AppProperties.getInterval();
        for (; ; ) {
            List<TaskDto> tasks = collector.getTasks();
            System.out.println(tasks);

            SettingsDto newSettings = null;
            try {
                newSettings = agencyApi.putMetrics(
                        hostname,
                        new MetricsDto(tasks, new SettingsDto(interval))
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
