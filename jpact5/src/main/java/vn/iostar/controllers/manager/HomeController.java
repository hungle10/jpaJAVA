package vn.iostar.controllers.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.util.List;
import java.util.stream.Collectors;
import vn.iostar.dao.*;
import vn.iostar.dao.Impl.UserDAOImpl;
import vn.iostar.entity.User;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/manager/home", "/manager/User" })
@MultipartConfig()
public class HomeController extends HttpServlet {

	IUserDAO dao = new UserDAOImpl();
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repository\\ltwebct5\\src\\main\\webapp\\uploads";
	public static final String DEFAULT_FILENAME = "default.file";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Set the content  
		String path = req.getServletPath();

		// Kiểm tra xem URL có chứa 'forgotPassword' hay không
		if (path.contains("/manager/home")) {
			List<User> users = dao.findAll();
			req.setAttribute("listuser", users);
			req.getRequestDispatcher("/views/listUser.jsp").forward(req, resp);
		} else if (path.contains("manager/User")) {

			resp.setContentType("text/html;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");

			String action = req.getParameter("action");
			System.out.print(action);
			
			
			System.out.print("Thanh cong");
			if ("AddOrEdit".equals(action)) {
				System.out.print("Thanh cong");
				String id = req.getParameter("id");
				User u = dao.findByUsername("username");

				if (u != null)
					req.setAttribute("USER", u);
				req.setAttribute("ACTION", "SaveOrUpdate");
				req.getRequestDispatcher("/views/editUser.jsp").forward(req, resp);
				System.out.print("Thanh cong");
			}
			else
			{
				System.out.print("That bai");
			}

		} else {
			// Nếu có URL khác không khớp
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			System.out.print("Thanh cong");
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
		String action = req.getParameter("action");
		System.out.print("Thanh cong 0");
		String id = req.getParameter("id");
		IUserDAO dao = new UserDAOImpl();
		User user= dao.findByUsername("username");
		if ("SaveOrUpdate".equals(action)) {
			System.out.print("Thanh cong 1");
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project
			
			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			System.out.print("Thanh cong 2");
			if (!uploadDir.exists())
				uploadDir.mkdir();
			try {
				String fileName = "";
				//vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì duyệt lại 
				 List<Part> fileParts = req.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
				for (Part part : fileParts) {
					fileName = getFileName(part);
					if(fileName!=null)
					{
					user.setImages(fileName);
					dao.update(user);
					part.write(uploadPath + File.separator + fileName);
					System.out.print("Thanh cong 3");
					}
				}
				req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			List<User> users = dao.findAll();
			req.setAttribute("listuser", users);
			getServletContext().getRequestDispatcher("/views/listUser.jsp").forward(req, resp);
		}
	}
}
