package com.csc.ignasia.selenium.keywords;

import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import com.csc.ignasia.selenium.HtmlObject;
import com.csc.ignasia.selenium.KeywordResult;
import com.csc.ignasia.selenium.keywords.KeywordHandler;
import com.csc.fsg.ignasia.model.common.ComponentModel;

s

/**
Keyword Id = 'Sample'
Please see IGNASIA Java Docs at
http://ignasia-javadocs.s3-website-us-east-1.amazonaws.com
to know more about the referenced classes
**/


@Service("GetDBValue")
public class GetDBValueKeywordHandler implements KeywordHandler {

	public KeywordResult process(WebDriver driver, HtmlObject htmlObject, ComponentModel componentModel, Map testData) {
		KeywordResult result = new KeywordResult();
		
		try {

			
			 
			String tablename = testData.get(componentModel.getParam1()).toString();
		if (tablename == null || tablename.equals("")) {
			result.setResult(true);
			result.setMessage("GetDBValue skipped");
			return result;
		}
		else
		{
		String columnname = testData.get(componentModel.getParam2()).toString();
		String selector = testData.get(componentModel.getParam3()).toString();
		
			  
			/* TEST DATA 
			            String tablename = "tablename";
			            String columnname = "columnname";
			            String selector = "selector";
			*/			
			            URL url = new URL("http://localhost:81/Printer");
			            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			            con.setRequestMethod("GET");
			            con.setRequestProperty("User-Agent", "USER_AGENT");
			            
			            con.setRequestProperty("tablename" ,tablename);
			            con.setRequestProperty("columnname", columnname);
			            con.setRequestProperty("selector",selector);
			            
			            /*
			            ((Object) con).setRequestHeader("tablename" ,tablename);
			            con.setRequestHeader("columnname", columnname);
			            con.setRequestHeader("selector",selector);
			            */
			            
			            int rc = con.getResponseCode();
			            
			            String s = con.getResponseMessage();
			           	
			            //String table= con.getHeaderField("result");
			            String msg= con.getHeaderField("msg");
			            Boolean flag = Boolean.parseBoolean(con.getHeaderField("result"));
			          	
			          	
			            if(rc==200)
			            {		
			            	result.setResult(flag);
							result.setMessage(msg);	
			            }else
			            {
							result.setResult(false);
							result.setMessage("The request to the servlet couldnt be completed. Code returned:" + rc);
			            }
			            
			            
			        } 


	}catch (Exception e) {
		result.setResult(false);
		result.setMessage("Exception occured. Message: " + e.getMessage());
    }

	}
}