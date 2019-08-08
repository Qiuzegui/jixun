package com.jluzh.jixun.domain;

public class OrderInfo {
	//收货地址id
    private Long userAddressId;
    //支付方式
    private Integer payType;
    
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
    
    
}
