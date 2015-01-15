package profile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uic = request.getParameter("uic");
		String level = request.getParameter("level");
		
		// Return XML
		response.setCharacterEncoding("UTF-8");
		XMLWriter writer = null;
		Document doc = null;
		
		if (uic != null && !uic.equals("") && level != null && !level.equals("")) {
			try {
				doc = ProfXML.buildXML(uic, level);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.flush();
			writer.close();
		}
		else if (uic == null || uic.equals("") || level == null || level.equals("")) {
			doc = ProfErrXML.getErrCode(2);
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.flush();
			writer.close();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
