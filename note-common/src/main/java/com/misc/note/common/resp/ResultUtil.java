package com.misc.note.common.resp;

public class ResultUtil {

    public static <T> ResultVO<T> success(){
        return resp(ResultEnum.SUCCESS);
    }

    public static <T> ResultVO<T> success(T data){
        return resp(ResultEnum.SUCCESS, data);
    }

    public static <T> ResultVO<T> failure(){
        return resp(ResultEnum.FAILURE);
    }

    public static <T> ResultVO<T> failure(T data){
        return resp(ResultEnum.FAILURE, data);
    }

    public static <T> ResultVO<T> resp(int code, String msg, T data){
        return new ResultVO<>(code, msg, data);
    }

    public static <T> ResultVO<T> resp(ResultEnum resultEnum){
        return new ResultVO<>(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static <T> ResultVO<T> resp(ResultEnum resultEnum, T data){
        return new ResultVO<>(resultEnum.getCode(), resultEnum.getMsg(), data);
    }
}
