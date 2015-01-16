package content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.XMLWriter;

public class Content extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
		String cid = request.getParameter("cid");
		String level = request.getParameter("level");
		
		response.setCharacterEncoding("UTF-8");
		XMLWriter writer = null;
		Document doc = null;
		
		if (!cid.equals("") && !level.equals("") && cid != null && level != null) {
			try {
				doc = ContXML.buildXML(cid, level);
			} catch (Exception e) {
				e.printStackTrace();
			}
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.flush();
			writer.close();
		}
		else if (cid.equals("") || level.equals("") || cid == null || level == null) {
			doc = ContErrXML.getErrCode(2);
			writer = new XMLWriter(response.getWriter());
			writer.write(doc);
			writer.flush();
			writer.close();
		}
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}

