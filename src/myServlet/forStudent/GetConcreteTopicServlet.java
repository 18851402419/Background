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
 * 获取当前课程下某一训练的题目servlet
 */
@WebServlet(name = "GetConcreteTopicServlet")
public class GetConcreteTopicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // 获取训练号taskId
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String taskId = jsonObject.getString("taskId");
        // todo 查询数据库该task的所有题目

        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "查询成功");
        Map<String, Object> questionData = new HashMap<>();
        for (int i = 0; i < 5; i++){
            // 拆这个task下的所有题目
            Map<String, Object> data = new HashMap<>();
            // todo 拆
            data.put("questionId", "questionId");
            data.put("questionTitle", "questionTitle");
            data.put("questionOptions", "questionOptions");
            data.put("questionAnswer", "questionAnswer");
            data.put("taskScore", "taskScore");
            questionData.put("data" + i, data);
        }

        MakeJson.make(response, map);
    }
}
