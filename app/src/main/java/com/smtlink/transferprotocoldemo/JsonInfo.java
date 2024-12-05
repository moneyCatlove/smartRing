package com.smtlink.transferprotocoldemo;

import org.json.JSONObject;

import java.io.Serializable;

public class JsonInfo implements Serializable {
    public String cmdKey;
    public JSONObject jsonObject;
}
//json으로 데이터 받아오는 fun