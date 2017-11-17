package com.ksu.grad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name = "Review", catalog = "EMAS", uniqueConstraints = @UniqueConstraint(columnNames = "Label"))
public class Review implements java.io.Serializable {

	private Integer id;
	private Weight weight;
	private String label;
	private String description;

	public Review() {
	}

	public Review(Weight weight, String label, String description) {
		this.weight = weight;
		this.label = label;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WeightId", nullable = false)
	public Weight getWeight() {
		return this.weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	@Column(name = "Label", unique = true, nullable = false, length = 45)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "Description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
