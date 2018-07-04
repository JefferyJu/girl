package com.imooc.GirlException;

import com.imooc.enums.ResultEnum;

/**
 * @author: JefferyJu
 * @date: 2018/7/4
 */
public class GirlException extends RuntimeException {

    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
