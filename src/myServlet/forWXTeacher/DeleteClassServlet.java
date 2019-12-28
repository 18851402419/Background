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
 * 教师删除课堂servlet
 */
@WebServlet(name = "DeleteClassServlet")
public class DeleteClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<>();
        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String classId = jsonObject.getString("classId"); // 获得课程的id

        // todo 删除该课程id对应的课程
        //  if// 删除成功
        map.put("message", "课堂删除成功");
        map.put("code", 100);
        MakeJson.make(response, map);
    }
}
