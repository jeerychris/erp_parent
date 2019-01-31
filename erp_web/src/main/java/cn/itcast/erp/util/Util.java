/**
 * 常用方法工具类
 */
package cn.itcast.erp.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class Util {

    /**
     * 把result转换成JSON字符串格式，并输出给页面
     *
     * @param jsonString
     * @param res
     */
    public static void write2UI(String jsonString, HttpServletResponse res) {
        //输出到页面
        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=UTF-8");
        try {
            res.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给JSON字符串的key,加上前缀。构建prefix.key的json字符串
     *
     * @param json
     * @param prefix
     * @return
     */
    public static String addPrefix2JsonString(String json, String prefix) {
        String result = null;
        //把JSON字符串转成Map对象

        Map<String, Object> map = JSON.parseObject(json);

        Map<String, Object> dataMap = new HashMap<>();
        for (String key : map.keySet()) {
            Object ob = map.get(key);
            if (ob instanceof Map) {
                Map<String, Object> map2 = (Map) ob;
                for (String key2 : map2.keySet()) {
                    dataMap.put(prefix + "." + key + "." + key2, map2.get(key2));
                }
            } else {
                dataMap.put(prefix + "." + key, ob);
            }

        }
        result = JSON.toJSONString(dataMap);
        return result;
    }
}
