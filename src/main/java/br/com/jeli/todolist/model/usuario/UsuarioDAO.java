package br.com.jeli.todolist.model.usuario;

import javax.persistence.EntityManager;

public class UsuarioDAO {

    private EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Usuario usuario) {
        this.em.persist(usuario);
    }
    public Usuario buscarPorId(String id) {
        return this.em.find(Usuario.class, id);
    }
}
