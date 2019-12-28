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
 * 教师获取某一课程下的训练及信息servlet
 */
@WebServlet(name = "GetClassTaskServlet")
public class GetClassTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String classId = jsonObject.getString("classId"); // 获得课程的id

        // todo 根据课程的id查询所有的训练及信息
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "获取训练信息记录成功");
        Map<String, Object> data = new HashMap<>();
        int i = 0;
        while (i < 10){
            // 根据resultset解析
            Map<String, Object> taskData = new HashMap<>();
            taskData.put("taskId", "taskId" + i);
            taskData.put("taskName", "taskName" + i);
            taskData.put("releaseTime", "releaseTime" + i);
            taskData.put("submitNum", "submitNum" + i);
            taskData.put("topScore", "topScore" + i);
            taskData.put("lowScore", "lowScore" + i);
            taskData.put("averageScore", "averageScore" + i);
            data.put("taskData" + i, taskData);
            i++;
        }
        MakeJson.make(response, map);
    }
}
