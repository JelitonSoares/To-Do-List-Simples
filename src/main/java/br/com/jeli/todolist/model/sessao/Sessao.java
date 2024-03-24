package br.com.jeli.todolist.model.sessao;

import br.com.jeli.todolist.model.usuario.Usuario;

public class Sessao {

    private String nomeSessao;

    public Sessao(Usuario usuario) {
        this.nomeSessao = usuario.getNome();
    }

    public String getNomeSessao() {
        return this.nomeSessao;
    }
}
