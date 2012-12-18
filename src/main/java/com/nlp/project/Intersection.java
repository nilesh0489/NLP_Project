package com.nlp.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Intersection {
	
//	static ArrayList<Map<String, Double>> list = new ArrayList<Map<String,Double>>();
	
	public static Map<String, Double> findCommonElements(ArrayList<Map<String, Double>> list, boolean flag) {		
		Map<String, Double> oneClass = new HashMap<String, Double>();
		Iterator<String> it;
		for(int i = 0; i < list.size(); i++) {
			if(i == 0) {
				it = list.get(0).keySet().iterator();
				while(it.hasNext()) {
					String temp = it.next();
					oneClass.put(temp, list.get(0).get(temp));
				}				
			}
			it = oneClass.keySet().iterator();
				if(flag) {
					while(it.hasNext()) {
						String key = it.next();
						if(list.get(i).containsKey(key)) {
							oneClass.put(key, 1.0);
						}
					}
			}
		}
		
		return oneClass;
	}
		
		public static Map<String, Double> findCommonElements(Map<String, Double> a, Map<String, Double> b) {		
			Map<String, Double> oneClass = new HashMap<String, Double>();
			Iterator<String> it = b.keySet().iterator();
			while(it.hasNext()) {
				String temp = it.next();
				if(!a.containsKey(temp)) {
					System.out.println(temp);
				}
			}	
		return oneClass;
	}

}
