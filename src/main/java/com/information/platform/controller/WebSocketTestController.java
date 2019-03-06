package com.information.platform.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.information.platform.bean.ResponseBean;
import com.information.platform.websocket.WebsocketServer;

@RestController
@RequestMapping("/socket")
public class WebSocketTestController {
	
	//推送数据接口  
	 @RequestMapping("/push/{cid}") 
	 public ResponseBean pushToWeb(@PathVariable String cid,String message) {
		 try { 
			 WebsocketServer.sendInfo(message,cid);
			 } catch (IOException e)  {
				 e.printStackTrace(); 
		      } 
		 return new ResponseBean(true, 200, "send message successfully",cid); 
	 } 
}
