package trial;
import java.sql.*;



public class DBManager {
	
	private Connection connection;
	
	/*
	 * �����ݿ�����
	 */
	public Connection getConnection() {
			try {
				
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 
				String url = "jdbc:sqlserver://localhost:1433;databaseName=EduSystem";
	 
				connection = DriverManager.getConnection(url, "sa", "123456");
	 
				
			} catch (Exception e) {
				System.out.println("���ݿ�����ʧ��");
				e.printStackTrace();
			}
			
			return connection;
	}
	
	/*
	 * �ͷ����ݿ�����
	 */
	public void releaseConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	
//	public static void main(String args[]) {
//		String sql = "SELECT * FROM SC";
//		DBManager dbManager = new DBManager();
//		Connection connection = dbManager.getConnection();
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet resultSet = stmt.executeQuery(sql);
//			
//			while (resultSet.next()) {
//				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
//			} 
//			System.out.println("********");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}