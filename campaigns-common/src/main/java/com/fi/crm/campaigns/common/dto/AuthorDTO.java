/**
 * 
 */
package com.fi.crm.campaigns.common.dto;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Jan, 29/2015 
 */
public class AuthorDTO implements BaseDTO{

	/** Serial UID */
	private static final long serialVersionUID = 1L;

	private Integer AuthorId;
	private String firstName;
	private String lastName1;
	private String lastName2;
	private String email;
	private String fullName;
	private String status;
	private PdUsuarioDTO pdusuario;

	/** Default constructor */
	public AuthorDTO() {}

	/** Id Constructor */
	public AuthorDTO(Integer authorId) {
		super();
		AuthorId = authorId;
	}

	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return AuthorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		AuthorId = authorId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName1
	 */
	public String getLastName1() {
		return lastName1;
	}

	/**
	 * @param lastName1 the lastName1 to set
	 */
	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	/**
	 * @return the lastName2
	 */
	public String getLastName2() {
		return lastName2;
	}

	/**
	 * @param lastName2 the lastName2 to set
	 */
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((AuthorId == null) ? 0 : AuthorId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorDTO other = (AuthorDTO) obj;
		if (AuthorId == null) {
			if (other.AuthorId != null)
				return false;
		} else if (!AuthorId.equals(other.AuthorId))
			return false;
		return true;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PdUsuarioDTO getPdusuario() {
		return pdusuario;
	}

	public void setPdusuario(PdUsuarioDTO pdusuario) {
		this.pdusuario = pdusuario;
	}
}