package com.modata.test.agent.agency;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MetricsDto {

    List<TaskDto> tasks;
    SettingsDto settings;

}
