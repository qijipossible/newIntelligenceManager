package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static List<String> readSiteList(String filePath){
		File file  = new File(filePath);
		String line;
		List<String> lines = new ArrayList<String>();
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			while((line = reader.readLine()) != null){
				if(!line.equals(""))
				    lines.add(line);
			}
			reader.close();
		} catch(IOException e){
			e.printStackTrace();
		}

		return lines;
		
//		String[] siteList = new String[lines.size()];
//		siteList = lines.toArray(siteList);
//		return siteList;
	}
}
