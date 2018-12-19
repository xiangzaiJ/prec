package utils;

import com.alibaba.fastjson.JSONObject;


/**
 * Created by Administrator on 2015/5/30.
 * 返回json对象
 */
public final class JsonResult {

    public static JSONObject resultSuccess(String msg, long startTime) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", true);
        jsonObj.put("msg", msg);
        jsonObj.put("time", DateTimeUtil.getCurrentTime());
        jsonObj.put("useTime", System.currentTimeMillis() - startTime);
        return jsonObj;
    }

    public static JSONObject resultError(String msg, long startTime) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", false);
        jsonObj.put("msg", msg);
        jsonObj.put("time", DateTimeUtil.getCurrentTime());
        jsonObj.put("useTime", System.currentTimeMillis() - startTime);
        return jsonObj;
    }

    public static JSONObject resultSuccess(Object object) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", true);
        jsonObj.put("msg", "操作成功");
        jsonObj.put("data", object);
        return jsonObj;
    }

    public static JSONObject resultSuccess2(Object object, Object tip) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", true);
        jsonObj.put("msg", "操作成功");
        jsonObj.put("data", object);
        jsonObj.put("tip", tip);
        return jsonObj;
    }

    public static JSONObject resultSuccess(String msg, Object object) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", true);
        jsonObj.put("msg", msg);
        jsonObj.put("data", object);
        return jsonObj;
    }

    public static JSONObject resultError(String msg) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("flag", false);
        jsonObj.put("msg", msg);
        return jsonObj;
    }

    //返回json对象
    public static JSONObject GetSuccessJson(Boolean success, String msg) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Success", success);
        jsonObj.put("msg", msg);
        return jsonObj;
    }

    //返回json对象
    public static JSONObject GetJson(int status, String msg) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("code", status);
        jsonObj.put("time", DateTimeUtil.getCurrentTime());
        jsonObj.put("msg", msg);
        return jsonObj;
    }

    public static JSONObject GetJson(int status) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("code", status);
        jsonObj.put("time", DateTimeUtil.getCurrentTime());
        return jsonObj;
    }

    public static JSONObject ErrorStep(String msg) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("msg", msg);
        jsonObj.put("code", 1);
        jsonObj.put("time", DateTimeUtil.getCurrentTime());
        jsonObj.put("session_token", "");
        jsonObj.put("page_token", "");
        return jsonObj;
    }
}
