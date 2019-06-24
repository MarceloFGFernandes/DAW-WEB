/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author marce
 */
@ManagedBean(name = "controleVoo")
@ViewScoped
public class ControleVoo implements Serializable {

    private Voo obj;
    private VooDAO<Voo> dao;
    private Aeroporto aeroporto;
    private AeroportoDAO<Aeroporto> daoAeroporto;
    private VooAgendado vooAgendado;
    private Boolean novoVooAgendado;

    public ControleVoo() {
        dao = new VooDAO<>();
        daoAeroporto = new AeroportoDAO<>();
    }

    public Voo getObj() {
        return obj;
    }

    public void setObj(Voo obj) {
        this.obj = obj;
    }

    public VooDAO<Voo> getDao() {
        return dao;
    }

    public void setDao(VooDAO<Voo> dao) {
        this.dao = dao;
    }

    public String listar() {
        return "/privado/voo/listar?faces-redirect=true";
    }

    public void novo() {
        obj = new Voo();
    }

    public void salvar() {
        boolean persistiu;
        if (obj.getId() == null) {
            persistiu = dao.persist(obj);
        } else {
            persistiu = dao.merge(obj);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());

        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public void editar(Integer id) {
        try {
            obj = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        obj = dao.localizar(id);
        if (dao.remove(obj)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }

    public void adicionarEscalasAeroporto() {
        if (aeroporto != null) {
            if (!obj.getListaEscalas().contains(aeroporto)) {
                obj.adicionarEscalasAeroporto(aeroporto);
                Util.mensagemInformacao("Aeroporto adicionado com sucesso!");
            } else {
                Util.mensagemErro("Aeroporto já existe na lista de Escalas!");
            }
        }
    }

    public void removerEscalasAeroporto(int indice) {
        Object[] lista = obj.getListaEscalas().toArray();
        Aeroporto a = (Aeroporto) lista[indice];
        obj.getListaEscalas().remove(a);
        Util.mensagemInformacao("Aeroporto removido com sucesso!");
    }

    public Aeroporto getAeroporto() {
        return aeroporto;
    }

    public void setAeroporto(Aeroporto aeroporto) {
        this.aeroporto = aeroporto;
    }

    public AeroportoDAO<Aeroporto> getDaoAeroporto() {
        return daoAeroporto;
    }

    public void setDaoAeroporto(AeroportoDAO<Aeroporto> daoAeroporto) {
        this.daoAeroporto = daoAeroporto;
    }

    public VooAgendado getVooAgendado() {
        return vooAgendado;
    }

    public void setVooAgendado(VooAgendado vooAendado) {
        this.vooAgendado = vooAendado;
    }

    public Boolean getNovoVooAgendado() {
        return novoVooAgendado;
    }

    public void setNovoVooAgendado(Boolean novoVooAgendado) {
        this.novoVooAgendado = novoVooAgendado;
    }
    
     public void novoVooAgendado(){
        vooAgendado = new VooAgendado();
        novoVooAgendado = true;
    }
    
    public void editarVooAgendado(int indice){
        vooAgendado = obj.getVoosAgendados().get(indice);
        novoVooAgendado = false;
    }
    
    public void salvarVooAgendado(){
        if(novoVooAgendado)
            obj.adicionarVooAgendado(vooAgendado);
        Util.mensagemInformacao("Voo Agendado persistido com sucesso!");
    }
    
    public void removerVooAgendado(int indice){
        obj.removerVooAgendado(indice);
        Util.mensagemInformacao("Voo Agendado removido com sucesso!");
    }


}
