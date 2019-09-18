package com.fi.crm.campaigns.common.dto;

import java.util.TreeMap;

import com.google.gson.Gson;


public class JamesDataDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2985101397188350719L;
	
    private Integer id;    
    private String ip;
    private String port;
    
    public JamesDataDTO () {
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		JamesDataDTO other = (JamesDataDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
    public static void main(String[] args) {
		TreeMap<Integer, JamesDataDTO> l = new TreeMap<>();
    	JamesDataDTO j= new JamesDataDTO();
		j.setId(1);
		j.setIp("192.168.1.36");
		j.setPort("25");
		l.put(1,j);
		
		j= new JamesDataDTO();
		j.setId(2);
		j.setIp("192.168.1.36");
		j.setPort("26");
		l.put(2,j);
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(l));
	}
}