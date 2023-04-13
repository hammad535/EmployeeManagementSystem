package application;

import java.util.ArrayList;

public class Employee {

	public int id;
	public String name, job, gender;
	public boolean fullTime;
	
	public static ArrayList<Employee> employees = new ArrayList<>();
	
	Employee(){this.id=-1;}
	
	public Employee(int id, String name, String job, String gender, boolean fullTime) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
		this.gender = gender;
		this.fullTime = fullTime;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isFullTime() {
		return fullTime;
	}
	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", job=" + job + ", gender=" + gender + ", fullTime="
				+ fullTime + "]";
	}
	
	
	
}

