package com.information.platform.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken  implements AuthenticationToken{
	private static final long serialVersionUID = 1L;
	//密钥
	private String token;

	public JwtToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		
		return token;
	}

	@Override
	public Object getCredentials() {
		
		return token;
	}

}
