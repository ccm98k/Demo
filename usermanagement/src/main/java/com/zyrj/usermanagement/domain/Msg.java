package com.zyrj.usermanagement.domain;

import java.util.HashMap;

/**
 * @auther ccm
 * @date 19:45
 */
public class Msg {
    private Integer code;
    //100成功，200失败
    private String message;

    private HashMap<String,Object> extend=new HashMap<String, Object>();

    public static Msg success(){
       Msg result=  new Msg();
        result.setCode(100);
        result.setMessage("查找成功");
        return result;
    }
    public static Msg fail(){
        Msg result=  new Msg();
        result.setCode(200);
        result.setMessage("查找失败");
        return result;
    }

    public Msg add(String s,Object o){
        this.getExtend().put(s,o);
        return this;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(HashMap<String, Object> extend) {
        this.extend = extend;
    }
}
