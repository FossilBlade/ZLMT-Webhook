package com.zlmt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "client_notifications")
@Data
//@IdClass(ClientNotificationID.class)
public class ClientNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notifyInstId;

//	@Id
	@JsonProperty("order_state")
	public Long orderState;
//	@Id
	@JsonProperty("state")
	public Long state;

	@ManyToOne
	@JoinColumn(name = "order_id", insertable = true, updatable = true)
	private Order order;

}