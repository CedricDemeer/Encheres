package org.encheres.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class images
 */
@WebServlet(name="images", urlPatterns = {"/images/*"})
public class images extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public images() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Chargement d'une image depuis un emplacement sécurisé du serveur
		System.out.println(request.getContextPath());
        BufferedImage image = getImage(request.getRequestURI());
        //Définition de l'en-tête de la réponse
        response.setContentType("image/jpeg");
        //Sérialisation de l'image dans la réponse
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        out.close();
	}

	/** Méthode qui fournit une image */
    private BufferedImage getImage(String uri) throws IOException {
    	String sContext = this.getServletContext().getRealPath("/");
		sContext += "images\\upload\\";
    	sContext += uri.substring("/Encheres/images/".length());
    	System.out.println();
		return ImageIO.read(new File(sContext));
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
