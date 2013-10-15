package org.zlp.domain;

import java.io.Serializable;

/**
 * Created with eclipse
 * 
 * @Description: 获取AccessToken返回对象
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-12
 * @Time: 上午10:39:02
 * 
 */
public class AccessToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String access_token = null;

	private String expires_in = null;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

}