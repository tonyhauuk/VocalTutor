package create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateDBConn {
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet result = null, ins = null;
	
	public static String result(String cn, String ci, String status, String creater, Long cts, String loc, Long sts, String dur,
			String capa, String major, String len, String price) {
		String ret = null;
		int createSucc = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date(sts));
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
			statement = conn.createStatement();
			
			result = statement.executeQuery("SELECT `creater`,`start_time`FROM `Course` WHERE `creater`='" + creater + "'"
					+ " AND `start_time`='" + date + "'");
			String time = null;
			while (result.next())
				time = result.getString("start_time"); 
			if (time != null) {
				createSucc = statement.executeUpdate("");
			}else
				ret = "7";
			
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
