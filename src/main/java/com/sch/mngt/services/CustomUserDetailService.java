package com.sch.mngt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.sch.mngt.entity.User;
import com.sch.mngt.entity.UserRole;
import com.sch.mngt.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("CustomUserDetailService => loadUserByUsername() -start");
		//List<User> userList = userRepository.loadUserByUsername(username);
		
		User user = userRepository.loadUserByUsername(username);;
		
		
		if(null!= user){
			CustomUserDetails userDetails = new CustomUserDetails(user.getUsername(),user.getPassword(),getGrantedAuthority(user));
		
			System.out.println("user{} => "+user);
			
			return userDetails;
		
		}else{
			throw new UsernameNotFoundException("User not found");
		}
		
	}
	
	private List<GrantedAuthority> getGrantedAuthority(User user){
		Set<UserRole> userRoles = user.getRoles();
		List<String> roleNames = new ArrayList<String>();
		for(UserRole role : userRoles){
			roleNames.add(role.getRoleName());
			
		}
		String authorityString = StringUtils.collectionToCommaDelimitedString(roleNames);
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
		return grantedAuthorities;
		
	}

}
