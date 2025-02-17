package com.misc.note.common.resp;

import lombok.Data;

@Data
public class ResultVO<T> {

    //响应编码
    private Integer code;

    //响应信息
    private String msg;

    //响应数据
    private T data;

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
