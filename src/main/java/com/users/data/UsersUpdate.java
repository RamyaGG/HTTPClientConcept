package com.users.data;

public class UsersUpdate {

	//class var, global var
	String name;
	String job;
	String id;
	String updatedAt;
	
	public UsersUpdate() {
		
	}
	
	public UsersUpdate(String name , String job){
		this.name = name; //this .name -- refers to class variables
		this.job = job;
//		this.id=id;
//		this.updatedAt=updatedAt;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
