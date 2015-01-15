package article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticleDBConn {

	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet tResult = null, sResult = null;

	public static String obtainInfos(String uic, String level, int amount) throws SQLException, ClassNotFoundException, NullPointerException {
		int mount = amount - 1;
		String ret = null;
		String sp = "@!#";
		
		try {		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
			statement = conn.createStatement();
			
			if (level.equals("t")) {
				tResult = statement.executeQuery("SELECT `course_id`, `course_name`, `img_url`, `course_intro`, `url`, `status`, `creater`, "
						+ "`create_time`, `course_location`, `start_time`, `duration`, `capacity`, `major_id`, `course_length`, `price`, `rating`"
						+ " FROM `Course` WHERE creater = '" + uic + "' and status = '1' ORDER BY start_time LIMIT " 
						+ String.valueOf(mount) + ", " + String.valueOf(amount));
				
				String cid = null, cn = null, ci = null, creater = null, ctime = null, loc = null, stime = null, dur = null, capa = null, 
						major = null, len = null, price = null, status = null, rate = null, img = null, url = null;
				
				while (tResult.next()) {
					cid = tResult.getString("course_id");
					cn = tResult.getString("course_name");
					img = tResult.getString("img_url");
					ci = tResult.getString("course_intro");
					url = tResult.getString("url");
					status = tResult.getString("status");
					creater = tResult.getString("creater");
					ctime = tResult.getString("create_time");
					loc = tResult.getString("course_location");
					stime = tResult.getString("start_time");
					dur = tResult.getString("duration");
					capa = tResult.getString("capacity");
					major = tResult.getString("major_id");
					len = tResult.getString("course_length");
					price = tResult.getString("price");
					rate = tResult.getString("rating");
					
				}
				ret = cid + sp + cn + sp + img + sp + ci + sp + url + sp + status + sp + creater + sp + ctime + sp + loc + sp + stime
						 + sp + dur + sp + capa + sp + major + sp + len + sp + price + sp + rate;
			}
			else if (level.equals("s")) {
				sResult = statement.executeQuery("SELECT `student_id`, `creater`, `course_id`, `status`, `rating`, `select_time` FROM `Student_Course` "
						+ "WHERE student_id = '" + uic + "' ORDER BY select_time LIMIT " + mount + ", " + amount);
				
				String sID = null, creater = null, cid = null, status = null, rating = null, sltime = null;
				
				while (sResult.next()) {
					sID = sResult.getString("student_id");
					creater = sResult.getString("creater");
					cid = sResult.getString("course_id");
					status = sResult.getString("status");
					rating = sResult.getString("rating");
					sltime = sResult.getString("select_time");
				}
				ret = sID + sp + creater + sp + cid + sp + status + sp + rating + sp + sltime;
			}	
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		finally {
			try {
				if (tResult != null) {
					tResult.close();
					tResult = null;
				}
				if (sResult != null) {
					sResult.close();
					sResult = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}	
		return ret;
	}
}