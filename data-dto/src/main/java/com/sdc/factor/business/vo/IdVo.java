package com.sdc.factor.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * id value object
 *
 * @author Harway
 * @since 2018/12/13
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@ApiModel(value = "id")
public class IdVo {

    @ApiModelProperty(value = "id", required = true, dataType = "long")
    @NotNull(message = "100601")
    private Long id;
}
