import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlTest {
    public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/transfusionsystem";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String sql = "select * from  auth_user";
            ResultSet rs = statement.executeQuery(sql);
            String job = null;
            String id = null;
            while(rs.next()){
                job = rs.getString("id");
                id = rs.getString("username");
                System.out.println(id + "\t" + job);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("Done");
        }
    }
}
