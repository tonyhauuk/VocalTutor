package login;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class LoginXML {
	protected static Document buildXML(String phone, String pwd) throws Exception {
		int pLen = phone.length();
		int pChar = Integer.parseInt(phone.substring(0, 1));
		
		Document doc = null;
		
		if (pLen == 11 && pChar == 1) {
			String uni = LoginDBConn.results(phone, pwd);
			String[] spuni = uni.split("@#");
			
			// Return array length > 1, sucess
			if (spuni.length == 2) {
				String uicCode = spuni[0];
				String levelCode = spuni[1];
				
				doc = DocumentHelper.createDocument();
				Element root = doc.addElement("login");
				
				Element errno = root.addElement("errno");
				errno.setText("0");
				
				Element msg = root.addElement("msg");
				msg.setText("成功");
				
				Element uic = root.addElement("uic");
				uic.setText(uicCode);
				
				Element level = root.addElement("level");
				level.setText(levelCode);
			}
			else if (spuni.length == 1) {
				// User not register 
				if (spuni[0].equals("5"))
					doc = LoginErrXML.getErrCode(5);
				// User password incorrect 
				else if (spuni[0].equals("1"))
					doc = LoginErrXML.getErrCode(1);
			}
				
		}
		else if (phone.equals("") || pwd.equals(""))
			doc = LoginErrXML.getErrCode(2);
		else if (pLen != 11 || pChar != 1 || pwd.length() < 6)
			doc = LoginErrXML.getErrCode(3);
		
		return doc;
	}
}
