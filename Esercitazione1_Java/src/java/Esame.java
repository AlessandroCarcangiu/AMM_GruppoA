/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alessandro
 */
public class Esame {
    /* Attributi */
    private String nomeStudente = "nome";
    private String cognomeStudente = "cognome";
    private String matricolaStudente = "00000";
    private String nomeEsame = "esame";
    private String voto = "0";
    private String commentoProf = "-";
    
    /* Costruttore */
    public Esame()
    {
        
    }
    public Esame(String nomeStudente, String cognomeStudente, String matricolaStudente, String nomeEsame, String voto, String commentoProf)
    {
        this.nomeStudente = nomeStudente;
        this.cognomeStudente = cognomeStudente;
        this.matricolaStudente = matricolaStudente;
        this.nomeEsame = nomeEsame;
        this.voto = voto;
        this.commentoProf = commentoProf;
    }
    
    /* Metodi */

    /**
     * @return the nomeStudente
     */
    public String getNomeStudente() {
        return nomeStudente;
    }

    /**
     * @param nomeStudente the nomeStudente to set
     */
    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    /**
     * @return the cognomeStudente
     */
    public String getCognomeStudente() {
        return cognomeStudente;
    }

    /**
     * @param cognomeStudente the cognomeStudente to set
     */
    public void setCognomeStudente(String cognomeStudente) {
        this.cognomeStudente = cognomeStudente;
    }

    /**
     * @return the matricolaStudente
     */
    public String getMatricolaStudente() {
        return matricolaStudente;
    }

    /**
     * @param matricolaStudente the matricolaStudente to set
     */
    public void setMatricolaStudente(String matricolaStudente) {
        this.matricolaStudente = matricolaStudente;
    }

    /**
     * @return the nomeEsame
     */
    public String getNomeEsame() {
        return nomeEsame;
    }

    /**
     * @param nomeEsame the nomeEsame to set
     */
    public void setNomeEsame(String nomeEsame) {
        this.nomeEsame = nomeEsame;
    }

    /**
     * @return the voto
     */
    public String getVoto() {
        return voto;
    }

    /**
     * @param voto the voto to set
     */
    public void setVoto(String voto) {
        this.voto = voto;
    }

    /**
     * @return the commentoProf
     */
    public String getCommentoProf() {
        return commentoProf;
    }

    /**
     * @param commentoProf the commentoProf to set
     */
    public void setCommentoProf(String commentoProf) {
        this.commentoProf = commentoProf;
    }
}
