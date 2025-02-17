package com.misc.note.common.exception;

import com.misc.note.common.resp.ResultEnum;
import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private Integer code;
    private String msg;

    public BizException() {
    }

    public BizException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public BizException(int code, String message) {
        super(message);
        this.msg = message;
        this.code = code;

    }

    public BizException(String message) {
        super(message);
        this.msg = message;
    }
}
