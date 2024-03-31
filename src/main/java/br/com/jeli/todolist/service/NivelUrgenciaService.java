package br.com.jeli.todolist.service;

import br.com.jeli.todolist.model.urgencia.NivelUrgencia;
import br.com.jeli.todolist.model.urgencia.NivelUrgenciaDAO;
import br.com.jeli.todolist.util.JPAUtil;

import javax.persistence.EntityManager;

public class NivelUrgenciaService {

    private EntityManager em;
    private NivelUrgenciaDAO nivelUrgenciaDAO;

    public NivelUrgenciaService() {
        this.em = JPAUtil.getEntityManager();
        this.nivelUrgenciaDAO = new NivelUrgenciaDAO(em);
    }

    public void cadastrar(NivelUrgencia urgencia) {
        this.em.getTransaction().begin();
        nivelUrgenciaDAO.salvar(urgencia);
        this.em.getTransaction().commit();
    }
}
