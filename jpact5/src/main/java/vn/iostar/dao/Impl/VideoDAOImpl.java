package vn.iostar.dao.Impl;

import java.util.List;

import config.JPAConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.dao.IVideoDAO;
import vn.iostar.entity.Category;
import vn.iostar.entity.User;
import vn.iostar.entity.Video;

public class VideoDAOImpl implements IVideoDAO {
	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.persist(video);
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
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			enma.merge(video);
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
	public void delete(String videoId) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		try {
			trans.begin();
			Video video = enma.find(Video.class,videoId);
			if(video!=null)
			enma.remove(video);
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
	public Video findByVideoId(String videoid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class,videoid);
		return video;
	}
	@Override
	public List<Video> findAll(){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll",Video.class);
		return query.getResultList();
	}
	@Override
	public List<Video> findByTitle(String title){
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT v from Video v where v.title LIKE :title";
		TypedQuery<Video> query = enma.createQuery(jpql,Video.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}
	@Override
	public List<Video> findAll(int page,int pagesize){
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll",Video.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(v) from Video v";
		Query query = enma.createQuery(jpql,Video.class);
	    return ((Long)query.getSingleResult()).intValue();
	}

}
