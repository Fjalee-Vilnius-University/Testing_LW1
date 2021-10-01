package com.prekes.web.prekesweb.model;

public class Preke implements Comparable<Preke> {
	
	private int kodas;
    private String pavadinimas;
    private String salis;
    private float kainaVnt;
    
    public Preke() {}
    
	public Preke(int kodas, String pavadinimas, String salis, float kainaVnt) {
		super();
		if(kodas < 0 || pavadinimas == null)
            throw new NullPointerException();
		this.kodas = kodas;
		this.pavadinimas = pavadinimas;
		this.salis = salis;
		this.kainaVnt = kainaVnt;
	}

	@Override
	public String toString() { // System.out.println(p)
		return "Preke [kodas=" + kodas + ", pavadinimas=" + pavadinimas + ", salis=" + salis + ", kainaVnt=" + kainaVnt + "]";
	}

	/**
     * Natural comparator
     * Compares the receiving object (this) with the specified object o
     * @param o
     * @return the value 0 if x == y;a value less than 0 if x < y; and value greater than 0 if x > y
     */
	@Override
	public int compareTo(Preke o) {
		return Integer.compare(this.kodas, o.kodas);
	}    
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kodas;
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
		Preke other = (Preke) obj;
		if (this.kodas != other.kodas)
			return false;
		return true;
	}

	public int getKodas() {
		return kodas;
	}
	public void setKodas(int kodas) {
		if(kodas < 0) {
            System.out.println("ERROR Preke.setKodas() trying to set wrong value for kodas=" + kodas + ", value must be >=0");
            throw new NullPointerException();
        }
		this.kodas = kodas;
	}
	
	public String getPavadinimas() {
		return pavadinimas;
	}
	
	public void setPavadinimas(String pavadinimas) {
		if(pavadinimas == null) {
            System.out.println("ERROR Preke.setPavadinimas() trying to set wrong value for pavadinimas=" + pavadinimas + ", value must be not null");
            throw new NullPointerException();
        }
		this.pavadinimas = pavadinimas;
	}
	
	public String getSalis() {
		return salis;
	}
	public void setSalis(String salis) {
		this.salis = salis;
	}
	public float getKainaVnt() {
		return kainaVnt;
	}
	public void setKainaVnt(float kainaVnt) {
		this.kainaVnt = kainaVnt;
	}
    
}
