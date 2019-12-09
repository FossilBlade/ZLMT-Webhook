package com.zlmt.model;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "drivers")
@Data
public class Driver {

	@Id
	@JsonProperty("driver_id")
	public Long driverId;
	@JsonProperty("company_id")
	public Long companyId;
	@JsonProperty("car_id")
	public Long carId;
	@JsonProperty("rate_id")
	public Long rateId;
	@JsonProperty("group_id")
	public Long groupId;
	@JsonProperty("call_sign")
	public Long callSign;
	@JsonProperty("password")
	public String password;
	@JsonProperty("phone")
	public String phone;
	@JsonProperty("home_address")
	public String homeAddress;
	@JsonProperty("email")
	public String email;
	@JsonProperty("driver_licence")
	public String driverLicence;
	@JsonProperty("taxi_licence")
	public String taxiLicence;
	@JsonProperty("balance")
	public Double balance;
	@JsonProperty("min_balance")
	public Long minBalance;
	@JsonProperty("comment")
	public String comment;

	@ElementCollection
	@JsonProperty("extras_id")
	public List<Long> extrasId = null;

	@ElementCollection
	@JsonProperty("allowed_order_types_id")
	public List<Long> allowedOrderTypesId = null;

	@Temporal(TemporalType.DATE)
	@JsonProperty("birthday")
	public Date birthday;

	@Temporal(TemporalType.DATE)
	@JsonProperty("hire_date")
	public Date hireDate;

	@Temporal(TemporalType.DATE)
	@JsonProperty("fire_date")
	public Date fireDate;

	@JsonProperty("active")
	public Boolean active;

}