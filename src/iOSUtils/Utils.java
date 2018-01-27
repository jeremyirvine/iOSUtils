package iOSUtils;

import java.io.*;
import java.net.*;

public class Utils {
	
// iOSUtils v1.0, Created by Jeremy Irvine 1/27/2018
// Scrapes https://www.theiphonewiki.com/ for Keys and IV's for a specific firmware on a device (if keys are present)
/*	public static void main(String[] args) {
                                                   Device Version
                                                         |
   				       Device Model -----------\         |        /---------------- Component Type (DeviceTree, KernelCache, RootFS, etc.) 
                                               |         |        |
        System.out.println(Utils.getKeyFor("iPad2,5", "8.4.1", KeyTypes.KERNELCACHE).getIv()); // ada137bf6aa705925d8ac5ada6025c11
		System.out.println(Utils.getKeyFor("iPad2,5", "8.4.1", KeyTypes.KERNELCACHE).getKey()); // 0155c713f32ee5fb9f18187e0b87d19ef38b9e56af121821264163627f894b05
																						|
																						\---------------------- Type of key (Key or IV)
	}
*/
	public static DeviceComponent getKeyFor(String device, String version, KeyTypes keyType) {
		String search_url = getDeviceURL(device, version);
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    String page = "";

	    try {
	        url = new URL(search_url);
	        is = url.openStream();
	        br = new BufferedReader(new InputStreamReader(is));

	        while ((line = br.readLine()) != null) {
	        	page += line + "__split__";
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	        }
	    }
	    String[] lines = page.split("__split__");
	    String ln = "";
	    String iv = "";
	    String key = "";
	    for(String s : lines) {
	    	if(s.contains(keyType.getValue() + "-iv")) {
	    		int t = (s.indexOf("<code id=\""+keyType.getValue()+"-iv\">"));
	    		int m = ("<code id=\"keypage-"+keyType.getValue()+"-iv\">").length() - 8;
	    		String tmp = s.substring(m+t, s.length());
	    		for(int i = 0; i < tmp.length(); i++) {
	    			if(!tmp.substring(i, i+1).equals("<")) {
	    				iv += tmp.substring(i, i+1);
	    			} else {
	    				break;
	    			}
	    		}
	    	} else if (s.contains(keyType.getValue() + "-key")) {
	    		int t = (s.indexOf("<code id=\""+keyType.getValue()+"-key\">"));
	    		int m = ("<code id=\"keypage-"+keyType.getValue()+"-key\">").length() - 8;
	    		String tmp = s.substring(m+t, s.length());
	    		for(int i = 0; i < tmp.length(); i++) {
	    			if(!tmp.substring(i, i+1).equals("<")) {
	    				key += tmp.substring(i, i+1);
	    			} else {
	    				break;
	    			}
	    		}
	    	}
	    }
//	    System.out.println(key);
	    if(iv == "") {
			return new DeviceComponent(key);
	    } else {
	    	return new DeviceComponent(iv, key);
	    }
	}
	
	private static String getDeviceURL(String device, String version) {
		String search_url = "https://www.theiphonewiki.com/wiki/Firmware_Keys/" + version.substring(0, 2).replace(".", "") + ".x";
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String line;
	    String page = "";

	    try {
	        url = new URL(search_url);
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));

	        while ((line = br.readLine()) != null) {
//	            System.out.println(line);
	        	page += line + "__split__";
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    String[] lines = page.split("__split__");
	    String ln = "";
	    for(String s : lines) {
	    	
//	    	System.out.println(s);
	    	if(s.contains(device)) 
	    	{
	    		if(s.toLowerCase().contains(version)) {
	    			ln = s;
	    			break;
	    		}
	    	}
	    }
	    ln = ln.substring(14, ln.length());
	    String ret = "";
	    for(int i = 0; i < ln.length(); i++) {
	    	if(!ln.substring(i, i + 1).equals("\"")) {
	    		ret += ln.substring(i, i + 1);
	    	} else {
	    		break;
	    	}
	    }
		return "https://www.theiphonewiki.com" + ret;
	}
	
 
}
