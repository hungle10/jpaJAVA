package vn.iostar.services;

import java.util.List;

import vn.iostar.dao.IVideoDAO;
import vn.iostar.dao.Impl.VideoDAOImpl;
import vn.iostar.entity.User;
import vn.iostar.entity.Video;

public interface IVideoService {

	void insert(Video video);

	void update(Video video);

	void delete(String videoId) throws Exception;

	Video findByVideoId(String videoid);

	List<Video> findAll();

	List<Video> findByTitle(String title);

	List<Video> findAll(int page, int pagesize);

	int count();

}
