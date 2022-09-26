package com.liam.effective.life.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.liam.effective.life.common.ThirdBaseResponse;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @author liam.li
 */
@Data
public class BaiduResponse<T> extends ThirdBaseResponse {

    private static final long serialVersionUID = 1L;

    private String status;

    private String t;

    @JsonProperty("set_cache_time")
    private String setCacheTime;

    private String qid;

    private String code;

    private String msg;

    private T data;


    @Override
    public Boolean hasError(){
        return !"0".equals(status) || data == null;
    }

    public Boolean isBlack(){

        if(data instanceof String){
            return StringUtils.isBlank(data.toString());
        }else if(data instanceof Collection){
            return CollectionUtils.isEmpty((Collection<?>) data);
        }
        return true;
    }

}
