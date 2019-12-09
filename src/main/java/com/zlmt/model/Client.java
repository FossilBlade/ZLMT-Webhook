package com.zlmt.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {

	@Id
	@JsonProperty("id")
	public Long id;

	@JsonProperty("company_id")
	public Long companyId;
	@JsonProperty("discount_number")
	public String discountNumber;
	@JsonProperty("fullname")
	public String fullname;
	@JsonProperty("email")
	public String email;
	@JsonProperty("success_orders")
	public Long successOrders;
	@JsonProperty("reject_orders")
	public Long rejectOrders;
	@JsonProperty("personal_discount_sum")
	public Long personalDiscountSum;
	@JsonProperty("personal_discount_percent")
	public Long personalDiscountPercent;
	@JsonProperty("bonus_balance")
	public Long bonusBalance;
	@JsonProperty("comment")
	public String comment;
	@JsonProperty("facebook_id")
	public Long facebookId;
	@JsonProperty("vk_id")
	public Long vkId;
	@JsonProperty("google_id")
	public Long googleId;
	@JsonProperty("blacklist")
	public Boolean blacklist;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "client_phones", joinColumns = {
			@JoinColumn(name = "client_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "phone_id", referencedColumnName = "id") })
	@JsonProperty("phones")
	public List<Phone> phones = null;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "client_phones", joinColumns = {
			@JoinColumn(name = "client_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "phone_id", referencedColumnName = "id") })
	@JsonProperty("notes")
	public List<CustomerNote> notes = null;

}