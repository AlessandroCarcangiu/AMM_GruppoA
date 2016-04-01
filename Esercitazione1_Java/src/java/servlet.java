/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alessandro
 */
@WebServlet(urlPatterns = {"/servlet"})
public class servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");        
        Esame esame = new Esame();
        
        if(request.getParameter("submit") != null)
        {
            // Preleva i dati inviati
            String nomeStudente = request.getParameter("NomeStudente");
            String cognomeStudente = request.getParameter("CognomeStudente");
            String matricola = request.getParameter("Matricola");
            String nomeEsame = request.getParameter("ListaEsami");
            String voto = request.getParameter("Voto");
            String commentoProf = request.getParameter("Descrizione");
            
            // Assegna i dati prelevati
            esame.setNomeStudente(nomeStudente);
            esame.setCognomeStudente(cognomeStudente);
            esame.setMatricolaStudente(matricola);
            esame.setNomeEsame(nomeEsame);
            esame.setVoto(voto);
            esame.setCommentoProf(commentoProf);
        }
        
        // Stampa dati
        try (PrintWriter out = response.getWriter()) 
        {
            out.println("<html>");
            out.println("<head><title>demolet</title></head>");
            out.println("<body>");
            out.println("<p>Nome studente:"+esame.getNomeStudente()+"</p>");
            out.println("<p>Cognome studente:"+esame.getCognomeStudente()+"</p>");
            out.println("<p>Matricola studente:"+esame.getMatricolaStudente()+"</p>");
            out.println("<p>Esame:"+esame.getNomeEsame()+"</p>");
            out.println("<p>Voto:"+esame.getVoto()+"</p>");
            out.println("<p>Commento:"+esame.getCommentoProf()+"</p>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
