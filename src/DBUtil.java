import java.sql.*;

public class DBUtil {
    // 数据库连接地址
    private static String URL;
    // 用户名
    private static String USERNAME;
    // 密码
    private static String PASSWORD;
    // mysql的驱动类
    private static String DRIVER;

    // 使用静态块加载驱动程序
    static {

       // URL = "jdbc:mysql://localhost:3306/test";
        URL = "jdbc:mysql://119.27.163.193/test";
        USERNAME = "root";
        PASSWORD = "1992925hcw#";
        DRIVER = "com.mysql.jdbc.Driver";
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 定义一个获取数据库连接的方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return conn;
    }

    // 关闭数据库连接
    public static void close(ResultSet rs, PreparedStatement preparedStatement, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
