package com.information.platform.utils;

import com.information.platform.exception.CustomException;

public class HtmlUtils {
              
	public static String EncodeHtml(String strUserInput)	{
		
	if (StringUtil.isBlank(strUserInput)){
		throw new CustomException("strUserInput is null" );
	}
	strUserInput = strUserInput.replace("&", "&amp;");
	strUserInput = strUserInput.replace("'", "''");
	strUserInput = strUserInput.replace("\"", "&quot;");
	strUserInput = strUserInput.replace(" ", "&nbsp;");
	strUserInput = strUserInput.replace("<", "&lt;");
	strUserInput = strUserInput.replace(">", "&gt;");
	strUserInput = strUserInput.replace("\n", "<br>");
	return strUserInput.trim();
	}
}
