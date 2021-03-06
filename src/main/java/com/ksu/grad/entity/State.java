package com.ksu.grad.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * State generated by hbm2java
 */
@Entity
@Table(name = "State", catalog = "EMAS", uniqueConstraints = @UniqueConstraint(columnNames = { "Code", "Country_Id",
		"Name" }))
public class State implements java.io.Serializable {

	private Integer id;
	private Country country;
	private String name;
	private String code;
	private Set<Address> addresses = new HashSet<Address>(0);

	public State() {
	}

	public State(Country country, String name, String code) {
		this.country = country;
		this.name = name;
		this.code = code;
	}

	public State(Country country, String name, String code, Set<Address> addresses) {
		this.country = country;
		this.name = name;
		this.code = code;
		this.addresses = addresses;
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

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Country_Id", nullable = false)
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "Name", nullable = false, length = 125)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Code", nullable = false, length = 4)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "state")
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
