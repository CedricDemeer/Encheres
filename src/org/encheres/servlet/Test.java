package org.encheres.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.encheres.bll.UtilisateurManager;
import org.encheres.bo.ArticleVendu;
import org.encheres.bo.TestSaveFile;
import org.encheres.bo.Utilisateur;
import org.encheres.dal.UtilisateurDAO;
import org.encheres.dal.UtilisateurDAOJDBCImpl;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
@MultipartConfig
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final int TAILLE_TAMPON = 10240;
	public static final String CHEMIN_FICHIERS = "/images/upload"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utilisateur> utilisateurs=new ArrayList<>();
		UtilisateurManager test=new UtilisateurManager();
		
		utilisateurs=test.selectToutLesUtilisateur();
		request.setAttribute("liste", utilisateurs);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // On récupère le champ description comme d'habitude
        String description = request.getParameter("description");
        request.setAttribute("description", description );

        // On récupère le champ du fichier
        Part part = request.getPart("photo");

        // Si on a bien un fichier
        if (part != null && part.getSize() > 0) {
        	
        	//On récupère le nom du fichier
        	String nomImage = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		     
        	//séparer le nom et l’extension
		    String[] fn = nomImage.split("(\\.)");

		    //stocker l’extension
		    String ext = fn[(fn.length-1)];
		    
		    if(!ext.isEmpty()) {
		    	//mettre en place un mécanisme ici, pour générer  
		    	//un nom de fichier unique afin d’éviter les écrasements de fichier
		    	UUID uuid = UUID.randomUUID();
		    	String random = uuid.toString();
		    	//recréer le nom complet
			     nomImage = random.toLowerCase() + "." + ext;
			     
			     InputStream fileContent = part.getInputStream();
			   //Version Production
			     //String sContext = //this.getServletContext().getRealPath("/");
			   
			     //TODO : A supprimer pour la production
			     //Version eclipse (indiquer en dur le répertoire de stockage des images sur le serveur
			     String sContext = "C:/"+ request.getContextPath() + "/WebContent/images/upload";
			    
			     File f = new File(sContext + "/images/upload/" + nomImage);
			     try {
			    	 TestSaveFile.receiveFile(fileContent, f); 
				 //en base de données, ne stocker que le chemin d’accès et le nom du fichier image
			    	 System.out.println(nomImage);
			    	 //art.setImage(nomImage); 
			     }
			     catch(IOException e) {
			    	 e.printStackTrace();
			     }
			     
		    }
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp").forward(request, response);
        
	}

    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    } 
}
