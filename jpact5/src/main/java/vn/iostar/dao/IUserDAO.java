package vn.iostar.dao;

import java.util.List;

import vn.iostar.entity.User;


public interface IUserDAO {

	int count();

	List<User> findAll(int page, int pagesize);

	List<User> findByFullname(String fullname);

	List<User> findAll();

	User findByUsername(String username);

	void delete(String username) throws Exception;

	void update(User user);

	void insert(User user);


}
