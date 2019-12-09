package com.zlmt.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "client_notes")
@Data
public class CustomerNote {

	@Id
	@JsonProperty("id")
	public Long id;

	@JsonProperty("note_date")
	public Date noteDate;
	@JsonProperty("user_id")
	public Long userId;
	@JsonProperty("text")
	public String text;

}