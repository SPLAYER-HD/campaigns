package com.fi.crm.campaigns.common.dto;

import java.util.Date;

import com.fi.crm.campaigns.common.enums.GenreEnum;

public class ClientDTO implements BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyId;
    private String clientCode;
	private Date createDate;
    private String storeId;//centrod
    private DocumentTypeDTO documentType;    
    private String documentNumber;
    private String firstName;//nombre pila
    private String lastName1;
    private String lastName2;
    private String indicative;
    private String telephone;
    private String cellPhone;
    private GenreEnum genre;
    private String email;
    private String fullName;
    private Date birthday;
    private String nationalIndicative;//indNacional;
    private String country;//pais;
    private CityDTO city;//canton;
    private ProvinceDTO province;
    private Date tstamp;
    private String indClientCode;//indCodCliente;
    private String indEmployed;//indEmpleado;
    private String address;
    
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public DocumentTypeDTO getDocumentType() {
		return documentType;
	}
	public void setDocumentType(DocumentTypeDTO documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName1() {
		return lastName1;
	}
	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}
	public String getLastName2() {
		return lastName2;
	}
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
	public String getIndicative() {
		return indicative;
	}
	public void setIndicative(String indicative) {
		this.indicative = indicative;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public GenreEnum getGenre() {
		return genre;
	}
	public void setGenre(GenreEnum genre) {
		this.genre = genre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNationalIndicative() {
		return nationalIndicative;
	}
	public void setNationalIndicative(String nationalIndicative) {
		this.nationalIndicative = nationalIndicative;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ProvinceDTO getProvince() {
		return province;
	}
	public void setProvince(ProvinceDTO province) {
		this.province = province;
	}
	public Date getTstamp() {
		return tstamp;
	}
	public void setTstamp(Date tstamp) {
		this.tstamp = tstamp;
	}
	public String getIndClientCode() {
		return indClientCode;
	}
	public void setIndClientCode(String indClientCode) {
		this.indClientCode = indClientCode;
	}
	public String getIndEmployed() {
		return indEmployed;
	}
	public void setIndEmployed(String indEmployed) {
		this.indEmployed = indEmployed;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public CityDTO getCity() {
		return city;
	}
	public void setCity(CityDTO city) {
		this.city = city;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ClientDTO [companyId=" + companyId + ", clientCode=" + clientCode + ", createDate=" + createDate + ", storeId=" + storeId
				+ ", documentType=" + documentType + ", documentNumber=" + documentNumber + ", firstName=" + firstName + ", lastName1=" + lastName1
				+ ", lastName2=" + lastName2 + ", indicative=" + indicative + ", telephone=" + telephone + ", cellPhone=" + cellPhone + ", genre="
				+ genre + ", email=" + email + ", fullName=" + fullName + ", birthday=" + birthday + ", nationalIndicative=" + nationalIndicative
				+ ", country=" + country + ", city=" + city + ", province=" + province + ", tstamp=" + tstamp + ", indClientCode=" + indClientCode
				+ ", indEmployed=" + indEmployed + ", address=" + address + "]";
	}
    
}
