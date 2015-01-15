package register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String pwd = request.getParameter("pwd");
		String level = request.getParameter("level");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String location = request.getParameter("location");
		String bio = request.getParameter("bio");
		String major = request.getParameter("major");
		String ts = request.getParameter("ts");
		
		// Return XML
		response.setCharacterEncoding("UTF-8");
		XMLWriter writer = null;
		Document doc = null;
		
		// User register
		if (phone != null && pwd != null && level != null && name != null && age != null && location != null && bio != null && major != null 
				&& gender != null && ts != null && !phone.equals("") && !pwd.equals("") && !level.equals("") && !name.equals("") && 
				!age.equals("") && !location.equals("") && !bio.equals("") && !major.equals("") && !gender.equals("") && !ts.equals("")) {
			try {			
				doc = RegXML.buildXML(phone, pwd, level, name, age, location, bio, major, gender, ts);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.close();
		}
		else if (phone == null || pwd == null || level == null || name == null || age == null || location == null || bio == null 
				|| major == null || gender == null || phone.equals("") || pwd.equals("") || level.equals("") || name.equals("") 
				|| age.equals("") || location.equals("") || bio.equals("") || major.equals("") || gender.equals("")) {
			doc = RegErrXML.getErrCode(2);
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.close();
		}
		
		// Teacher register
//		else if (level.equals("t")) {
//			if (phone != null && pwd != null && level != null && name != null && age != null && location != null && bio != null && major != null 
//					&& gender!= null && !phone.equals("") && !pwd.equals("") && !level.equals("") && !name.equals("") && !age.equals("") && !location.equals("")
//					&& !bio.equals("") && !major.equals("") && !gender.equals("")) {
//				try {			
//					doc = RegXML.buildXMLT(phone, pwd, level, name, age, location, bio, major, gender, ts);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				writer = new XMLWriter(response.getWriter());
//				writer.write(doc);
//				writer.close();
//			}
//			else if (phone == null || pwd == null || level == null || 
//					phone.equals("") || pwd.equals("") || level.equals("")) {
//				doc = RegErrXML.getErrCode(2);
//				writer = new XMLWriter(response.getWriter());
//				writer.write(doc);
//				writer.close();
//			}
//		}
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
