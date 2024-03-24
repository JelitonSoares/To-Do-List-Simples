package br.com.jeli.todolist.service;

import br.com.jeli.todolist.model.tarefa.Tarefa;
import br.com.jeli.todolist.model.tarefa.TarefaDAO;
import br.com.jeli.todolist.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TarefaService {
    private EntityManager em;
    private TarefaDAO tarefaDAO;

    public TarefaService() {
        this.em = JPAUtil.getEntityManager();
        this.tarefaDAO = new TarefaDAO(em);
    }


    public void listar() {
        List<Tarefa> tarefaList =  this.tarefaDAO.listarTarefa();
        System.out.println(tarefaList);
        System.out.println("Enter para continuar: ");
    }
}
