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
 * 上传学生答题情况servlet
 */
@WebServlet(name = "UploadAnswerServlet")
public class UploadAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String taskId = jsonObject.getString("taskId"); // 训练号
        String stdChoice = jsonObject.getString("stdChoice");   // 每道题答题情况
        String taskScore = jsonObject.getString("taskScore");
        String stdId = jsonObject.getString("stdId");

        // todo 将本次的答题情况存入数据库

        // 打包json返回前端
        Map<String, Object> map = new HashMap<>();
        // todo
        //  if (添加成功)...
        map.put("code", 100);
        map.put("message", "提交成功");
        MakeJson.make(response, map);
    }
}
