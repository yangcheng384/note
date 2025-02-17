package com.misc.note.common.annotations;

import com.misc.note.common.resp.ResultEnum;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface Error {

    ResultEnum resultEnum() default ResultEnum.NORMAL;
}
