package profile;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ProfXML {

	public static Document buildXML(String uic, String level) {
		Document doc = null;
		if (uic.length() == 32 && level.equals("s") || level.equals("t")) {
			
			if (level.equals("s")) {
				String set = ProfDBConn.result(uic, level).replaceAll("null", "");
				String[] sets = set.split("@!#");
				
				String sName = sets[0];
				String sGender = sets[1];
				String sImg = sets[2];
				String sAge = sets[3];
				String sPwd = sets[4];
				String sLoc = sets[5];
				String sPhone = sets[6];
				String sBio = sets[7];
				String sMajor = sets[8];
				String sStudID = sets[9];
				String sReg = sets[10];
				
				doc = DocumentHelper.createDocument();
				Element root = doc.addElement("profile");
				Element errno = root.addElement("errno");
				errno.setText("0");
				Element msg = root.addElement("msg");
				msg.setText("成功");
				Element user = root.addElement("user"); // New node : user
				Element name = user.addElement("name");
				name.setText(sName);
				Element gender = user.addElement("gender");
				gender.setText(sGender);
				Element img = user.addElement("img_url");
				img.setText(sImg);
				Element age = user.addElement("age");
				age.setText(sAge);
				Element pwd = user.addElement("password");
				pwd.setText(sPwd);
				Element loc = user.addElement("location");
				loc.setText(sLoc);
				Element phone = user.addElement("phone");
				phone.setText(sPhone);
				Element bio = user.addElement("bio");
				bio.setText(sBio);
				Element major = user.addElement("major_id");
				major.setText(sMajor);
				Element sID = user.addElement("student_id");
				sID.setText(sStudID);
				Element regDate = user.addElement("reg_date");
				regDate.setText(sReg);				
			}
			else if (level.equals("t")) {
				String set = ProfDBConn.result(uic, level).replaceAll("null", " ");
				String[] sets = set.split("@!#");
				
				String tName = sets[0];
				String tGender = sets[1];
				String tImg = sets[2].trim();
				String tAge = sets[3];
				String tPwd = sets[4];
				String tLoc = sets[5];
				String tPhone = sets[6];
				String tBio = sets[7];
				String tMajor = sets[8];
				String tTchrID = sets[9];
				String tReg = sets[10];
				String tTchrCert = sets[11].trim();
				
				doc = DocumentHelper.createDocument();
				Element root = doc.addElement("profile");
				Element errno = root.addElement("errno");
				errno.setText("0");
				Element msg = root.addElement("msg");
				msg.setText("成功");
				Element user = root.addElement("user"); // New node : user
				Element name = user.addElement("name");
				name.setText(tName);
				Element gender = user.addElement("gender");
				gender.setText(tGender);
				Element img = user.addElement("img_url");
				img.setText(tImg);
				Element age = user.addElement("age");
				age.setText(tAge);
				Element pwd = user.addElement("password");
				pwd.setText(tPwd);
				Element loc = user.addElement("location");
				loc.setText(tLoc);
				Element phone = user.addElement("phone");
				phone.setText(tPhone);
				Element bio = user.addElement("bio");
				bio.setText(tBio);
				Element major = user.addElement("major_id");
				major.setText(tMajor);
				Element tchrCert = user.addElement("tchr_cert_url");
				tchrCert.setText(tTchrCert);
				Element sID = user.addElement("teacher_id");
				sID.setText(tTchrID);
				Element regDate = user.addElement("reg_date");
				regDate.setText(tReg);
					
			}
		}
		else if (uic.length() != 32 && !level.equals("s") || !level.equals("t"))
			doc = ProfErrXML.getErrCode(3);
		
		return doc;
	}
}
