import entity.Login;

import java.io.IOException;
//控制器层
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        Login login = new Login(name,pwd);
        LoginDao loginDao = new LoginDao();
        int result = loginDao.login(login);
        if(result==1){
            System.out.println("登录成功");
            request.getRequestDispatcher("welcome.jsp").forward(request,response);
        }else if(result==0){
            System.out.println("用户名密码输入错误");
            response.sendRedirect("login.jsp");
        }else if(result==-1){
            System.out.println("系统报错");
            response.sendRedirect("login.jsp");
        }


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    doPost(request,response);
    }
}
