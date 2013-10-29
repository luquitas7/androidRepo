package com.rd.biz.auth;

import com.google.gson.annotations.SerializedName;
import com.rd.communication.BaseResponse;

public class AutenticacionResponse extends BaseResponse{

	@SerializedName("Address")
	private String address;
	@SerializedName("Appuserid")
	private int appuserid;
	@SerializedName("Appusersignupstateid")
	private int appusersignupstateid;
	@SerializedName("Appusertypeid")
	private int appusertypeid;
	@SerializedName("Braincustomerid")
	private String braincustomerid;
	@SerializedName("Canlogin")
	private boolean canlogin;
	@SerializedName("City")
	private String city;
	@SerializedName("Country")
	private String country;
	@SerializedName("Customernopid")
	private int customernopid;
	@SerializedName("Email")
	private String email;
	@SerializedName("Fax")
	private String fax;
	@SerializedName("firstname")
	private String firstname;
	@SerializedName("Issuperadmin")
	private boolean issuperadmin;
	@SerializedName("lastname")
	private String lastname;
	@SerializedName("Metaxml")
	private String metaxml;
	@SerializedName("Parentid")
	private int parentid;
	@SerializedName("passwordhash")
	private String passwordhash;
	@SerializedName("Passwordsalt")
	private String passwordsalt;
	@SerializedName("Phone")
	private String phone;
	@SerializedName("State")
	private String state;
	@SerializedName("username")
	private String username;
	@SerializedName("Zip")
	private String zip;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAppuserid() {
		return appuserid;
	}
	public void setAppuserid(int appuserid) {
		this.appuserid = appuserid;
	}
	public int getAppusersignupstateid() {
		return appusersignupstateid;
	}
	public void setAppusersignupstateid(int appusersignupstateid) {
		this.appusersignupstateid = appusersignupstateid;
	}
	public int getAppusertypeid() {
		return appusertypeid;
	}
	public void setAppusertypeid(int appusertypeid) {
		this.appusertypeid = appusertypeid;
	}
	public String getBraincustomerid() {
		return braincustomerid;
	}
	public void setBraincustomerid(String braincustomerid) {
		this.braincustomerid = braincustomerid;
	}
	public boolean isCanlogin() {
		return canlogin;
	}
	public void setCanlogin(boolean canlogin) {
		this.canlogin = canlogin;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getCustomernopid() {
		return customernopid;
	}
	public void setCustomernopid(int customernopid) {
		this.customernopid = customernopid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public boolean isIssuperadmin() {
		return issuperadmin;
	}
	public void setIssuperadmin(boolean issuperadmin) {
		this.issuperadmin = issuperadmin;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMetaxml() {
		return metaxml;
	}
	public void setMetaxml(String metaxml) {
		this.metaxml = metaxml;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getPasswordhash() {
		return passwordhash;
	}
	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}
	public String getPasswordsalt() {
		return passwordsalt;
	}
	public void setPasswordsalt(String passwordsalt) {
		this.passwordsalt = passwordsalt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("Appuserid: ").append(this.appuserid)
				.append(", Firstname: ").append(this.firstname)
				.append(", Lastname: ").append(this.lastname)
				.append(", Username: ").append(this.username)
				.toString();
	}
}
