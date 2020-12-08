package com.example.spring.session.db2.demo;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpSessionUsingController {
	private static int setAttr_count = 0;

	@GetMapping("/sessionDemo")
	public static String sessionDemo(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession hs = req.getSession();
		hs.setAttribute("demoKey"+setAttr_count, "demoValue"+setAttr_count);
		setAttr_count++;

		String return_str = "HttpSession.setAttribute Called." + " Count:" + setAttr_count;
		System.out.println(return_str);
		
		return return_str;
	}
	
	@GetMapping("/getAllInsertedValues")
	public static String getAllInsertedValue(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession hs = req.getSession();
		if(hs==null) {
			return "no session exists.";
		}
		
		Enumeration<String> attributeNames = hs.getAttributeNames();
		
		String attributeName = "";
		
		StringBuilder keyAndValues = new StringBuilder();	
		
		keyAndValues.append("{");
		while(attributeNames.hasMoreElements()) {
			attributeName = attributeNames.nextElement();
			Object lastValue = hs.getAttribute(attributeName);
			
			keyAndValues.append( "\"" + attributeName + "\"" + ":" + "\"" + lastValue + "\"" );
			
			if(attributeNames.hasMoreElements()) {
				keyAndValues.append(",");
			}else {		
			}			
		}
		keyAndValues.append("}");
		
		System.out.println(keyAndValues);
		return keyAndValues.toString();
	}

}
