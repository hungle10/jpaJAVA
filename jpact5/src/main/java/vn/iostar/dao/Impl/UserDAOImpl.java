package vn.iostar.dao.Impl;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.JPAConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.dao.IUserDAO;
import vn.iostar.entity.Category;
import vn.iostar.entity.User;

public class UserDAOImpl implements IUserDAO{

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.persist(user);
			trans.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			trans.rollback();
			throw e;
			
		}
		finally {
			enma.close();
		}
	}
	@Override
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.merge(user);
			trans.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			trans.rollback();
			throw e;
			
		}
		finally {
			enma.close();
		}
	}
	@Override
	public void delete(String username) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			User user = enma.find(User.class,username);
			if(user!=null)
			enma.remove(user);
			else {
			 throw new Exception("Khong tim thay");
			}
			trans.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			trans.rollback();
			throw e;
			
		}
		finally {
			enma.close();
		}
	}
	@Override
	public User findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class,username);
		return user;
	}
	@Override
	public List<User> findAll(){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll",User.class);
		return query.getResultList();
	}
	@Override
	public List<User> findByFullname(String fullname){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT u from User u where u.fullname LIKE :fullname";
		TypedQuery<User> query = enma.createQuery(jpql,User.class);
		query.setParameter("fullname","%"+fullname+"%");
		return query.getResultList();
	}
	@Override
	public List<User> findAll(int page,int pagesize){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll",User.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(u) from User u";
		Query query = enma.createQuery(jpql,User.class);
	    return ((Long)query.getSingleResult()).intValue();
	}
}

