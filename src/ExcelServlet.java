import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;


public class ExcelServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        ExcelDemo excelDemo = new ExcelDemo();
        try {
            excelDemo.createExcel(year,month);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filename = month + "Month.xls";
        filename  = URLEncoder.encode(filename,"UTF-8");
        //下载需要设置响应头
        response.setHeader("content-Type","application/octet-stream");//MIME类型为二进制
        response.setHeader("content-Disposition","attachment;filename="+filename);//filename包含后缀
        //Servlet通过文件的地址，将文件的输入流读到Servlet中

        InputStream inputStream = getServletContext().getResourceAsStream("/res/"+filename);
        //通过输出流将刚才的输入流传给用户
        OutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024*10];
        int len = 0;
        while( (len=inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }

        outputStream.close();
        inputStream.close();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
