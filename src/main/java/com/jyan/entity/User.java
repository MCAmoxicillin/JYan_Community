package com.jyan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户登录名")
    @TableField("userName")
    private String username;

    @ApiModelProperty(value = "用户登录密码")
    @TableField("userPwd")
    private String userpwd;

    @ApiModelProperty(value = "用户网名")
    @TableField("netName")
    private String netname;

    @ApiModelProperty(value = "用户签名")
    @TableField("userSlogan")
    private String userslogan;

    @ApiModelProperty(value = "用户性别")
    private String sex;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @ApiModelProperty(value = "用户角色id")
    @TableField("roleId")
    private Integer roleid;

    @ApiModelProperty(value = "用户头像")
    private String image;

    @ApiModelProperty(value = "用户手机号码")
    private String phone;

    public User(Integer id,String username, String userpwd, Integer roleid) {
        this.id=id;
        this.username = username;
        this.userpwd = userpwd;
        this.roleid = roleid;
    }
}
