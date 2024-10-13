package vn.iostar.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*	HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) {
			UserModel u = (UserModel) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if (u.getRoleid() == 2) {
				resp.sendRedirect(req.getContextPath() + "/admin/home");
				return;
			} else if (u.getRoleid() == 3) {
				resp.sendRedirect(req.getContextPath() + "/manager/home");
				return;
			} else {
				resp.sendRedirect(req.getContextPath() + "/home");
				return;
			}
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {// string
					session = req.getSession(true);
					String username = (String) session.getAttribute("username");
					IUserDAO ud = new UserDAOImpl();
					UserModel u = ud.findByUserName(username);
					System.out.println(u.getUsername());
					req.setAttribute("username", u.getUsername());
					if (u.getRoleid() == 2) {
						resp.sendRedirect(req.getContextPath() + "/admin/home");
						return;
					} else if (u.getRoleid() == 3) {
						resp.sendRedirect(req.getContextPath() + "/manager/home");
						return;
					} else {
						resp.sendRedirect(req.getContextPath() + "/home");
						return;
					}
				}
			}
		}
	   resp.sendRedirect(req.getContextPath() + "/login");*/
	}
}
