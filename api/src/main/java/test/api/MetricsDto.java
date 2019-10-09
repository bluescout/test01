package test.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MetricsDto {

    List<TaskDto> tasks;
    SettingsDto settings;

}
