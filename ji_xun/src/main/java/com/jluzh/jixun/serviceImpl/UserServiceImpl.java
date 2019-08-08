package com.jluzh.jixun.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jluzh.jixun.dao.UserInfoDao;
import com.jluzh.jixun.domain.MemberDetails;
import com.jluzh.jixun.service.UserService;
import com.jluzh.mapper.UserMemberMapper;
import com.jluzh.model.UserMember;
import com.jluzh.model.UserMemberExample;
/**
 * 用户管理类
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMemberMapper userMemberMapper;
	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public UserMember getByUsername(String username) {
		UserMemberExample example = new UserMemberExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UserMember> userList = userMemberMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(userList)) {
            return userList.get(0);
        }
		return null;
	}
	@Override
	public UserMember getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		MemberDetails user= (MemberDetails)auth.getPrincipal();
		return user.getUserMember();
	}
	@Override
	public UserMember getByOpenID(String OpenID) {
		UserMemberExample example = new UserMemberExample();
		example.createCriteria().andOpenidEqualTo(OpenID);
		List<UserMember> userList = userMemberMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(userList)) {
            return userList.get(0);
        }
		return null;
	}
	@Override
	public UserMember autoGenerateUser(String openID) throws Exception {
		UserMember record = new UserMember();
		record.setOpenid(openID);
		int res = userMemberMapper.insert(record);
		if(res<=0)throw new Exception("创建失败");
		return record;
	}
	@Override
	public Integer updataWxUserInfo(String nickName, String avatarUrl) {
		UserMember user = this.getCurrentUser();
		int res = userInfoDao.updataWxUserInfo(user.getId(),nickName,avatarUrl);
		return res;
	}
	
}
