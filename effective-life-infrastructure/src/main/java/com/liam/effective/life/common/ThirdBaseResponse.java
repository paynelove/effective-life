package com.liam.effective.life.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author liam.li
 */
@Data
public abstract class ThirdBaseResponse implements Serializable {

    private String code;

    private String msg;

    /**
     * 是否异常
     * @return
     */
    public abstract Boolean hasError();

    public String errorMsg(){
        if(StringUtils.isNotBlank(msg)){
            return msg;
        }
        return null;
    }

}
