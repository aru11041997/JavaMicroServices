package com.talk2amareswaran.projects.authserver.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.talk2amareswaran.projects.authserver.model.UserEntity;
import com.talk2amareswaran.projects.authserver.pojo.RoleEntity;
import com.talk2amareswaran.projects.authserver.repo.UserRepo;

@Repository
public class OAuthDAOServiceImpl implements OAuthDAOService {

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserEntity getUserDetails(String emailId) {
		
		Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
		
		com.talk2amareswaran.projects.authserver.pojo.UserEntity pojoUser=userRepo.findByUserName(emailId);
		
		UserEntity modelUser=new UserEntity();
		if(pojoUser!=null) {
			modelUser.setId(Integer.toString(pojoUser.getId()));
			modelUser.setEmailId(emailId);
			modelUser.setName(pojoUser.getName());
			modelUser.setPassword(pojoUser.getPassword());
			Set<RoleEntity> roleEntities=pojoUser.getRoles();
			if(roleEntities!=null) {
				for (RoleEntity roleEntity : roleEntities) {
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+roleEntity.getRole());
					grantedAuthoritiesList.add(grantedAuthority);
				}
			}
			modelUser.setGrantedAuthoritiesList(grantedAuthoritiesList);
			
			return modelUser;
		}
		
		return null;
	
		
		
	}

}
