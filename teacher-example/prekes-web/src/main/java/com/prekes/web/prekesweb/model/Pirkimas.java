package com.prekes.web.prekesweb.model;

//import java.time.LocalDate;

public class Pirkimas implements Comparable<Pirkimas> {
	
	private int zmogausId;
    private int prekesKodas;
    private int vnt;
    private String date;

    private String prekesPav;

    public Pirkimas() {}

	public Pirkimas(int zmogausId, int prekesKodas, int vnt, String date) { // "2019-03-27"
		super();
		this.zmogausId = zmogausId;
		this.prekesKodas = prekesKodas;
		this.vnt = vnt;
		this.date = date;
	}

	public String getPirkimasId() {
		return zmogausId + "-" + prekesKodas + "-" + date;
	}
	@Override
	public String toString() {
		return "Pirkimas [zmogausId=" + zmogausId + ", prekesKodas=" + prekesKodas + ", vnt=" + vnt + ", date=" + date
				+ ", prekesPav=" + prekesPav + "]";
	}

	@Override
	public int compareTo(Pirkimas p) {
		if(this.zmogausId > p.zmogausId) 
            return 1;
        else if(this.zmogausId < p.zmogausId)
            return -1;
        else {
            if(this.prekesKodas > p.prekesKodas) return 1;
            else if(this.prekesKodas < p.prekesKodas) return -1;
            else return this.date.compareTo(p.date);
        } 
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + prekesKodas;
		result = prime * result + zmogausId;
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
		Pirkimas other = (Pirkimas) obj;
		
		if (zmogausId == other.zmogausId) {
			if (prekesKodas == other.prekesKodas) {
				if (date == null || other.date == null)
					return false;
				if (date.equals(other.date))
					return true;
			}
		}
		return false;
	}

	public int getZmogausId() {
		return zmogausId;
	}

	public void setZmogausId(int zmogausId) {
		this.zmogausId = zmogausId;
	}

	public int getPrekesKodas() {
		return prekesKodas;
	}

	public void setPrekesKodas(int prekesKodas) {
		this.prekesKodas = prekesKodas;
	}

	public int getVnt() {
		return vnt;
	}

	public void setVnt(int vnt) {
		this.vnt = vnt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrekesPav() {
		return prekesPav;
	}

	public void setPrekesPav(String prekesPav) {
		this.prekesPav = prekesPav;
	}
}
