package com.ssafy.niceage.Domain.Senior_Citizen_Center;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Senior_Citizen_CenterDTO {
	@Id @GeneratedValue
	@Column (name = "senior_id")
	private int Id;

	@Column (name = "senior_name")
    private String Name;
	
	@Column (name = "senior_address")
    private String Address;
	
	public Senior_Citizen_CenterDTO(int id, String name, String address) {
		super();
		Id = id;
		Name = name;
		Address = address;
	}
	
}
