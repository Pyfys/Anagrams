/**
 *
 *  @author Shkambara Dmytro S15163
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


public class Anagrams {
	ArrayList<String> keys = new ArrayList<String>();
	Map<String,ArrayList<String>> map = new LinkedHashMap<String,ArrayList<String>>();
	public Anagrams(String allWords) {
		FileReader file = null;
		BufferedReader bf = null;
		try {
			file = new FileReader(allWords);
			bf = new BufferedReader(file);
			String c = null;
			while((c = bf.readLine()) != null) {
				for(int i = 0;i<c.split(" ").length;i++) {
						char[]tmp = c.split(" ")[i].toCharArray();
						String stmp = "";
						Arrays.sort(tmp);
						for(int j = 0;j<tmp.length;j++) {
							stmp += tmp[j];
						}
						if(!(keys.contains(stmp))) keys.add(stmp);
						
						put(map,stmp,c.split(" ")[i]);
				}
			}
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<ArrayList> getSortedByAnQty() {
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		int max = map.get(keys.get(0)).size();
		for(int i = 1;i<map.size();i++) {
			if(map.get(keys.get(i)).size() > max) {
				max = map.get(keys.get(i)).size();
			}
		}
		for(int i = max; i>=0;i--) {
			for(int j = 0;j<keys.size();j++) {
				if(map.get(keys.get(j)).size() == i) {
					list.add(map.get(keys.get(j)));
				}
			}
		}
		return list;
	}
		  public static void put(Map<String, ArrayList<String>> map, String key, String value) {
		        if (map.get(key) == null) {
		            ArrayList<String> list = new ArrayList<String>();
		            list.add(value);
		            map.put(key, list);
		        } else {
		            map.get(key).add(value);
		        }
		    }

		public String getAnagramsFor(String next) {
			char[] tmp = next.toCharArray();
			Arrays.sort(tmp);
			String stmp = "";
			String result = "";
			for(int i = 0;i<tmp.length;i++) {
				stmp += tmp[i];
			}
			for(int i = 0;i<map.size();i++) {
				if(map.get(stmp) != null) {
					for(int j = 0;j<map.get(stmp).size();j++) {
						if(next.equals(map.get(stmp).get(0))) {
							map.get(stmp).remove(j--);
						}
					}
					result = next+": "+map.get(stmp);
				}
			}
			return result;
		}
}  
