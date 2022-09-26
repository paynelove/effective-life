package com.liam.effective.life.dto.data;

public enum ErrorCode{

    /**
     * 参数错误
     */
    ARGS_ERROR("ARGS_ERROR", "参数错误"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误"),
    /**
     * 第三方错误
     */
    THIRD_ERROR("THIRD_ERROR", "第三方错误"),
    /**
     * demo biz error
     */
    B_CUSTOMER_companyNameConflict("B_CUSTOMER_companyNameConflict", "客户公司名冲突"),
    ;

    private final String errCode;
    private final String errDesc;

    private ErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }
}
