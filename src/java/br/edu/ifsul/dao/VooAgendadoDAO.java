/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.VooAgendado;
import java.io.Serializable;

/**
 *
 * @author marce
 */
public class VooAgendadoDAO<TIPO> extends DAOGenerico<VooAgendado> implements Serializable{

    public VooAgendadoDAO() {
        super();
        classePersistente = VooAgendado.class;
    }
    
}
