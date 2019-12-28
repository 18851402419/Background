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
 * 教师发布的所有签到记录servlet
 */
@WebServlet(name = "RegisterRecordServlet")
public class RegisterRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String userWxId = jsonObject.getString("userWxId"); // 获得教师微信id

//        todo 根据教师的微信id查询他所有的签到记录
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        int i = 0;
        while (i < 10){
            // 根据resultset解析
            Map<String, Object> registerData = new HashMap<>();
            registerData.put("register", "register" + i);
            registerData.put("registerClass", "registerClass" + i);
            registerData.put("endTime", "endTime" + i);
            registerData.put("registerNum", "registerNum" + i);
            registerData.put("totalNum", "totalNum" + i);
            data.put("registerData" + i, registerData);
            i++;
        }
        map.put("code", 100);
        map.put("message", "查询签到记录成功");
        MakeJson.make(response, map);
    }
}
