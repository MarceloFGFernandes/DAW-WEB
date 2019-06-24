/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.conversores;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author marce
 */
@FacesConverter(value = "converterPessoa")
public class ConvertePessoa implements Serializable, Converter{
    //converter a informação do usuário (tela) para o controle
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equalsIgnoreCase("Selecione um registro")){
            return null;
        }else{
            return EntityManagerUtil.getEntityManager().find(Pessoa.class, Integer.parseInt(string));
        }
    }

    //Busca as informações pelo conttrole e converte para mostrar na tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null)
            return null;
        Pessoa p = (Pessoa) o;
        return p.getId().toString();
    }
        
}
