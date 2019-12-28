package myServlet.forWXTeacher;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import myUnit.GetRequestJsonUtils;
import myUnit.MakeJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 教师新建签到servlet
 */
@WebServlet(name = "NewRegisterServlet")
public class NewRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        // 获取前端发来的data
        String userId = jsonObject.getString("userId");
        String classId = jsonObject.getString("classId");
        String endTime = jsonObject.getString("endTime");   // 签到结束时间
        String maxNum = jsonObject.getString("maxNum"); // 该签到最大人数
        // 经纬度
        String tchLatitude = jsonObject.getString("tchLatitude");
        String tchLongitude = jsonObject.getString("tchLongitude");
        // 有效距离（半径）
        int effectiveDistance = jsonObject.getInteger("effectiveDistance");

        // 数据库需要：签到id、课程id、经纬度、教师id、最大容量

        // todo 在老师的签到表添加一条签到记录
        //  if (添加成功)...
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "发布签到成功");
        MakeJson.make(response, map);
    }
}
