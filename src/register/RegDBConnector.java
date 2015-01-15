package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegDBConnector {
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet result = null;

	public static String userResults(String phone, String pwd, String md5, String level, String name, 
			String age, String location, String bio, String major, String gender, String date) {
		String retCode = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
		
			statement = conn.createStatement();
			//result = statement.executeQuery("SELECT `phone` FROM `User_desc` WHERE `phone`='" + phone + "'");
			result = statement.executeQuery("select count(*) from Teacher_Info t1, Student_Info t2 "
					+ "where t1.teacher_id = '" + md5 + "' or t2.student_id = '" + md5 + "'");
			
			
			while(result.next())
				retCode = String.valueOf(result.getInt(1));
				//retCode  = result.getString("phone");
			
			if (retCode.equals("0") /*|| retCode == null || retCode.equals("")*/) {
//				int returnID = statement.executeUpdate("INSERT INTO `User_desc`(`phone`, `password`, `uic`, `level`)"
//						+ " VALUES ('" + phone + "','" + pwd + "','" + md5 + "','" + level + "')");
				if (level.equals("t")) {
					int retID = statement.executeUpdate("INSERT INTO `Teacher_Info`(`name`, `gender`, `img_url`, `age`, `password`, `location`, "
							+ "`phone`, `my_bio`, `major_id`, `tchr_cert_url`, `teacher_id`, `reg_date`) VALUES ('" + name +"','" + gender + "','"
							+ "" + "','" + age + "','" + pwd + "','" + location + "','" + phone + "','" + bio + "','" + major + "','" + "" + "','"
							+ md5 + "','" + date + "')");
					retCode = String.valueOf(retID);
				}
				else if (level.equals("s")) {
					int retID = statement.executeUpdate("INSERT INTO `Student_Info`(`name`, `gender`, `img_url`, `age`, `password`, `location`, "
							+ "`phone`, `my_bio`, `major_id`, `student_id`, `reg_date`) VALUES ('" + name + "','" + gender + "','" + "" + "','" +
							age + "','" + pwd + "','" + location + "','" + phone + "','" + bio + "','" + major + "','" + md5 + "','" + date + "')");
					retCode = String.valueOf(retID);
				}
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
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
		return retCode;
	}

	public static String teacherResults(String phone, String pwd1, String md5, String lev, String name, 
			String age, String location, String bio, String major, String gender, String date) {
		String retCode = null;
		try {
			String level = null;
			if (lev.equals("t"))
				level = "1";
			else if (lev.equals("s"))
				level = "2";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
		
			statement = conn.createStatement();
			result = statement.executeQuery("SELECT `phone` FROM `User_desc` WHERE `phone`='" + phone + "'");

			while(result.next())
				retCode  = result.getString("phone");
			
			if (retCode == null || retCode.equals("")) {
				int returnID = statement.executeUpdate("INSERT INTO `User_desc`(`phone`, `password`, `uic`, `level`)"
						+ " VALUES ('" + phone + "','" + pwd1 + "','" + md5 + "','" + level + "')");
				retCode = String.valueOf(returnID);
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
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
		return retCode;
	}
}
