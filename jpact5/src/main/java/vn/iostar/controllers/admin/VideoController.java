package vn.iostar.controllers.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Category;
import vn.iostar.entity.Video;
import vn.iostar.services.*;
import vn.iostar.services.Impl.*;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert",
		"/admin/video/edit", "/admin/video/update", "/admin/video/delete", "/admin/video/search" })
@MultipartConfig()
public class VideoController extends HttpServlet{
	IVideoService videoservice = new IVideoServiceImpl();
	ICategoryService categoryservice = new CategoryServiceImpl();
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\jpaJAVA\\jpact5\\src\\main\\webapp\\uploads";
	public static final String DEFAULT_FILENAME = "default.file";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/admin/videos")) {
	        List<Video> list = videoservice.findAll();
	        req.setAttribute("listvideo", list);
	        req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
	    } else if (url.contains("/admin/video/add")) {
	        List<Category> list = categoryservice.findAll();
	        //System.out.print("helo");
	       req.setAttribute("categorylist", list);
	       req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
	    } else if (url.contains("/admin/video/edit")){
	    	HttpSession session = req.getSession();
			String videoId = req.getParameter("videoId");
			Video video = videoservice.findByVideoId(videoId);
			List<Category> listcate = categoryservice.findAll();
			session.setAttribute("video", video);
			session.setAttribute("categorylist",listcate);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
	    }else if (url.contains("/admin/video/delete")) {
			System.out.print("Cuc cu chuan bi xoa ne");
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project

			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				String fileName = "";
				String videoId = req.getParameter("videoId");
				Video video = videoservice.findByVideoId(videoId);
				fileName = video.getImages();

				if (fileName != "") {
					videoservice.delete(videoId);
					File fileToDelete = new File(uploadPath + File.separator + fileName);
					fileToDelete.delete();
				}
				req.setAttribute("message", "File " + fileName + " has been deleted successfully!");
			} catch (Exception e) {

			}
			List<Video> list = videoservice.findAll();
	        req.setAttribute("listvideo", list);
	        req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}

	}
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return DEFAULT_FILENAME;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("insert")) {
			System.out.print("Cuc cu 2222");
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project

			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				String fileName = "";
				// vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì
				// duyệt lại
				List<Part> fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName()))
						.collect(Collectors.toList());
				for (Part part : fileParts) {
					fileName = getFileName(part);
					if (fileName != "") {
						String videoId = req.getParameter("videoId");
						String videotitle = req.getParameter("videotitle");
						String videocount = req.getParameter("videocount");
						String category = req.getParameter("category");
						Category cate = (Category) categoryservice.findByCategoryname(category).getFirst();
						String active = req.getParameter("status");
						String videodescription = req.getParameter("videodescription");
						
						
						String file = req.getParameter("file");
						
						Video video = Video.builder().videoId(videoId).title(videotitle).views(videocount).category(cate).active(active).description(videodescription).images(fileName).build();
						
						
						videoservice.insert(video);
						part.write(uploadPath + File.separator + fileName);
					}

				}
				req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		if(url.contains("/admin/video/search")) {
			String name = req.getParameter("search");
			List<Video> list = videoservice.findByTitle(name);
			System.out.println(list);
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}
		if (url.contains("/admin/video/edit")) {
			System.out.print("Cuc cu 2222");
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project

			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				System.out.println("Nha anh trong mit thai 787");
				String fileName = "";
				String id = req.getParameter("id");
				String categoryname = req.getParameter("categoryname");
				int status = Integer.parseInt(req.getParameter("status"));
				String images = fileName;
				// vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì
				// duyệt lại
				List<Part> fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName()))
						.collect(Collectors.toList());
				for (Part part : fileParts) {
					System.out.println("Nha anh trong mit thai 897");
					fileName = getFileName(part);
					if (fileName != "") {
						String videoId = req.getParameter("videoId");
						String videotitle = req.getParameter("videotitle");
						String videocount = req.getParameter("videocount");
						String category = req.getParameter("category");
						Category cate = (Category) categoryservice.findByCategoryname(category).getFirst();
						String active = req.getParameter("status");
						String videodescription = req.getParameter("videodescription");
						
						
						String file = req.getParameter("file");
						
						Video video = Video.builder().videoId(videoId).title(videotitle).views(videocount).category(cate).active(active).description(videodescription).images(fileName).build();
						videoservice.update(video);
						List<Category> listcate = categoryservice.findAll();
						req.setAttribute("video", video);
						req.setAttribute("categorylist",listcate);
						part.write(uploadPath + File.separator + fileName);
					} else {
						String videoId = req.getParameter("videoId");
						Video mdold = videoservice.findByVideoId(videoId);
						List<Category> listcate = categoryservice.findAll();
						req.setAttribute("categorylist",listcate);
						req.setAttribute("video", mdold);
						videoservice.update(mdold);
					}
				}
				req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
	}

}
