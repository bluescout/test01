package playground.agent.api;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import playground.agent.helpers.AppProperties;
import playground.api.AgencyApi;

public class AgencyApiFactory {

    public static AgencyApi getAgencyApi() {
        return LazyHolder.api;
    }

    private static class LazyHolder {
        static final AgencyApi api = Feign.builder()
                .retryer(Retryer.NEVER_RETRY)
                .requestInterceptor(new BasicAuthRequestInterceptor(AppProperties.getUsername(), AppProperties.getKey()))
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new CustomFeignLogger())
                .logLevel(Logger.Level.FULL)
                .target(FeignAgencyApi.class, AppProperties.getAgencyUrl());
    }

}
