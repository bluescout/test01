package com.modata.test.agent.agency;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({
        "Accept: application/json",
        "Content-Type: application/json"
})
// TODO: move API and DTOs to a new module to share between Agency and Agent
public interface AgencyApi {

    // TODO: add authentication
    // TODO: think of computerId collisions
    @RequestLine("PUT /v1/metrics/{computerId}")
    SettingsDto putMetrics(@Param("computerId") String computerId, MetricsDto metrics);

}
