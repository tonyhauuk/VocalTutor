package deletefriend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDBConn {
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet result = null;
	
	public int result(String uic, String who) {
		int ret = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
			statement = conn.createStatement();
			
			ret = statement.executeUpdate("");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
					result = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
}
