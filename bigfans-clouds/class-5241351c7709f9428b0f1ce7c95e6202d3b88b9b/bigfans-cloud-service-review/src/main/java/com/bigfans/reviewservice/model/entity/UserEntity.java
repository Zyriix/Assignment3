package com.bigfans.reviewservice.model.entity;

import com.bigfans.framework.model.AbstractModel;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name="User")
public class UserEntity extends AbstractModel {

	public String getModule() {
		return "User";
	}
	
	private static final long serialVersionUID = -1189566871934067878L;

	@Column(name = "status")
	protected Integer status;
	@Column(name = "account")
	protected String account;
	@Column(name = "email")
	protected String email;
	@Column(name = "mobile")
	protected String mobile;
	@Column(name = "avatar")
	protected String avatar;

}