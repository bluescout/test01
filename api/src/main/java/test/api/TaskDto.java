package test.api;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskDto {
    private int pid;
    private String name;
    private double cpu;
    private long memory;
    private long diskIo;
}
