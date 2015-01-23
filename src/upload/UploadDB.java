package upload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UploadDB {
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	
	public void imgSql(String type, String level, String path, String uic) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
			statement = conn.createStatement();
			
			// a7c7243c9ebb77cbcc987a87d3a8642d
			if (type.equals("ava") && level.equals("s"))
				statement.executeUpdate("UPDATE `Student_Info` SET `img_url` = '" + path + "' WHERE `student_id` = '" + uic + "'");
			else if (type.equals("ava") && level.equals("t"))
				statement.executeUpdate("UPDATE `Teacher_Info` SET `img_url` = '" + path + "' WHERE `teacher_id` = '" + uic + "'");
			else if (type.equals("cert"))
				statement.executeUpdate("UPDATE `Teacher_Info` SET `tchr_cert_url` = '" + path + "' WHERE `teacher_id` = '" + uic + "'");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
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
	}
	
	public void courseSql(String type, String cid, String path) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
			statement = conn.createStatement();
			
			if (type.equals("audio"))
				statement.executeUpdate("UPDATE `Course` SET `url`='" + path + "' WHERE `course_id`='" + cid + "'");
			else if (type.equals("video"))
				statement.executeUpdate("UPDATE `Course` SET `url`='" + path + "' WHERE `course_id`='" + cid + "'");
			else if (type.equals("photo"))
				statement.executeUpdate("UPDATE `Course` SET `url`='" + path + "' WHERE `course_id`='" + cid + "'");
			else if (type.equals("icon"))
				statement.executeUpdate("UPDATE `Course` SET `img_url`='" + path + "' WHERE `course_id`='" + cid + "'");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
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
	}
}
