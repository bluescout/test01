package com.modata.test.agent.helpers;

import com.modata.test.agent.agency.AgencyApi;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class AgencyApiFactory {

    private static class LazyHolder {
        static final AgencyApi api = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .target(AgencyApi.class, AppProperties.getAgencyUrl());
    }

    public static AgencyApi getAgencyApi() {
        return LazyHolder.api;
    }

}
