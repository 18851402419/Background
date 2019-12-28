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
import java.util.HashMap;
import java.util.Map;

/**
 * 学生新增课程servlet
 */
@WebServlet(name = "StuAddClassServlet")
public class StuAddClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 获取课程id和学生id
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String classId = jsonObject.getString("classId");
        String studentId = jsonObject.getString("studentId");

        // todo 根据课程id查数据库，新增一名学生

        // 新增成功
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "学生添加课程成功");
        MakeJson.make(response, map);
    }
}
