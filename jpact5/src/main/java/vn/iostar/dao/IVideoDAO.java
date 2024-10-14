package vn.iostar.dao;

import java.util.List;

import vn.iostar.entity.User;
import vn.iostar.entity.Video;

public interface IVideoDAO {

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByTitle(String title);

	List<Video> findAll();

	Video findByVideoId(String videoid);

	void delete(String videoId) throws Exception;

	void update(Video video);

	void insert(Video video);


}
