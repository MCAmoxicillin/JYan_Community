package com.jyan.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
/**
 * 注册信息
 */
public class RegisterMessage {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String userpwd;
    @ApiModelProperty(value = "确认密码")
    private String reuserpwd;
}
