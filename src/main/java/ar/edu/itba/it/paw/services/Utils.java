package ar.edu.itba.it.paw.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Utils {

	public Map<String, String> flatten(Map<String, String[]> params) {
		
		Set<String> set = params.keySet();
		Map<String, String> ret = new HashMap<String, String>();
		
		for(String key: set) {
			ret.put(key, params.get(key)[0]);
		}
		
		return ret;
	}
	
}
