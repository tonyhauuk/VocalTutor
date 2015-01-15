package upload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class HttpUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	File tempPathFile;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userID = request.getParameter("url");
			String uploadPath= "/tmp/test/" + userID;
			String tempPath = "/tmp/test/temp/" + userID;
			createPath(uploadPath, tempPath);
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			factory.setRepository(tempPathFile);
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> i = items.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				String fileName = fi.getName();
				if (fileName != null) {
					File fullFile = new File(fi.getName());
					File savedFile = new File(uploadPath, fullFile.getName());
					fi.write(savedFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createPath(String uploadPath, String tempPath) {		
		File uploadFile = new File(uploadPath);
		if (!uploadFile.exists())
			uploadFile.mkdirs();
		
		File tempPathFile = new File(tempPath);
		if (!tempPathFile.exists())
			tempPathFile.mkdirs();
	}
}
