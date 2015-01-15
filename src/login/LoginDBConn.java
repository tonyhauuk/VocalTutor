package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDBConn {
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet tchrResult = null, studResult = null, tpwdResult = null, spwdResult = null, result = null;

	public static String results(String phone, String pwd) {
		Encrypt e = new Encrypt();
		String md5 = e.doEncrypt(phone, null);
		
		String retCode = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);
		
			statement = conn.createStatement();
			//userResult = statement.executeQuery("SELECT `phone` FROM `User_desc` WHERE `phone`=" + phone);
			tchrResult = statement.executeQuery("SELECT `teacher_id` from `Teacher_Info` where `teacher_id`='" + md5 + "'");
			
			String teacherId = null, studentId = null;
			while (tchrResult.next())
				teacherId = tchrResult.getString("teacher_id");
			
			// First, judge teacher id exist
			if (teacherId != null) {
				tpwdResult = statement.executeQuery(
						"SELECT `teacher_id` from `Teacher_Info` where `teacher_id`='" + md5 + "'and `password`='" + pwd +"'");
				String pwdNum = null;
				while (tpwdResult.next())
					pwdNum = tpwdResult.getString("teacher_id");
				// Second, judeg teacher password is correct
				if (pwdNum == null) 
					retCode = "1@#";
				if (pwdNum.equals(md5)) 
					retCode = pwdNum + "@#t";
					
			}
			// Second, judge student id exist
			else if (teacherId == null) {
				studResult = statement.executeQuery("SELECT `student_id` from `Student_Info` where `student_id`='" + md5 + "'");
				while (studResult.next())
					studentId = studResult.getString("student_id");
				
				// Thrid, judeg student id exist
				if (studentId != null) {
					spwdResult = statement.executeQuery(
							"SELECT student_id from Student_Info where student_id = '" + md5 + "'and `password`='" + pwd +"'");
					String pwdNum = null;
					while (spwdResult.next())
						pwdNum = spwdResult.getString("student_id");
					// Second, judeg teacher password is correct
					if (pwdNum == null) 
						retCode = "1@#";
					else if (pwdNum.equals(md5)) 
						retCode = pwdNum + "@#s";
				}
				else
					retCode = "5@#";
			}
			
			/*else if (phoneNum.equals(phone)) {
				//getPwd(pwd);
				pwdResult = statement.executeQuery("SELECT `password` FROM `User_desc`"
						+ " WHERE `password`='" + pwd + "'");
				String pwdNum = null;
				while (pwdResult.next())
					pwdNum = pwdResult.getString("password");
				
				// Judge user password
				if (pwdNum == null)
					retCode = "1@#";
				else if (pwdNum.equals(pwd)) {
					result = statement.executeQuery("SELECT `uic`, `level` FROM `User_desc`"
							+ " WHERE `phone`=" + phone + " AND `password`=" + "'" + pwd + "'");

					while(result.next()) {
						uic  = result.getString("uic");
						level = result.getString("level");
					}
					retCode = uic + "@#" + level;
				}				
			}	*/
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		finally {
			try {
				if (result != null) {
					result.close();
					result = null;
				}
				if (tpwdResult != null) {
					tpwdResult.close();
					tpwdResult = null;
				}
				if (spwdResult != null) {
					spwdResult.close();
					spwdResult = null;
				}
				if (tchrResult != null) {
					tchrResult.close();
					tchrResult = null;
				}
				if (studResult != null) {
					studResult.close();
					studResult = null;
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
		return retCode;
	}
}
