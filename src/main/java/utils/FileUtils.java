package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public static List<String> readSiteList(String filePath){
		File file  = new File(filePath);
		String line;
		List<String> lines = new ArrayList<String>();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file)); 
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
