import entity.Login;

import java.sql.*;

//模型层，用于处理登录
public class LoginDao {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PWD = "1992925hcw#";

    public int login(Login login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int flag = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载具体的驱动类
            connection = DriverManager.getConnection(URL, USERNAME, PWD);//连接数据库
            String sql = "select count(*) from login where name = ? and password = ?";
            String name = login.getUname();
            String pwd = login.getPwd();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pwd);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int result = rs.getInt(1);
                if (result > 0) {
                    flag = 1;
                } else {
                    flag = 0;
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            flag = -1;

        } catch (SQLException e) {
            e.printStackTrace();
            flag = -1;
        } finally {
            try {
                if (rs != null)
                    if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = -1;
            }
        }
        return flag;
    }

}
