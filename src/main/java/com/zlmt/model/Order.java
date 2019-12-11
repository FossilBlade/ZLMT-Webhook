package com.zlmt.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long instId;

	@Id
	@JsonProperty("order_id")
	public Long orderId;

	@JsonProperty("company_id")
	public Long companyId;

	@JsonProperty("type_id")
	public Long typeId;

	@JsonProperty("order_source")
	public Long orderSource;

	@JsonProperty("operator_session_id")
	public Long operatorSessionId;

	@JsonProperty("price")
	public Double price;

	@JsonProperty("done_price")
	public Double donePrice;

	@JsonProperty("contragent_id")
	public Long contragentId;

	@JsonProperty("client_id")
	public Long clientId;

	@JsonProperty("phone")
	public String phone;

	@JsonProperty("car_type_id")
	public Long carTypeId;

	@JsonProperty("tariff_id")
	public Long tariffId;

	@JsonProperty("transfer_id")
	public Long transferId;

	@ElementCollection
	@JsonProperty("car_extras")
	public List<Long> carExtras = null;

	@ElementCollection
	@JsonProperty("driver_extras")
	public List<Long> driverExtras = null;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("pickup_time")
	public Date pickupTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("create_time")
	public Date createTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("accept_time")
	public Date acceptTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("car_delivery_time")
	public Date carDeliveryTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("done_time")
	public Date doneTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("cancel_time")
	public Date cancelTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("wait_client_time")
	public Date waitClientTime;

	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("with_client_time")
	public Date withClientTime;

	@JsonProperty("driving_time")
	public Long drivingTime;

	@JsonProperty("discount_price")
	public Double discountPrice;

	@JsonProperty("discount_percent")
	public Double discountPercent;

	@JsonProperty("discount_driver_compensation")
	public Double discountDriverCompensation;

	@JsonProperty("loyalty_rule_id")
	public Long loyaltyRuleId;

	@JsonProperty("promo_code_id")
	public Long promoCodeId;

	@JsonProperty("auto_dispatch")
	public Boolean autoDispatch;

	@JsonProperty("comment")
	public String comment;

	@JsonProperty("notes")
	public String notes;

	@JsonProperty("pickup_address")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "locality_id", insertable = true, updatable = true),
			@JoinColumn(name = "street", insertable = true, updatable = true) })
	public Address pickupAddress;

	@JsonProperty("destinations")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_destination_address", joinColumns = {
			@JoinColumn(name = "order_id", referencedColumnName = "orderId") }, inverseJoinColumns = {
					@JoinColumn(name = "locality_id", insertable = true, updatable = true),
					@JoinColumn(name = "street", insertable = true, updatable = true) })
	public List<Address> destinations = null;

	@JsonProperty("distance")
	public Long distance;

	@JsonProperty("driver_id")
	public Long driverId;

	@JsonProperty("state")
	public Long state;

	@JsonProperty("dispatch_method")
	public Long dispatchMethod;

	@JsonProperty("dispatch_state")
	public Long dispatchState;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	@JsonProperty("client_notifications")
	public List<ClientNotification> clientNotifications = null;

	@ElementCollection
	@JsonProperty("allowed_payment_types")
	public List<Long> allowedPaymentTypes = null;

	@JsonProperty("markup_rule_id")
	public Long markupRuleId;

	@JsonProperty("markup_price")
	public Double markupPrice;

	@JsonProperty("markup_percent")
	public Double markupPercent;

}