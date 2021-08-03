package com.ssafy.niceage.Domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ssafy.niceage.Domain.Board.Board;
import com.ssafy.niceage.Domain.Comment.Comment;
import com.ssafy.niceage.Domain.Enter.Enter;

import lombok.Data;

@Entity
@Data
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column (name = "user_no", nullable = false)
	private int No;
	
	@OneToMany(mappedBy = "enter")
	List<Enter> enters = new ArrayList<Enter>();
	
	@OneToMany(mappedBy = "board")
	List<Board> boards = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "comment")
	List<Comment> comments = new ArrayList<Comment>();
	
	@Column (name = "user_id", nullable = false, length = 45)
	private String Id;
	
	@Column (name = "user_password", nullable = false, length = 45)
    private String Password;
	
	@Column (name = "user_name", nullable = false, length = 45)
    private String Name;
	
	@Column (name = "user_address", nullable = false, length = 200)
    private String Address;
	
	@Column (name = "user_gender", nullable = false, length = 1)
    private String Gender;
	
	@Column (name = "user_birth", nullable = false)
	@Temporal(TemporalType.DATE)
    private Date Birth;
	
	@Column (name = "user_phone_number", nullable = false, length = 30)
    private String Phone;
	
	@Column (name = "user_emergency_number", nullable = true, length = 30)
    private String Emergency;

	public User(int no, String id, String password, String name, String address, String gender, Date birth,
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
