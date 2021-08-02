package com.ssafy.niceage.Domain.User;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class UserDTO {
	
	@Id @GeneratedValue
	@Column (name = "user_no")
	private int No;
	@Column (name = "user_id")
	private String Id;
	@Column (name = "user_password")
    private String Password;
	@Column (name = "user_name")
    private String Name;
	@Column (name = "user_address")
    private String Address;
	@Column (name = "user_gender")
    private String Gender;
	@Column (name = "user_birth")
    private String Birth;
	@Column (name = "user_phone_number")
    private String Phone;
	@Column (name = "user_emergency_number")
    private String Emergency;
	
    public UserDTO(int no, String id, String password, String name, String address, String gender, String birth,
			String phone, String emergency) {
		super();
		No = no;
		Id = id;
		Password = password;
		Name = name;
		Address = address;
		Gender = gender;
		Birth = birth;
		Phone = phone;
		Emergency = emergency;
	}

}
