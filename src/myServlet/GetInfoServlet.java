package myServlet;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import myUnit.ClassCondition;
import myUnit.GetRequestJsonUtils;
import myUnit.MakeJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取用户基本信息servlet
 */
@WebServlet(name = "GetInfoServlet")
public class GetInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        // 获取WxId
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
        String userWxId = jsonObject.getString("userWxId");
        // 查用户类型表，获得用户的类型
        String userType = getUserType(userWxId);
        String userId = "";
        String userName = "";
        String userMajor = "";
        // 存放每一门课程的信息
        Map<String, Object> classInfo = new HashMap<>();
        if (userType.equals("学生")){
            // 查询这个学生的基本信息
            ResultSet rs = getInfo(userWxId);
            try {
                if (rs.next()){
                    userId = rs.getString("Student_id");
                    userName = rs.getString("Student_name");
                    userMajor = rs.getString("Major");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ResultSet rsClass = getHaveCourseInfo(userWxId);
            try {
                while (rs.next()){
                    // 拆这个学生的每一门课
                    Map<String, Object> eachClass = new HashMap<>();
                    eachClass.put("classId", rsClass.getString("Course_id"));
                    eachClass.put("className", rsClass.getString("Course_name"));
                    classInfo.put("eachClass", eachClass);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (userType.equals("教师")){
            // 查询这个教师的基本信息
            ResultSet rs = getInfo(userWxId);
            try {
                if (rs.next()){
                    // 老师无专业
                    userId = rs.getString("Student_id");
                    userName = rs.getString("Student_name");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            ResultSet rsClass = getAllCourseInfo(userWxId);
            try {
                while (rs.next()){
                    // 拆这个老师的每一门课
                    Map<String, Object> eachClass = new HashMap<>();
                    eachClass.put("classId", rsClass.getString("Course_id"));
                    eachClass.put("className", rsClass.getString("Course_name"));
                    eachClass.put("classNewTime", rsClass.getString("time"));
                    eachClass.put("enrollment", rsClass.getString("current"));
                    eachClass.put("maxNum", rsClass.getString("maxNum"));
                    classInfo.put("eachClass", eachClass);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        // 打包json返回前端
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "查询成功");
        // data存放一系列信息
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        data.put("userType", userType);
        data.put("userMajor", userMajor);   // 若为老师，这个为空
        data.put("userName", userName);
        data.put("classInfo", classInfo);
        map.put("data", data);
        MakeJson.make(response, map);
    }

    /**
     * 通过用户微信id查已选的所有课程信息
     * @param userWxId 用户微信id
     * @return 所有的课程
     */
    private ClassCondition[] GetAllClassFromDatabase(String userWxId) {
        ClassCondition alls[] = null;
        return alls;
    }
}
