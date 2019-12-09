package com.zlmt.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientNotificationID implements Serializable {

	public Long orderState;

	public Long state;

}