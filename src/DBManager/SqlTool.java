package DBManager;
import java.sql.*;
  
public class SqlTool { 
  //========数据库 
  private Connection ct = null; 
  private PreparedStatement ps = null; 
  private ResultSet rs = null; 
  private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
  private String url = "jdbc:sqlserver://127.0.0.1:1433;database=EduSystem"; 
  private String user = "sa"; 
  private String passwd = "123456"; 
    
  //========查询 
  public ResultSet queryExecute(String sql, String []paras) 
  { 
    try { 
      //========1、加载驱动 
      Class.forName(driver); 
      //========2、连接 
      ct = DriverManager.getConnection(url, user, passwd); 
      //========3、创建PreparedStatement 
      ps = ct.prepareStatement(sql); 
      //========4、给问号赋值 
      if(paras != null) { 
        for(int i = 0; i < paras.length; i++) { 
          ps.setString(i + 1, paras[i]); 
        } 
      } 
      //========5、执行 
      rs = ps.executeQuery(); 
    } catch (Exception e) { 
      // TODO: handle exception 
      e.printStackTrace(); 
    } finally { 
      //this.close(); 
    } 
    //========返回值 
    return rs; 
  } 
    
  //========增删改 
  public boolean cudExecute(String sql, String []paras) 
  { 
    boolean b = true; 
    try { 
      //========1、加载驱动 
      Class.forName(driver); 
      //========2、连接 
      ct = DriverManager.getConnection(url, user, passwd); 
      //========3、创建PreparedStatement 
      ps = ct.prepareStatement(sql); 
      //========4、给问号赋值 
      for(int i = 0; i < paras.length; i++) { 
        ps.setString(i + 1, paras[i]); 
      } 
      //========5、执行 
      if(ps.executeUpdate() != 1) b = false; 
    } catch (Exception e) { 
      // TODO: handle exception 
      b = false; 
      e.printStackTrace(); 
    } finally { 
      this.close(); 
    } 
    //========返回值 
    return b; 
  } 
    
  //========关闭资源 
  public void close() 
  { 
    try { 
      if(rs!=null) rs.close(); 
      if(ps!=null) ps.close(); 
      if(ct!=null) ct.close(); 
    } catch (Exception e2) { 
      // TODO: handle exception 
      e2.printStackTrace(); 
    } 
  } 
}
