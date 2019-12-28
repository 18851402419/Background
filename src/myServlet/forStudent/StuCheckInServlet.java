package myServlet.forStudent;

import com.alibaba.fastjson.JSONObject;
import myUnit.GetRequestJsonUtils;
import myUnit.MakeJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 学生签到servlet
 */
@WebServlet(name = "StuCheckInServlet")
public class StuCheckInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String studentId = jsonObject.getString("studentId");
        String classId = jsonObject.getString("classId");
        String registerTime = jsonObject.getString("registerTime");
        String latitude = jsonObject.getString("latitude");
        String longitude = jsonObject.getString("longitude");

        // todo 查询数据库得到老师发布的时间和签到结束时间、老师发布的经纬度
        String teaEndTime = "";
        String tlati = "";
        String tlong = "";
        // 将两个时间转化时间戳
        long registerT = getStringToLong(registerTime);
        long endTime = getStringToLong(teaEndTime);
        // 判断签到是否符合要求
        if (registerT > endTime){
            // 超时
            Map<String, Object> map = new HashMap<>();
            map.put("code", 200);
            map.put("message", "学生签到超时");
            MakeJson.make(response, map);
        }
        else if (isMatch(tlati, tlong, latitude, longitude)){
            // 是否不在指定地点内
            Map<String, Object> map = new HashMap<>();
            map.put("code", 300);
            map.put("message", "学生签到不在范围");
            MakeJson.make(response, map);
        }
        else {
            // 签到成功
            Map<String, Object> map = new HashMap<>();
            map.put("code", 100);
            map.put("message", "学生签到成功");
            MakeJson.make(response, map);
        }

        // todo 根据相应的签到是否超时或是不在范围内存签到记录入数据库
    }

    /**
     * 判断学生签到地点是否符合要求
     *
     * @param tlati 老师的坐标
     * @param tlong 老师的坐标
     * @param latitude 学生的坐标
     * @param longitude 学生的坐标
     * @return
     */
    private boolean isMatch(String tlati, String tlong, String latitude, String longitude) {
        double d = Math.pow(Double.parseDouble(tlati) - Double.parseDouble(latitude), Double.parseDouble(tlong) - Double.parseDouble(longitude));
        return d > 2;
    }

    /**
     * 将日期string转化为时间戳
     * @param timeStr
     * @return 返回时间戳
     */
    private static long getStringToLong(String timeStr) {
//        String time = "2010-11-20 11:10:10";

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = formatter.parse(timeStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
