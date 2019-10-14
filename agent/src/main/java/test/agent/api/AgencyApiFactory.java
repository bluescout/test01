package test.agent.api;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import test.agent.helpers.AppProperties;
import test.api.AgencyApi;

public class AgencyApiFactory {

    public static AgencyApi getAgencyApi() {
        return LazyHolder.api;
    }

    private static class LazyHolder {
        static final AgencyApi api = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new CustomFeignLogger())
                .logLevel(Logger.Level.FULL)
                .target(FeignAgencyApi.class, AppProperties.getAgencyUrl());
    }

}
