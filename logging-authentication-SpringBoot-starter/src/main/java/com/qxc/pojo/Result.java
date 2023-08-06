package com.qxc.pojo;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Author qxc
 * @Date 2023 2023/8/6 0:43
 * @Version 1.0
 * @PACKAGE com.qxc.pojo
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public final class Result{
    private Boolean finish;
    private String message;
    private Object date;
}
