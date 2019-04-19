package com.demo.utils;
/*
* 操作返回结果类
* */
public class AjaxResult{

    //操作是否成功
    private Integer code;
    //返回的信息
    private String msg;

    private Object data;

    private Integer key;

    public Integer getKey() {
        return key;
    }

    public AjaxResult setKey(Integer key) {
        this.key = key;
        return this;
    }

    public AjaxResult setData(Object data) {
        this.data = data;
        return this;
    }

    public Object getData() {
        return data;
    }


    public Integer getCode() {
        return code;
    }

    public AjaxResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg(){
        return msg;
    }

    public AjaxResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static AjaxResult getAjaxResult(){
        return new AjaxResult();
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
