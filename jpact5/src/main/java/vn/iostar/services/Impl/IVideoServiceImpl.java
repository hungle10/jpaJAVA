package vn.iostar.services.Impl;
import vn.iostar.dao.Impl.*;
import java.util.List;

import vn.iostar.entity.User;
import vn.iostar.entity.Video;
import vn.iostar.dao.*;
import vn.iostar.services.*;


public class IVideoServiceImpl implements IVideoService{
	IVideoDAO videodao = new VideoDAOImpl();
	@Override
	public int count() {
		return videodao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videodao.findAll(page, pagesize);
	}

	@Override
	public List<Video> findByTitle(String title) {
		return videodao.findByTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videodao.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return videodao.findByUsername(username);
	}

	@Override
	public void delete(int videoId) throws Exception {
		videodao.delete(videoId);
		
	}

	@Override
	public void update(Video video) {
		videodao.update(video);
		
	}

	@Override
	public void insert(Video video) {
		videodao.insert(video);
		
	}

}
