package test.agency.controllers;

public final class UiApi {
    private UiApi() {
    }

    private static final String BASE_PATH = "/ui-api";

    public static class V1 {
        private V1() {
        }

        private static final String BASE_PATH_V1 = BASE_PATH + "/v1";

        public static final String COMPUTERS = BASE_PATH_V1 + "/computers";
        public static final String METRICS = COMPUTERS + "/{computerId}";
        public static final String METRICS_HISTORY = COMPUTERS + "/{computerId}/history";
    }

}
