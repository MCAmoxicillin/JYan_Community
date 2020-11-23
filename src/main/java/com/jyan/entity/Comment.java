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
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "评论文章id")
    @TableField("articleId")
    private Integer articleid;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论用户id")
    @TableField("userId")
    private Integer userid;

    @ApiModelProperty(value = "父评论id，如果没有就为null		")
    @TableField("parentId")
    private Integer parentid;

    @ApiModelProperty(value = "评论时间")
    @TableField("createdTime")
    private Date createdtime;

    @ApiModelProperty(value = "评论用户头像")
    @TableField("userImg")
    private String userimg;

    @ApiModelProperty(value = "评论用户网名")
    @TableField("userNetname")
    private String usernetname;


}
