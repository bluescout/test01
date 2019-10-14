package test.agency.controllers;

public final class AgentApi {
    private AgentApi() {
    }

    private static final String BASE_PATH = "/agent-api";

    public static class V1 {
        private V1() {
        }

        private static final String BASE_PATH_V1 = BASE_PATH + "/v1";

        public static final String METRICS = BASE_PATH_V1 + "/computers/{computerId}/metrics";
    }

}
