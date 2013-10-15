package org.zlp.domain;

import java.io.Serializable;

/**
 * Created with eclipse
 * 
 * @Description: 返回码
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-12
 * @Time: 上午11:28:02
 * 
 */
public class Error implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errcode = null;

	private String errmsg = null;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}