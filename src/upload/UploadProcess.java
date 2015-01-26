package upload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadProcess {
	File tempPath, tempAva, tempIcon, tempAudio, tempVideo, tempPhoto, tempCert;
	private static final String SRV = "/var/www/html/vt/";
	
	public void getReq(HttpServletRequest request) throws ServletException, IOException {
		String uic = request.getParameter("uic");
		String level = request.getParameter("level");
		String type = request.getParameter("type");
		String cid = (request.getParameter("cid") != null) ? request.getParameter("cid") : "0";

		UploadDB udb = new UploadDB();
		try {
			String uploadPath= SRV + uic;
			String ava = uploadPath + "/ava/";
			String icon = uploadPath + "/icon/";
			String audio = uploadPath + "/audio/";
			String video = uploadPath + "/video/";
			String photo = uploadPath + "/photo/";
			String cert = uploadPath + "/cert/";
			
			String tempPath = SRV + "temp/" + uic;
			String avaTemp = tempPath + "/ava/";
			String iconTemp = tempPath + "/icon/";
			String audioTemp = tempPath + "/audio/";
			String videoTemp = tempPath + "/video/";
			String photoTemp = tempPath + "/photo/";
			String certTemp = tempPath + "/cert/";
			
			createPath(uploadPath, ava, icon, audio, video, photo, cert);
			createTempPath(tempPath, avaTemp, iconTemp, audioTemp, videoTemp, photoTemp, certTemp);
			
			// Avatar teacher
			if (type.equals("ava") && level.equals("s")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(1048576); // Temporary area 1MB
				factory.setRepository(tempAva);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 2); // Avatar 2MB
				
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> i = items.iterator();
				
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					String fileName = fi.getName();
					
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(ava, fullFile.getName());
						fi.write(savedFile);
					}
					udb.imgSql(type, level, ava + fileName, uic);
				}
			}
			// Avatar student
			else if (type.equals("ava") && level.equals("t")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(1048576); // Temporary area 1MB
				factory.setRepository(tempAva);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 2); // Avatar 2MB
				
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> i = items.iterator();
				
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					String fileName = fi.getName();
					
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(ava, fullFile.getName());
						fi.write(savedFile);
					}
					udb.imgSql(type, level, ava + fileName, uic);
				}
			}
			// Course icon
			else if (type.equals("icon") && level.equals("t") && !cid.equals("0")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(51200); // Temporary area 50KB
				factory.setRepository(tempIcon);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 5); // Icon 500KB
				
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> i = items.iterator();
				
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					String fileName = fi.getName();
					
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(icon, fullFile.getName());
						fi.write(savedFile);
					}
					udb.courseSql(type, cid, icon + fileName);
				}
			}
			// Course homework audio
			else if (type.equals("audio") && level.equals("t") && !cid.equals("0")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(1024 * 1024 * 5); // Temporary area 5MB
				factory.setRepository(tempAudio);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 10); // Audio 10MB
				
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> i = items.iterator();
				
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					String fileName = fi.getName();
					
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(audio, fullFile.getName());
						fi.write(savedFile);
					}
					udb.courseSql(type, cid, audio + fileName);
				}
			}
			// Course homework video
			else if (type.equals("video") && level.equals("t") && !cid.equals("0")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(1024 * 1024 * 10); // Temporary area 10MB
				factory.setRepository(tempVideo);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 20); // Video 20MB
				
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> i = items.iterator();
				
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					String fileName = fi.getName();
					
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(video, fullFile.getName());
						fi.write(savedFile);
					}
					udb.courseSql(type, cid, video + fileName);
				}
			}
			// Course homework photos
			else if (type.equals("photo") && level.equals("t") && !cid.equals("0")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(1024 * 1024 * 1); // Temporary area 1MB
				factory.setRepository(tempPhoto);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 2); // Photo 2MB
				
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> i = items.iterator();
				
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					String fileName = fi.getName();
					
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(photo, fullFile.getName());
						fi.write(savedFile);
					}
					udb.courseSql(type, cid, photo + fileName);
				}
			}
			// Teacher certificate url / picture
			else if (type.equals("cert") && level.equals("t")) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				
				factory.setSizeThreshold(1024 * 1024 * 1); // Temporary area 1MB
				factory.setRepository(tempCert);
				
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 2); // Certificate 2MB
				
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> i = items.iterator();
				
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					String fileName = fi.getName();
					
					if (fileName != null) {
						File fullFile = new File(fi.getName());
						File savedFile = new File(cert, fullFile.getName());
						fi.write(savedFile);
					}
					udb.imgSql(type, level, cert + fileName, uic);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private void createPath(String upload, String ava, String icon, String audio, String video, String photo, String cert) {
		File uploadPath = new File(upload);
		if (!uploadPath.exists())
			uploadPath.mkdir();
		
		File uploadava = new File(ava);
		if (!uploadava.exists())
			uploadava.mkdirs();
		
		File uploadIcon = new File(icon);
		if (!uploadIcon.exists())
			uploadIcon.mkdirs();
		
		File uploadAudio = new File(audio);
		if (!uploadAudio.exists())
			uploadAudio.mkdirs();
		
		File uploadVideo = new File(video);
		if (!uploadVideo.exists())
			uploadVideo.mkdirs();
		
		File uploadPhoto = new File(photo);
		if (!uploadPhoto.exists())
			uploadPhoto.mkdirs();
		
		File uploadCert = new File(cert);
		if (!uploadCert.exists())
			uploadCert.mkdirs();
	}
	
	private void createTempPath(String temp, String avaTemp, String iconTemp, String audioTemp, String videoTemp, String photoTemp, String certTemp) {
		File tempPath = new File(temp);
		if (!tempPath.exists())
			tempPath.mkdir();
		
		File tempAva = new File(avaTemp);
		if (!tempAva.exists())
			tempAva.mkdirs();
		
		File tempIcon = new File(iconTemp);
		if (!tempIcon.exists())
			tempIcon.mkdirs();
		
		File tempAudio = new File(audioTemp);
		if (!tempAudio.exists())
			tempAudio.mkdirs();
		
		File tempVideo = new File(videoTemp);
		if (!tempVideo.exists())
			tempVideo.mkdirs();
		
		File tempPhoto = new File(photoTemp);
		if (!tempPhoto.exists())
			tempPhoto.mkdirs();
		
		File tempCert = new File(certTemp);
		if (!tempCert.exists())
			tempCert.mkdirs();
	}
}
