package com.information.platform.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
     private static final long EXPIRE_TIME=12*3600*1000;
     
    /**
     * 验证token是否正确
     * @param token 密钥
     * @param username 用户名
     * @param secret 用户密码
     * @return
     */
     public static  boolean verify(String token,String username,String secret){
    	 try {
    		 String string  = username+secret;
    		 //以用户名+密码的形式加密
			Algorithm algorithm = Algorithm.HMAC256(string);
    		 JWTVerifier verifier = JWT.require(algorithm)
    				 .withClaim("username", username)
    				 .build();
    		 DecodedJWT jwt = verifier.verify(token);
    		 System.out.println(jwt);
    		 return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
     }
     
     
     /** *获得token中的信息无需secret解密也能获得 
      * @return token中包含的用户名
      * 
      *  */ 
     public static String getUsername(String token) { 
    	 try { 
    		 DecodedJWT jwt = JWT.decode(token); 
    	    return jwt.getClaim("username").asString(); 
    	    } 
    	 catch (JWTDecodeException e)
    	 {
    		 return null; 
    		 } } 
     
     
     /**  生成签名,5min后过期 *
      * @param username 用户名
        * @param secret 用户的密码
        * @return 加密的token
        *  */ 
     public static String sign(String username, String secret) {
    	 try { 
    		 Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME); 
    	 Algorithm algorithm = Algorithm.HMAC256(username+secret); 
    	 // 附带username信息 
    	 return JWT.create() .withClaim("username", username) .withExpiresAt(date) .sign(algorithm);
    	 } catch (UnsupportedEncodingException e)
    	 {
    		 return null; 
    		 } 
    	 }
    	 
     }

   
