import entity.Login;

import java.sql.*;

//模型层，用于处理登录
public class LoginDao {

    public int login(Login login) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int flag = -1;
        try {
            /**
             Class.forName("com.mysql.jdbc.Driver");//加载具体的驱动类
             connection = DriverManager.getConnection(URL, USERNAME, PWD);//连接数据库
             String sql = "select count(*) from login where name = ? and password = ?";
             */
            connection = DBUtil.getConnection();
            String sql = "select count(*) from login where name = ? and password = ?";
            String name = login.getName();
            String pwd = login.getPassword();
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

        } catch (SQLException e) {
            e.printStackTrace();
            flag = -1;
        } finally {
            DBUtil.close(rs,preparedStatement,connection);
        }
        return flag;
    }

    public int regist(Login login){
        Connection connection = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet rs = null;
                    int flag = -1;


                    try {
                        connection = DBUtil.getConnection();
                        //查询是否存在
                        String sql = "select count(*) from login where name = ?";
                        String name = login.getName();
                        String pwd = login.getPassword();
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1,name);
                        rs = preparedStatement.executeQuery();
                        if(rs.next()){
                            int result = rs.getInt(1);
                            if(result>=1){
                                flag = 0;
                                return flag;

                }
                String sql1 = "INSERT INTO login(name,password) values(?,?)";
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,pwd);
                int i  = preparedStatement.executeUpdate();
                if(i>0){
                    flag = 1;
                    return flag;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            flag = -1;
        }
        return flag;
    }

}
