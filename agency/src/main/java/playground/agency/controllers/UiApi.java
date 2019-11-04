package playground.agency.controllers;

final class UiApi {
    private UiApi() {
    }

    private static final String BASE_PATH = "/ui-api";

    final static class V1 {
        private V1() {
        }

        private static final String BASE_PATH_V1 = BASE_PATH + "/v1";

        static final String COMPUTERS = BASE_PATH_V1 + "/computers";
        static final String METRICS = COMPUTERS + "/{computerId}";
        static final String METRICS_HISTORY = COMPUTERS + "/{computerId}/history";
    }

}
