package vn.iostar.services;

import vn.iostar.entity.User;

public interface IUserService {
	public User login(String username, String password);
	public User get(String username); //find by user name
	boolean register(String email, String password, String username, String
			fullname);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);

}
