package upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			UploadProcess up = new UploadProcess();
			up.getReq(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
