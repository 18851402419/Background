package myServlet.forWXTeacher;

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
 * 老师创建一门课程servlet
 */
@WebServlet(name = "CreateClassServlet")
public class CreateClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<>();
        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String teacherId = jsonObject.getString("teacherId"); // 获得老师的id
        String maxNum = jsonObject.getString("maxNum"); // 课程最大容量
        // todo classId应该是自动生成 不能用户指定
        //String classId = jsonObject.getString("classId");
        String newTime = jsonObject.getString("newTime");   // 课程创建时间
        String className = jsonObject.getString("className");   // 课程名

        // todo 删除该课程id对应的课程
        //  if// 删除成功
        map.put("message", "教师新建课堂成功");
        map.put("code", 100);
        MakeJson.make(response, map);
    }
}
