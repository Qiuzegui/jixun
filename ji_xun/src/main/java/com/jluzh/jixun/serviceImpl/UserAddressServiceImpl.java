package com.jluzh.jixun.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jluzh.jixun.service.UserAddressService;
import com.jluzh.jixun.service.UserService;
import com.jluzh.mapper.UserAddressMapper;
import com.jluzh.model.UserAddress;
import com.jluzh.model.UserAddressExample;
import com.jluzh.model.UserMember;

import io.jsonwebtoken.lang.Collections;

@Service
public class UserAddressServiceImpl implements UserAddressService {
	@Autowired
	UserService userService;
	@Autowired
	UserAddressMapper userAddressMapper;
	@Override
	public int add(UserAddress address) {
		UserMember user = userService.getCurrentUser();
		address.setMemberId(user.getId());
		int res = userAddressMapper.insert(address);
		return res;
	}

	@Override
	public int delete(Long id) {
		UserMember user = userService.getCurrentUser();
		UserAddressExample example = new UserAddressExample();
		example.createCriteria().andMemberIdEqualTo(user.getId()).andIdEqualTo(id);
		int res = userAddressMapper.deleteByExample(example);
		return res;
	}

	@Override
	public int modifyAddress(Long id, UserAddress address) {
		address.setId(null);
		UserMember user = userService.getCurrentUser();
		UserAddressExample example = new UserAddressExample();
		example.createCriteria().andMemberIdEqualTo(user.getId()).andIdEqualTo(id);
		int res = userAddressMapper.updateByExampleSelective(address, example);
		return res;
	}

	@Override
	public List<UserAddress> getUserAddressList() {
		UserMember user = userService.getCurrentUser();
		UserAddressExample example = new UserAddressExample();
		example.createCriteria().andMemberIdEqualTo(user.getId());
		List<UserAddress> res = userAddressMapper.selectByExample(example);
		return res;
	}

	@Override
	public UserAddress getDetailAddress(Long id) {
		UserMember user = userService.getCurrentUser();
		UserAddressExample example = new UserAddressExample();
		example.createCriteria().andMemberIdEqualTo(user.getId()).andMemberIdEqualTo(user.getId());
		List<UserAddress> res = userAddressMapper.selectByExample(example);
		if(!Collections.isEmpty(res))
			return res.get(0);
		return null;
	}
	
}
