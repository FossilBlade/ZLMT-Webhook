package com.zlmt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "phones")
@Data
public class Phone {

	@Id
	@JsonProperty("id")
	public Long id;

	@JsonProperty("phone")
	public String phone;

}