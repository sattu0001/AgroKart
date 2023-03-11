package com.app.agri.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agri.entity.Role;
import com.app.agri.repository.RoleRepository;
import com.app.agri.service.RolesServices;

@Service
public class RolesServiceImpl extends ConversionService implements RolesServices {
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles=roleRepo.findAll();
		
		
		Iterator<Role> it=roles.iterator();
		while(it.hasNext()) {
			if(it.next().getRole().equalsIgnoreCase("")) {
				it.remove();
			}
		}
		return roles;
		
	}
}
