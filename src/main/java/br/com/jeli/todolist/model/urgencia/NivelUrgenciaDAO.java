package br.com.jeli.todolist.model.urgencia;

import br.com.jeli.todolist.util.JPAUtil;

import javax.persistence.EntityManager;

public class NivelUrgenciaDAO {
    private EntityManager em;

    public NivelUrgenciaDAO(EntityManager em) {
        this.em = em;
    }

    public void salvar(NivelUrgencia urgencia) {
        this.em.persist(urgencia);
    }
}
