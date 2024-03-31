package br.com.jeli.todolist.service;

import br.com.jeli.todolist.model.tarefa.Tarefa;
import br.com.jeli.todolist.model.tarefa.TarefaDAO;
import br.com.jeli.todolist.model.urgencia.NivelUrgencia;
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
        tarefaList.forEach(System.out::println);
        System.out.println("Enter para continuar: ");
    }

    public void criarTarefa(Tarefa tarefa) {
        this.em.getTransaction().begin();
        tarefaDAO.cadastrar(tarefa);
        this.em.getTransaction().commit();
    }

    public void marcarTarefa(Long id) {
        tarefaDAO.marcarTarefa(id);
    }

    public void atualizarNomeTarefa(Long id, String nome) {
        tarefaDAO.atualizarNome(id, nome);
    }

    public void atualizarUrgencia(Long id, NivelUrgencia urgencia){
        tarefaDAO.atualizarUrgencia(id, urgencia);
    }

    public void removerTarefa(Long id) {
        tarefaDAO.remover(id);
    }
}
