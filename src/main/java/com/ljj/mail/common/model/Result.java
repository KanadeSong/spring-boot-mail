package com.ljj.mail.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 页面响应entity
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 0:35
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private Result() {
        put("code", 0);
    }

    public static Result error() {
        return error(500, "未知异常，请联系管理员！");
    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    private static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Object msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
