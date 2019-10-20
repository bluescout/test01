package test.agency.controllers;

final class AgentApi {
    private AgentApi() {
    }

    private static final String BASE_PATH = "/agent-api";

    final static class V1 {
        private V1() {
        }

        private static final String BASE_PATH_V1 = BASE_PATH + "/v1";

        static final String METRICS = BASE_PATH_V1 + "/computers/{computerId}/metrics";
    }

}
