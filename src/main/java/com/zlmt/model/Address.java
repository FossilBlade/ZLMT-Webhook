package com.zlmt.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
@IdClass(AddressID.class)
public class Address {

	@Id
	@JsonProperty("locality_id")
	public Long localityId;

	@Id
	@JsonProperty("street")
	public String street;

	@JsonProperty("building")
	public String building;
	@JsonProperty("comment")
	public String comment;
	@JsonProperty("parking_id")
	public Long parkingId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "lat", insertable = true, updatable = true),
			@JoinColumn(name = "lng", insertable = true, updatable = true) })
	@JsonProperty("latlng")
	public Latlng latlng;

}