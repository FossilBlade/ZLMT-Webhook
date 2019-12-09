package com.zlmt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "client_notifications")
@Data
@IdClass(ClientNotificationID.class)
public class ClientNotification {

	@Id
	@JsonProperty("order_state")
	public Long orderState;
	@Id
	@JsonProperty("state")
	public Long state;

}