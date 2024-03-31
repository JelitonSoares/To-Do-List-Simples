package br.com.jeli.todolist.service;

import br.com.jeli.todolist.model.sessao.Sessao;
import br.com.jeli.todolist.model.usuario.Usuario;
import br.com.jeli.todolist.model.usuario.UsuarioDAO;
import br.com.jeli.todolist.util.JPAUtil;

import javax.persistence.EntityManager;

public class UsuarioService {

    private EntityManager em;
    private UsuarioDAO usuarioDAO;
    private Sessao atual;


    public UsuarioService(){
        this.em = JPAUtil.getEntityManager();
        this.usuarioDAO = new UsuarioDAO(em);
    }

    public void cadastrar(Usuario usuario) {
        this.em.getTransaction().begin();
        this.usuarioDAO.cadastrar(usuario);
        this.em.getTransaction().commit();
    }

    public boolean entrar(String id, String senha) {
        Usuario usuario = buscarUsuarioPorId(id);

        if(senha.equals(usuario.getSenha())) {
            this.atual = new Sessao(usuario);
            return true;
        }

        throw new RuntimeException("Senha não confere!!");
    }

    private Usuario buscarUsuarioPorId(String id) {
        return usuarioDAO.buscarPorId(id);
    }

    public String getNomeSessaoAtual() {
        return this.atual.getNomeSessao();
    }

    public Usuario getUsuarioSessaoAtual() {
        return atual.getUsuario();
    }

}
