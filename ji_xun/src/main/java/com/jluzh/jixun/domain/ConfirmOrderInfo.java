package com.jluzh.jixun.domain;

import java.math.BigDecimal;
import java.util.List;
import com.jluzh.model.CartItem;
import com.jluzh.model.UserAddress;


public class ConfirmOrderInfo {
	 //用户购物车商品列表
	private List<CartItem> goodsList;
	 //用户收货地址列表
	private List<UserAddress> userAddressList;
	 //计算的金额
    private OrderAmout calcAmount;
	 //TODO:其他功能待完善
	
    public List<CartItem> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<CartItem> goodsList) {
		this.goodsList = goodsList;
	}
    
	public List<UserAddress> getUserAddressList() {
		return userAddressList;
	}

	public void setUserAddressList(List<UserAddress> userAddressList) {
		this.userAddressList = userAddressList;
	}

	public OrderAmout getCalcAmount() {
		return calcAmount;
	}

	public void setCalcAmount(OrderAmout calcAmount) {
		this.calcAmount = calcAmount;
	}




	public static class OrderAmout{
		//订单商品总金额
		private BigDecimal totalAmount;
		//运费TODO:暂不考虑
        //private BigDecimal freightAmount;
		//活动优惠TODO:暂不考虑
        //private BigDecimal promotionAmount;
		//应付金额
        private BigDecimal payAmount;
        
		public BigDecimal getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(BigDecimal totalAmount) {
			this.totalAmount = totalAmount;
		}
		public BigDecimal getPayAmount() {
			return payAmount;
		}
		public void setPayAmount(BigDecimal payAmount) {
			this.payAmount = payAmount;
		}
        
        
	}
}
