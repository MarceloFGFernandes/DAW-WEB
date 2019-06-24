/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author marce
 */

@ManagedBean(name = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable{
    private Pessoa obj;
    private PessoaDAO<Pessoa> dao;
    
    

    public ControlePessoa() {
        dao = new PessoaDAO<>();
      
    }
    
    

    public Pessoa getObj() {
        return obj;
    }

    public void setObj(Pessoa obj) {
        this.obj = obj;
    }

    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }
    
    public String listar(){
        return "/privado/pessoa/listar?faces-redirect=true";
    }
 
    public void novo(){
        obj = new Pessoa();
    }
    
    public void salvar(){
        boolean persistiu;
        if(obj.getId() == null){
            persistiu = dao.persist(obj);
        }else{
            persistiu = dao.merge(obj);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public void editar(Integer id){
        try {
            obj = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void remover(Integer id){
         obj = dao.localizar(id);
         if(dao.remove(obj))
             Util.mensagemInformacao(dao.getMensagem());
         else
             Util.mensagemErro(dao.getMensagem());
    }

  
}
