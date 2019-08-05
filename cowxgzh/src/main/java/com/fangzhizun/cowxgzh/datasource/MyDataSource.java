package com.fangzhizun.cowxgzh.datasource;

public class MyDataSource {

	private Integer id;

	private String name;

	private String companyName;

	private String companyContact;

	private String companyTel;

	private String companyAddress;

	private String packageName;

	private String logoCompany;

	private String codeVersion;

	private String driver;

	private String url;

	private String username;

	private String password;

	private String dbname;

	private String nodepath;

	private Integer state;

	private String registrationTime;

	private String companyRentCity;

	private String authoritySwitch;

	private String appId;

	private String secret;

	private String wxKey;

	private String wxMerchantNumber;

    public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getWxMerchantNumber() {
		return wxMerchantNumber;
	}

	public void setWxMerchantNumber(String wxMerchantNumber) {
		this.wxMerchantNumber = wxMerchantNumber;
	}

	public String getWxKey() {
		return wxKey;
	}

	public void setWxKey(String wxKey) {
		this.wxKey = wxKey;
	}

	public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAuthoritySwitch() {
		return authoritySwitch;
	}

	public void setAuthoritySwitch(String authoritySwitch) {
		this.authoritySwitch = authoritySwitch;
	}

	public String getCompanyRentCity() {
		return companyRentCity;
	}

	public void setCompanyRentCity(String companyRentCity) {
		this.companyRentCity = companyRentCity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyContact() {
		return companyContact;
	}

	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getLogoCompany() {
		return logoCompany;
	}

	public void setLogoCompany(String logoCompany) {
		this.logoCompany = logoCompany;
	}

	public String getCodeVersion() {
		return codeVersion;
	}

	public void setCodeVersion(String codeVersion) {
		this.codeVersion = codeVersion;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getNodepath() {
		return nodepath;
	}

	public void setNodepath(String nodepath) {
		this.nodepath = nodepath;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	@Override
	public String toString() {
		return "MyDataSource [id=" + id + ", name=" + name + ", companyName=" + companyName + ", companyContact="
				+ companyContact + ", companyTel=" + companyTel + ", companyAddress=" + companyAddress
				+ ", packageName=" + packageName + ", logoCompany=" + logoCompany + ", codeVersion=" + codeVersion
				+ ", driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ ", dbname=" + dbname + ", nodepath=" + nodepath + ", state=" + state + ", registrationTime="
				+ registrationTime + ", companyRentCity=" + companyRentCity + ", authoritySwitch=" + authoritySwitch
				+ ", appId=" + appId + ", secret=" + secret + ", wxKey=" + wxKey + ", wxMerchantNumber="
				+ wxMerchantNumber + "]";
	}


}
