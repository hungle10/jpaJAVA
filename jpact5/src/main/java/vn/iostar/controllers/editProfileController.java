package vn.iostar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import vn.iostar.dao.*;
import vn.iostar.dao.Impl.UserDAOImpl;
import vn.iostar.entity.User;


/**
 * Servlet implementation class editProfileController
 */
@WebServlet(urlPatterns = "/editProfile")
@MultipartConfig
public class editProfileController extends HttpServlet {
	IUserDAO dao = new UserDAOImpl();
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\repository\\ltwebct5\\src\\main\\webapp\\uploads";
	public static final String DEFAULT_FILENAME = "default.file";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletrequestuest requestuest, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletrequestuest requestuest, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		IUserDAO dao = new UserDAOImpl();
		User user= dao.findByUsername("username");
		
		if(request.getParameter("fullname")!=null)
			user.setFullname((String)request.getParameter("fullname"));
		if(request.getParameter("phone")!=null)
			user.setPhone((String)request.getParameter("phone"));
		
		
		
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project
			
			System.out.print(uploadPath);
			File uploadDir = new File(uploadPath);
			System.out.print("Thanh cong 2");
			if (!uploadDir.exists())
				uploadDir.mkdir();
			System.out.print("Thanh cong 22");
			try {
				String fileName = "";
				//vì đọc(dịch input) thành cái part chỉ 1 lần nên cần lưu trong list để cần thì duyệt lại 
				 List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
				for (Part part : fileParts) {
					fileName = getFileName(part);
					System.out.print(fileName);
					if(fileName.equals("")!=true)
					{
					user.setImages(fileName);
					dao.update(user);
					part.write(uploadPath + File.separator + fileName);
					}
				}
				request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				request.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			request.setAttribute("user",user);
			getServletContext().getRequestDispatcher("/views/editProfile.jsp").forward(request, response);
		}

	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return DEFAULT_FILENAME;
	}


}
