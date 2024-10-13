package vn.iostar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll",query="Select u from User u ")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Username")
	private String username;
	
	@Column(name="Active")
	private boolean active;
	
	@Column(name="Admin")
	private boolean admin;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Phone")
	@Pattern(regexp = "^\\{8,10}$", message = " Số điện thoại từ 8-10 số")
	@NotEmpty(message="Hãy nhập số điện thoại")
	private String phone;
	
	@Column(name="Fullname", columnDefinition = "nvarchar(255)")
	private String fullname;
	
	@Column(name="Password", columnDefinition = "nvarchar(255)")
	private String password;
	
	@Column(name="Images", columnDefinition = "nvarchar(500)")
	private String images;

}
