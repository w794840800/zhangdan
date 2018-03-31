package com.beidou.ybz.accountbook.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonTools {
    public GsonTools() {
        // TODO Auto-generated constructor stub
    }

    // 使用Gson进行解析Person
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            Log.i("Gson", "getObject");
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return t;
    }

    // 使用Gson进行解析 List<Person>
    public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();

        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
            Log.i("Gson", "getObjectList");
        } catch (Exception e) {
        }
        return list;
    }

    public static <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * @param uglyJSONString
     * @return
     */
    public static String jsonFormatter(String uglyJSONString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJSONString);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }
}
