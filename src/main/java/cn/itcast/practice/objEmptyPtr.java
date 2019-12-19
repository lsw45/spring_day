package cn.itcast.practice;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class objEmptyPtr {
    public static void main(String[] args){
        HashMap<String,Object> map = new HashMap<>();
        map.put("code",233);
        map.put("data","{\"result\":\"fail\"}");
        JSONObject result = new JSONObject(map);
        System.out.println(Integer.valueOf(233).equals(result.getIntValue("code"))); //true
        System.out.println(("").equals(result.getIntValue("code"))); //false

        System.out.println(result.getIntValue("message") == 0);//true
        System.out.println(Integer.valueOf(0).equals(result.getInteger("message")));//false

        JSONObject data = JSONObject.parseObject(result.get("data").toString());
        System.out.println(("fail").equals(data.getString("result")));//true
        System.out.println(("fail").equals(data.getString("result2")));//false
        System.out.println(data.getString("result2").equals("fail")); // NullPointerException
    }
}
