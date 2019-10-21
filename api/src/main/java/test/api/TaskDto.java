package test.api;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class TaskDto {
    private String pid;
    private String name;
    private String cpuTime;
    private String memory;
    private String diskIo;
}
