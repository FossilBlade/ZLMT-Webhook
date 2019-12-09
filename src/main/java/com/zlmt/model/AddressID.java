package com.zlmt.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressID implements Serializable {

	public Long localityId;
	public String street;

	public AddressID() {

	}

}