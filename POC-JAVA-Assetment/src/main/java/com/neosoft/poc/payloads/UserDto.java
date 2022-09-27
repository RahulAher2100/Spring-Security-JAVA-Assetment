package com.neosoft.poc.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

	private int userId;
	
	@NotEmpty
	private String fullName;

	@Email
	private String emailId;

	@NotEmpty
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password is Invalid!!")
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	private String city;

	@NotEmpty
	private String district;

	@NotEmpty
	private String state;

	@NotEmpty
	private String country;

	@NotEmpty
	@Size(min = 6, max = 6, message = "Pincode Should be 6 digit")
	private String pinCode;

	@NotEmpty
	@Size(min = 12, max = 12, message = "Aadhar Card Number Should 12 digit")
	private String adharCard;

	@NotEmpty
	@Size(min = 10, max = 10, message = "Pan Card Number Should 10 digit")
	private String panCard;

	@NotEmpty
	@Size(min = 10, max = 10, message = "Mobile Number Should 10 digit")
	private String mobileNo;

	private String userType;
	private boolean status;

}
