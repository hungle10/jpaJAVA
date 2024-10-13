package vn.iostar.controllers.admin;

import vn.iostar.services.*;
import vn.iostar.services.Impl.CategoryServiceImpl;
import vn.iostar.entity.*;

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
import jakarta.servlet.jsp.PageContext;

@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete", "/admin/category/search" })
@MultipartConfig()
public class CategoryController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryServiceImpl();
	public static final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\git\\jpaJAVA\\jpact5\\src\\main\\webapp\\uploads";
	public static final String DEFAULT_FILENAME = "default.file";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("categories")) {

			List<Category> list = cateService.findAll();

			req.setAttribute("listcate", list);
			System.out.println(list);

			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

		}
		if (url.contains("/admin/category/edit")) {
			HttpSession session = req.getSession();
			int id = Integer.parseInt(req.getParameter("id"));
			Category cate = cateService.findById(id);
			session.setAttribute("cate", cate);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		if (url.contains("/admin/category/delete")) {
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
				Category cate = cateService.findById(Integer.parseInt(req.getParameter("id")));
				fileName = cate.getImages();

				if (fileName != "") {
					int id = Integer.parseInt(req.getParameter("id"));
					cateService.delete(id);
					File fileToDelete = new File(uploadPath + File.separator + fileName);
					fileToDelete.delete();
				}
				req.setAttribute("message", "File " + fileName + " has been deleted successfully!");
			} catch (Exception e) {

			}
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}

		System.out.println("Da goi 5");
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
		System.out.print("Cuc cu do post");
		String url = req.getRequestURI();
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		if (url.contains("/admin/category/edit")) {
			System.out.println("Nha anh trong mit thai");
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
						images = fileName;
						Category category = Category.builder().categoryId(Integer.parseInt(id))
								.categoryname(categoryname).images(images).status(status).build();
						req.setAttribute("cate", category);
						System.out.println("Nha anh trong mit thai");
						cateService.update(category);
						System.out.println("Nha anh trong mit thai 123");
						part.write(uploadPath + File.separator + fileName);
					} else {
						id = req.getParameter("id");
						Category mdold = cateService.findById(Integer.parseInt(id));
						req.setAttribute("cate", mdold);
						cateService.update(mdold);
					}
				}
				req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		if (url.contains("/admin/category/insert")) {
			System.out.print("Cuc cu 2222");
			String uploadPath = File.separator + UPLOAD_DIRECTORY; // upload vào thư mục bất kỳ
			// String uploadPath = getServletContext().getRealPath("") + File.separator +
			// UPLOAD_DIRECTORY; //upload vào thư mục project

			System.out.print(uploadPath);
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
						String categoryname = req.getParameter("categoryname");
						int status = Integer.parseInt(req.getParameter("status"));
						String images = fileName;
						Category category = Category.builder().categoryname(categoryname).images(images).status(status)
								.build();
						cateService.insert(category);
						part.write(uploadPath + File.separator + fileName);
					}

				}
				req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			} catch (FileNotFoundException fne) {
				req.setAttribute("message", "There was an error: " + fne.getMessage());
			}
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		if(url.contains("/admin/category/search")) {
			String name = req.getParameter("search");
			List<Category> list = cateService.findByCategoryname(name);
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}

	}

}
