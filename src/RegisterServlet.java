import entity.Login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        Login login = new Login(name,pwd);
        LoginDao loginDao = new LoginDao();
        int result = loginDao.regist(login);
        if(result==1){
            System.out.println("注册成功");
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }else if(result==0){
            System.out.println("用户已经存在");
            response.sendRedirect("login.jsp");
        }else if(result==-1){
            System.out.println("系统报错");
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
