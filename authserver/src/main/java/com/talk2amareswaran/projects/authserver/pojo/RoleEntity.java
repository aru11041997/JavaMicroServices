package com.talk2amareswaran.projects.authserver.pojo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	private String role;
	
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
	
	public RoleEntity() {}

	public RoleEntity(String role) {
		this.role = role;
	}

	public int getId() {
		return roleId;
	}

	public void setId(int id) {
		this.roleId = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
	
	
}
