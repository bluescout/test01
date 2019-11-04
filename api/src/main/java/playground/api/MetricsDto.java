package playground.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MetricsDto {
    // use Date as Local/ZonedDateTime is not well supported by Feign
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    Date timestamp;
    List<TaskDto> tasks;
    SettingsDto settings;
}
