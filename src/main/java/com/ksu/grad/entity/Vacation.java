package com.ksu.grad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Vacation generated by hbm2java
 */
@Entity
@Table(name = "Vacation", catalog = "EMAS", uniqueConstraints = @UniqueConstraint(columnNames = "Length"))
public class Vacation implements java.io.Serializable {

	private int id;
	private String length;
	private String description;
	private int weightId;

	public Vacation() {
	}

	public Vacation(int id, String length, int weightId) {
		this.id = id;
		this.length = length;
		this.weightId = weightId;
	}

	public Vacation(int id, String length, String description, int weightId) {
		this.id = id;
		this.length = length;
		this.description = description;
		this.weightId = weightId;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Length", unique = true, nullable = false, length = 45)
	public String getLength() {
		return this.length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "WeightId", nullable = false)
	public int getWeightId() {
		return this.weightId;
	}

	public void setWeightId(int weightId) {
		this.weightId = weightId;
	}

}
