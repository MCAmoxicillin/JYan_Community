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
@ApiModel(value="Article对象", description="")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "内容主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "发表内容用户id")
    @TableField("userId")
    private Integer userid;

    @ApiModelProperty(value = "内容分类id")
    @TableField("categoryId")
    private Integer categoryid;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "内容创建时间")
    @TableField("createdTime")
    private Date createdtime;

    @ApiModelProperty(value = "内容修改时间")
    @TableField("updateTime")
    private Date updatetime;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章浏览量")
    private Integer view;

    @ApiModelProperty(value = "用户网名")
    @TableField("userNetname")
    private String usernetname;

    @ApiModelProperty(value = "用户头像地址")
    @TableField("userImg")
    private String userimg;


}
