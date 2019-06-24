/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Voo;
import java.io.Serializable;

/**
 *
 * @author marce
 */
public class VooDAO<TIPO> extends DAOGenerico<Voo> implements Serializable{

    public VooDAO() {
        super();
        classePersistente = Voo.class;
    }
    
}
