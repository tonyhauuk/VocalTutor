package register;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class RegXML {
	// Student register
	public static Document buildXML(String phone, String pwd, String level, String name, String age, String location, 
			String bio, String major, String gender, String ts) throws Exception { 
		int pLen = phone.length();
		int pChar = Integer.parseInt(phone.substring(0, 1));
		String regex = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,})$";
		boolean isPwd = pwd.matches(regex);
		long time = Long.parseLong(ts);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf1.format(new Date(time));
		
		Document doc = null;
		String retCode = null;
		
		if (pLen == 11 && pChar == 1 && isPwd) {
			Encrypt e = new Encrypt();
			String md5 = e.doEncrypt(phone, null);
			retCode = RegDBConnector.userResults(phone, pwd, md5, level, name, age, location, bio, major, gender, date);
			
			if (retCode.equals("1")) { // if return id = 1, is success
				doc = DocumentHelper.createDocument();
				
				Element root = doc.addElement("reg");
				Element errno = root.addElement("errno");
				errno.setText("0");
				
				Element msg = root.addElement("msg");
				msg.setText("成功");
				
				Element uic = root.addElement("uic");
				uic.setText(md5);
			}
			else if (!retCode.equals("1")) // if return id == phone, not success
				doc = RegPhoneErr.getErrCode(4, phone);
		}
		else if (phone.equals("") || pwd.equals("") || level.equals(""))
			doc = RegErrXML.getErrCode(2);
		else if (pLen != 11 || pChar != 1 || pwd.length() < 6 || isPwd == false || !level.equals("s") || !level.equals("t"))
			doc = RegErrXML.getErrCode(3);
		
		return doc;
	}
}
