package test.api;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class TaskDto {
    private int pid;
    private String name;
    private String cpuTime;
    private Long memory;
    private Long diskIo;
}
