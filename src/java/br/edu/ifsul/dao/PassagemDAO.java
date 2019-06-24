/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Passagem;
import java.io.Serializable;

/**
 *
 * @author marce
 */
public class PassagemDAO<TIPO> extends DAOGenerico<Passagem> implements Serializable{

    public PassagemDAO() {
        super();
        classePersistente = Passagem.class;
    }
    
}
