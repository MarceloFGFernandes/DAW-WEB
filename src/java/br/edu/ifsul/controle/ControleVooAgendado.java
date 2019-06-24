/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.VooAgendadoDAO;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author marce
 */

@ManagedBean(name = "controleVooAgendado")
@ViewScoped
public class ControleVooAgendado implements Serializable{
    private VooAgendado obj;
    private VooAgendadoDAO<VooAgendado> dao;
    

    public ControleVooAgendado() {
        dao = new VooAgendadoDAO<>();
      
    }
    

    public VooAgendado getObj() {
        return obj;
    }

    public void setObj(VooAgendado obj) {
        this.obj = obj;
    }

    public VooAgendadoDAO<VooAgendado> getDao() {
        return dao;
    }

    public void setDao(VooAgendadoDAO<VooAgendado> dao) {
        this.dao = dao;
    }
    
    public String listar(){
        return "/privado/vooAgendado/listar?faces-redirect=true";
    }
    
    public void novo(){
        obj = new VooAgendado();
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
