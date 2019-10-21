package test.agent.helpers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class MappingHelper {
    private MappingHelper() {
    }

    public static long parseLongSilently(String value, String fieldName) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            log.error("Failed to parse value [{}] of field [{}] as an integer.", value, fieldName);
        }
        return 0;
    }

}
