/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.esercitazione2;

import amm.esercitazione2.Classi.UtentiFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alessandro
 */
@WebServlet(name = "Registra", urlPatterns = {"/Registra"})
public class Registra extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        int id = Integer.parseInt(request.getParameter("alunnoId"));
        request.setAttribute("alunno", UtentiFactory.getInstance().getStudente(id));
        request.setAttribute("professore", UtentiFactory.getInstance()
                .getProfessore((int)session.getAttribute("id")));
        
        // Registrazione esame (solo se l'utente ha premuto il tasto 'submit'
        if(request.getParameter("submit") != null)
        {
            // Preleva i dati da registrare
            int idStudente = Integer.parseInt(request.getParameter("alunnoId"));
            int idMateria = UtentiFactory.getInstance().getMateria(request.getParameter("listaEsami")).getId();
            int voto = Integer.parseInt(request.getParameter("voto"));
            String descrizione = request.getParameter("descrizione");
            
            try
            {
                registrazioneEsame(idStudente, idMateria, 
                        voto, descrizione);
            }catch(SQLException e)
            {}
        }              
        
        request.getRequestDispatcher("form_registra.jsp").forward(request, response);
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
            throws ServletException, IOException {
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
    
    private void registrazioneEsame(int idStudente, int idMateria,
            int voto, String descrizione) throws SQLException
    {
        Connection conn = DriverManager.getConnection(
                UtentiFactory.getInstance().getConnectionString(),
                "alessandrocarcangiu",
                "0000");
        
        PreparedStatement updatePianodiStudi = null;
        PreparedStatement updateEsami_superati = null;
        
        // Sql 
        String deletePianodiStudi = "delete from pianodistudi "
                + "where idMateria = ? "
                + "and idStudente = ?";
        String insertEsami_Superati = "insert into esami_superati "
                + "(idMateria, idStudente, voto, descrizione) "
                + "values (?,?,?,?)";
        
        try
        {
           conn.setAutoCommit(false);
           updatePianodiStudi = conn.
                   prepareStatement(deletePianodiStudi);
           updateEsami_superati = conn.
                   prepareStatement(insertEsami_Superati);
           
           // PianodiStudi
           updatePianodiStudi.setInt(1, idMateria);
           updatePianodiStudi.setInt(2, idStudente);
           // Esami_superati
           updateEsami_superati.setInt(1, idMateria);
           updateEsami_superati.setInt(2, idStudente);
           updateEsami_superati.setInt(3, voto);
           updateEsami_superati.setString(4, descrizione);
           
           int c1 = updatePianodiStudi.executeUpdate();
           int c2 = updateEsami_superati.executeUpdate();
           
           if(c1 != 1 || c2 != 1)
               conn.rollback();
           
           conn.commit();           
        }catch(SQLException e)
        {
            try
            {
                conn.rollback();
            }catch(SQLException e2)
            {
                
            }
        }
        finally
        {
            if(updatePianodiStudi != null)
                updatePianodiStudi.close();
            if(updateEsami_superati != null)
                updateEsami_superati.close();
            
            conn.setAutoCommit(true);
            conn.close();
        }    
    }
}
