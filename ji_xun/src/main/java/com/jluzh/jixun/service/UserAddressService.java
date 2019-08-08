package com.jluzh.jixun.service;

import java.util.List;

import com.jluzh.model.UserAddress;

public interface UserAddressService {
	/**
     * 添加收货地址
     */
	int add(UserAddress address);
	/**
     * 删除收货地址
     * @param id 地址表的id
     */
	int delete(Long id);
	/**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
	int modifyAddress(Long id,UserAddress address);
	/**
     * 返回当前用户的收货地址
     */
	List<UserAddress> getUserAddressList();

    /**
     * 获取地址详情
     * @param id 地址id
     */
	UserAddress getDetailAddress(Long id);
}
