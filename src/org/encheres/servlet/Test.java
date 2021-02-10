package org.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.bll.EnchereManager;
import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.Enchere;
import org.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
@MultipartConfig
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setIntHeader("/Test", 5);
		 
		
		response.setContentType("text/html;charset=UTF-8");
	 
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
	}

   
}
