package content;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContDBConn {
	
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet result = null;

	public static String result(String level, String cid) {
		String ret = null;
		String sp = "@!#";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
			statement = conn.createStatement();
			if (level.equals("t")) {
				result = statement.executeQuery("SELECT `course_id`, `course_name`, `img_url`, `course_intro`, `url`, "
						+ "`status`, `creater`, `create_time`, `course_location`, `start_time`, `duration`, `capacity`, "
						+ "`major_id`, `course_length`, `price`, `rating` FROM `Course` WHERE course_id = '" + cid + "'");
				
				String cn = null, img = null, ci = null, url = null, status = null, creater = null, ctime = null, loc = null, 
						stime = null, dur = null, capa = null, major = null, len = null, price = null, rating = null;
				while (result.next()) {
					cn = result.getString("course_name");
					img = result.getString("img_url");
					ci = result.getString("course_intro");
					url = result.getString("url");
					status = result.getString("status");
					creater = result.getString("creater");
					ctime = result.getString("create_time");
					loc = result.getString("course_location");
					stime = result.getString("start_time");
					dur = result.getString("duration");
					capa = result.getString("capacity");
					major = result.getString("major_id");
					len = result.getString("course_length");
					price = result.getString("price");
					rating = result.getString("rating");
				}
				
				ret = cn + sp + img + sp + ci + sp + url + sp + status + sp + creater + sp + ctime + sp + loc 
						+ sp + stime + sp + dur + sp + capa + sp + major + sp + len + sp + price + sp + rating;
			}
			else if (level.endsWith("s")) {
				result = statement.executeQuery("SELECT `student_id`, `creater`, `course_id`, `status`, `rating`, `select_time` "
						+ "FROM `Student_Course` WHERE course_id = '" + cid + "'");
				
				String sID = null, creater = null, scid = null, status = null, rating = null, selectTime = null;
				
				while (result.next()) {
					sID = result.getString("student_id");
					creater = result.getString("creater");
					scid = result.getString("course_id");
					status = result.getString("status");
					rating = result.getString("rating");
					selectTime = result.getString("select_time");
				}
				ret = sID + sp + creater + sp + scid + sp + status + sp + rating + sp + selectTime;
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
