package br.com.jeli.todolist.model.sessao;

import br.com.jeli.todolist.model.usuario.Usuario;

public class Sessao {

    private String nomeSessao;
    private Usuario usuario;

    public Sessao(Usuario usuario) {
        this.nomeSessao = usuario.getNome();
        this.usuario = usuario;
    }

    public String getNomeSessao() {
        return this.nomeSessao;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
}
