package myUnit;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MakeJson {
    /**
     * 此静态方法是构造json对象并传给前端
     * @param response
     * @param map 传入的数据
     * @return
     * @throws IOException
     */
    public static void make(HttpServletResponse response, Map<String, Object> map) throws IOException {
        String result = new Gson().toJson(map);
        PrintWriter printW = response.getWriter();
        printW.write(result);
        printW.close();
    }
}
