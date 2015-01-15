package profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfDBConn {
	private static final String URL = "jdbc:mysql://localhost:3306/cwl";
	private static final String USR = "java_mysql";
	private static final String PWD = "1d2f5cbbd94430827c12659a68e4a671";

	static Connection conn = null;
	static Statement statement = null;
	static ResultSet result = null;

	public static String result(String uic, String level) {
		String set = "";
		String sp = "@!#";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USR, PWD);

			statement = conn.createStatement();
			if (level.equals("s")) {
				result = statement.executeQuery("SELECT `name`, `gender`, `img_url`, `age`, `password`, `location`, `phone`, `my_bio`, `major_id`, "
						+ "`student_id`, `reg_date` FROM `Student_Info` WHERE student_id = '" + uic + "'");
				
				String name = null, gender = null, img = null, age = null, pwd = null, location = null, 
						phone = null, bio = null, majorID = null, studID = null, regDate = null;
				while (result.next()) {
					name = result.getString("name");
					gender = result.getString("gender");
					img = result.getString("img_url");
					age = result.getString("age");
					pwd = result.getString("password");
					location = result.getString("location");
					phone = result.getString("phone");
					bio = result.getString("my_bio");
					majorID = result.getString("major_id");
					studID = result.getString("student_id");
					regDate = result.getString("reg_date");
				}
				set = name + sp + gender + sp + img + sp + age + sp + pwd + sp + location + sp 
						+ phone + sp + bio + sp + majorID + sp +  studID + sp + regDate;
			}
			else if (level.equals("t")) {
				result = statement.executeQuery("SELECT `name`, `gender`, `img_url`, `age`, `password`, `location`, `phone`, `my_bio`, "
						+ "`major_id`, `tchr_cert_url`, `teacher_id`, `reg_date` FROM `Teacher_Info` WHERE teacher_id = '" + uic + "'");
				
				String name = null, gender = null, img = null, age = null, pwd = null, location = null, 
						phone = null, bio = null, majorID = null, tchrCret = null, tchrID = null, regDate = null;
				while (result.next()) {
					name = result.getString("name");
					gender = result.getString("gender");
					img = result.getString("img_url");
					age = result.getString("age");
					pwd = result.getString("password");
					location = result.getString("location");
					phone = result.getString("phone");
					bio = result.getString("my_bio");
					majorID = result.getString("major_id");
					tchrCret = result.getString("tchr_cert_url");
					tchrID = result.getString("teacher_id");
					regDate = result.getString("reg_date");
				}
				set = name + sp + gender + sp + img + sp + age + sp + pwd + sp + location + sp 
						+ phone + sp + bio + sp + majorID + sp +  tchrID + sp + regDate + sp + tchrCret;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return set;
	}
}
