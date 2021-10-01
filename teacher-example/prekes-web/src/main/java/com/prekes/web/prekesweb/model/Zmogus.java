package com.prekes.web.prekesweb.model;

public class Zmogus implements Comparable<Zmogus> {
	private int id;
	private String vardas;
	
	public Zmogus() {}
	
	public Zmogus(int id, String vardas) {
		super();
		this.id = id;
		this.vardas = vardas;
	}

	@Override
	public String toString() {
		return "Zmogus [id=" + id + ", vardas=" + vardas + "]";
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

}
