package test.agent.api;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import test.api.AgencyApi;
import test.api.MetricsDto;
import test.api.SettingsDto;

@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
})
// TODO: move API and DTOs to a new module to share between Agency and Agent
public interface FeignAgencyApi extends AgencyApi {

    // TODO: add authentication
    @RequestLine("PUT /agent-api/v1/computers/{computerId}/metrics")
    SettingsDto putMetrics(@Param("computerId") String computerId, MetricsDto metrics);

}
