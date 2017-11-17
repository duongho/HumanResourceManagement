package com.ksu.grad.entity;
// Generated Nov 16, 2017 10:28:30 PM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Weight generated by hbm2java
 */
@Entity
@Table(name = "Weight", catalog = "EMAS", uniqueConstraints = @UniqueConstraint(columnNames = "Value"))
public class Weight implements java.io.Serializable {

	private Integer id;
	private BigDecimal value;
	private Set<EmployeeType> employeeTypes = new HashSet<EmployeeType>(0);
	private Set<Leaves> leaveses = new HashSet<Leaves>(0);
	private Set<Review> reviews = new HashSet<Review>(0);

	public Weight() {
	}

	public Weight(BigDecimal value) {
		this.value = value;
	}

	public Weight(BigDecimal value, Set<EmployeeType> employeeTypes, Set<Leaves> leaveses, Set<Review> reviews) {
		this.value = value;
		this.employeeTypes = employeeTypes;
		this.leaveses = leaveses;
		this.reviews = reviews;
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

	@Column(name = "Value", unique = true, nullable = false, precision = 3)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "weight")
	public Set<EmployeeType> getEmployeeTypes() {
		return this.employeeTypes;
	}

	public void setEmployeeTypes(Set<EmployeeType> employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "weight")
	public Set<Leaves> getLeaveses() {
		return this.leaveses;
	}

	public void setLeaveses(Set<Leaves> leaveses) {
		this.leaveses = leaveses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "weight")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

}
