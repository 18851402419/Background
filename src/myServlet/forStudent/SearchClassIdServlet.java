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
 * 根据课程id查找课程servlet
 */
@WebServlet(name = "SearchClassIdServlet")
public class SearchClassIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 获取课程id
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String searchClassId = jsonObject.getString("searchClassId");

        // todo 根据课程id查数据库

        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "课程查询成功");
        Map<String, Object> data = new HashMap<>();
        data.put("classId", "classId");
        data.put("className", "className");
        data.put("teacherName", "teacherName");
        data.put("stdNum", "stdNum");
        map.put("data", data);
        MakeJson.make(response, map);
    }
}
