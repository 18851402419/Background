package myServlet.forStudent;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import myUnit.GetRequestJsonUtils;
import myUnit.MakeJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取学生当前课程下的所有训练servlet
 */
@WebServlet(name = "GetTaskServlet")
public class GetTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 获取课程名和微信id
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String userWxId = jsonObject.getString("userWxId");
        String classId = jsonObject.getString("classId");
        // todo 根据WxId和课程名查询数据库该用户该课程下所有的训练

        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "查询成功");
        Map<String, Object> data = new HashMap<>();
        // data存的是题目号、题目描述、题目选项、题目答案
        data.put("taskId", "taskId");
        data.put("taskName", "taskName");
        data.put("taskState", "taskState");
        data.put("taskScore", "taskScore");
        map.put("data", data);
        MakeJson.make(response, map);
        // MakeJson(response, map);
    }

//    private void MakeJson(HttpServletResponse response, Map<String, Object> map) throws IOException {
//        String result = new Gson().toJson(map);
//        PrintWriter printW = response.getWriter();
//        printW.write(result);
//        printW.close();
//    }
}
