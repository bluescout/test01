package com.modata.test.agent.agency;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {
    private int pid;
    private String name;
    private double cpu;
    private long memory;
    private long diskIo;
}
