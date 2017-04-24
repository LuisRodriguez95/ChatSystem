package model;

import java.util.HashMap;

public class PasswordDTB {

	private static HashMap<String, String> hmap = new HashMap<String, String>();

    public static HashMap<String, String> getHmap() {
		return hmap;
	}
 
    static {
    	  hmap.put("Luis", "123");
          hmap.put("qsdqsd", "123");
          hmap.put("qsdqs", "123");
    }
}
