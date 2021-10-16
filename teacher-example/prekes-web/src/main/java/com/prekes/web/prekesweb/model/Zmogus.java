package com.prekes.web.prekesweb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // map to db table. Default table name is the name of entity class
public class Zmogus implements Comparable<Zmogus> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // JPA autogenerates value of id
	private int id;
	
	private String vardas;
	private String role;
	
	protected Zmogus() {}
	
	public Zmogus(String vardas, String role) {
		this.vardas = vardas;
		this.role = role;
	}

	/*public Zmogus(int id, String vardas, String role) {
		super();
		this.id = id;
		this.vardas = vardas;
		this.role = role;
	}*/

	@Override
	public String toString() {
		return "Zmogus [id=" + id + ", vardas=" + vardas + " role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zmogus other = (Zmogus) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Zmogus o) {
		return Integer.compare(this.id, o.getId());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVardas() {
		return vardas;
	}

	public void setVardas(String vardas) {
		this.vardas = vardas;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
