package com.jyan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 江延
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Log对象", description="")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "事件")
    private String action;

    @ApiModelProperty(value = "数据")
    private String data;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userid;

    @ApiModelProperty(value = "用户ip")
    private String ip;

    @ApiModelProperty(value = "日志创建时间")
    @TableField("createdTime")
    private Date createdtime;


}
