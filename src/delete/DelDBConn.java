package delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DelDBConn {
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet result = null;

	public static String retStr(String uic, String cid) {
		String ret = null;
		int delSucc = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
			statement = conn.createStatement();
			
			result = statement.executeQuery("SELECT creater FROM `Course` WHERE course_id = '" + cid + "' "
					+ "AND creater = '" + uic + "' AND STATUS = '2'");
			while (result.next())
				ret = result.getString("creater");
			
			if (ret != null) {
				delSucc = statement.executeUpdate("DELETE FROM `Course`  WHERE course_id "
						+ "= '" + cid + "' and creater = '" + uic + "'");
				ret = String.valueOf(delSucc);
			}			
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
