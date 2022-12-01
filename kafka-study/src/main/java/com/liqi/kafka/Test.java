package com.liqi.kafka;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String[] args) {
        String data = "{\n" +
                "    \"commSeqNo\": \"2021062314141501\",\n" +
                "    \"firmware\": \"1.0\",\n" +
                "    \"appVersion\": \"1.2.4\",\n" +
                "    \"imei\": \"862822030087161\",\n" +
                "    \"protocolVersion\": \"1.0\",\n" +
                "    \"busiType\": \"K01\",\n" +
                "    \"profileList\": \"[\n" +
                "        {\n" +
                "            \"iccid\": \"10601100300003000145\",\n" +
                "            \"status\": \"0\"\n" +
                "        }    ]\",\n" +
                "    \"imsi\": \"454061110309818\",\n" +
                "    \"network\": \"460,1,1234,88776655,-89,CMCC,LTE\",\n" +
                "    \"dataStats\": \"444555\"\n" +
                "}";
        data = data.replaceAll("(\"\\[)", "\\[").replaceAll("(\\]\")", "\\]");

        JSONObject jsonObject = JSONObject.parseObject(data);
        String profileList = jsonObject.getString("profileList");
        JSONArray objects = JSONObject.parseArray(profileList);
        System.out.println(objects);

    }
}
