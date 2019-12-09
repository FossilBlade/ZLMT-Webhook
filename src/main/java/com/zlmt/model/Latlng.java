package com.zlmt.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "lat_lngs")
@IdClass(LatLngID.class)
@Data
public class Latlng {
	@Id
	@JsonProperty("lat")
	public Double lat;

	@Id
	@JsonProperty("lng")
	public Double lng;

}