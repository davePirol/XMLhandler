/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbparsexhtml;

import java.util.ArrayList;

/**
 *
 * @author Davide
 */
public class Prof {
    private String nome,materia,mail;
    private ArrayList giorni,ore;
    
    public Prof(String materia, String nome, String mail, ArrayList giorni, ArrayList ore) {
        this.nome = nome;
        this.materia = materia;
        this.mail = mail;
        this.giorni = giorni;
        this.ore=ore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList getGiorni() {
        return giorni;
    }

    public void setGiorni(ArrayList giorni) {
        this.giorni = giorni;
    }

    public ArrayList getOre() {
        return ore;
    }

    public void setOre(ArrayList ore) {
        this.ore = ore;
    }
    
    public String toString(){
        String s=materia+";"+nome+";"+mail+";";
        for(int i=0;i<giorni.size();i++)
            s+=giorni.get(i).toString()+"/";
        s+=";";
        for(int i=0;i<ore.size();i++)
            s+=ore.get(i).toString()+"/";
        s+=";";
        return s;
    }
    
    
}
