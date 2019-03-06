package com.information.platform.bean;

/**
 * 返回数据统一格式
 */
public class ResponseBean {
	
	/**
	 * 成功or失败
	 */
	private Boolean success;
	
	/**
	 * HTTP状态码
	 */
	private Integer code;

	/**
	 * 状态码说明
	 */
	private String message;

	/**
	 * 返回的数据
	 */
	private Object data;
	
	/**
	 * 异常的详细说明
	 */
	private String error;
	
	public ResponseBean() {
		super();
	}
	
	/**
	 * 默认error为null
	 * @param success
	 * @param code
	 * @param message
	 * @param data
	 */
	public ResponseBean(Boolean success, Integer code, String message, Object data) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
		this.error = null;
	}

	/**
	 * 需要返回所有字段
	 * @param success
	 * @param code
	 * @param message
	 * @param data
	 * @param error
	 */
	public ResponseBean(Boolean success, Integer code, String message, Object data, String error) {
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
		this.error = error;
	}



	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
