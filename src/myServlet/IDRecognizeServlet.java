package myServlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import myUnit.GetRequestJsonUtils;
import myUnit.MakeJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
 * 身份验证servlet
 */
@MultipartConfig
@WebServlet(name = "IDRecognizeServlet")
public class IDRecognizeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
//        String obj = request.getParameter("jsonObj");
//        String userType = request.getParameter("userType");
//        String userId = request.getParameter("userId");
//        String userName = request.getParameter("userName");
//        String userWxId = request.getParameter("userWxId");

        // 使用JsonUtils将request的json拿出来
        JSONObject jsonObject = GetRequestJsonUtils.getRequestJsonObject(request);
//        String obj = jsonObject.getString("jsonObj");
//        String obj = jsonObject.getString("x");
//        JSONArray objs = jsonObject.getJSONArray("z");
//        JSONArray objss = jsonObject.getJSONArray("w");
        // 获取前端传来的用户类型、学号、用户名、微信id
        String userType = jsonObject.getString("userType");
        String userId = jsonObject.getString("userId");
        String userName = jsonObject.getString("userName");
        String userWxId = jsonObject.getString("userWxId");
//        System.out.println("--------------------------message--------------------------" + obj);
//        System.out.println("--------------------------message--------------------------" + objs);
//        System.out.println("--------------------------message--------------------------" + objss);
        // todo 判断该学号是否已经被使用
        // todo 将该用户添加入相应的数据库
        // 查询用户类型表是否有这个用户
        String getUsertype = getUserType(userWxId);

        if (getUsertype.equals("学生")){
            // 查询这个学生的学号和姓名是否和前端传来的一样
            ResultSet rs = getInfo(userWxId);
            Identify(response, userId, userName, rs);
        }
        else if (getUsertype.equals("教师")){
            // 查询这个教师的学号和姓名是否和前端传来的一样
            ResultSet rs = getInfo(userWxId);
            Identify(response, userId, userName, rs);
        }
        else {
            // 查询结果为空，即这个人不在数据库内，根据前端传过来的用户类型插入对应的表
            boolean insertSign = false;
            if (userType.equals("")){
                insertSign = addUser(userType, userId, userName, userWxId);
                if (insertSign){
                    // 插入成功
                    IdentifySuccess(response, userName);
                }
                else {
                    // 插入失败
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", 500);
                    map.put("message", "内部服务器错误");
                    map.put("name", userName);
                    MakeJson.make(response, map);
                }
            }
            else {
                // 前端传来的userType出现问题，为空
                Map<String, Object> map = new HashMap<>();
                map.put("code", 300);
                map.put("message", "前端userType为空");
                map.put("name", userName);
                MakeJson.make(response, map);
            }
        }

        // todo 获取数据库返回结果

        // 打包json返回前端
        IdentifySuccess(response, userName);
//        String result = new Gson().toJson(map);
//        PrintWriter printW = response.getWriter();
//        printW.write(result);
//        printW.close();
    }

    /**
     * 用户认证成功，code=100
     * @param response
     * @param userName
     * @throws IOException
     */
    private void IdentifySuccess(HttpServletResponse response, String userName) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 100);
        map.put("message", "认证成功");
        map.put("name", userName);
        MakeJson.make(response, map);
    }

    /**
     * 判断数据库已有的这个人是否和前端传过来的用户id和用户姓名相同
     * @param response
     * @param userId
     * @param userName
     * @param rs
     * @throws IOException
     */
    private void Identify(HttpServletResponse response, String userId, String userName, ResultSet rs) throws IOException {
        String getUserName = "";
        String getUserId = "";
        try {
            while (rs.next()) {
                getUserName = rs.getString("Student_name");
                getUserId = rs.getString("Student_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getUserId.equals(userId) && getUserName.equals(userName)) {
            // 前端发来的名字和数据库一样
            // 打包json返回前端
            IdentifySuccess(response, userName);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 200);
            map.put("message", "认证失败");
            map.put("name", userName);
            MakeJson.make(response, map);
        }
    }

    /**
     * 查询数据库是否有这个人
     * @param userWxId
     * @return 有这个人就返回存有个人信息的ResultSet
     */
    private ResultSet GetRS(String userWxId) {
        ResultSet rs = null;
        return rs;
    }

}
