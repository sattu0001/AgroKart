package com.app.agri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.agri.common.AgriContant;
import com.app.agri.entity.Role;
import com.app.agri.service.RolesServices;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI+"/roles")
public class RoleController {
	@Autowired
	RolesServices roleService;
	
	@GetMapping("")
	public ResponseEntity<List<Role>> getAllRoles(){
		return ResponseEntity.ok(roleService.getAllRoles());
	}
}
