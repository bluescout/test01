package playground.api;

public interface AgencyApi {

    SettingsDto putMetrics(String computerId, MetricsDto metrics);

}
