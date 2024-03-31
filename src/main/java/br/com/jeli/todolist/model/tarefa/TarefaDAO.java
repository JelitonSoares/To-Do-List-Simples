package br.com.jeli.todolist.model.tarefa;



import br.com.jeli.todolist.model.urgencia.NivelUrgencia;

import javax.persistence.EntityManager;
import java.util.List;

public class TarefaDAO {
    private EntityManager em;

    public TarefaDAO(EntityManager em) {
        this.em = em;
    }

    public List<Tarefa> listarTarefa() {
        String jpql = "SELECT t FROM Tarefa t";
        return this.em.createQuery(jpql, Tarefa.class).getResultList();
    }

    public void marcarTarefa(Long id) {
        this.em.getTransaction().begin();
        Tarefa tarefa = buscarPorId(id);
        tarefa.marcarTarefa();
        this.em.getTransaction().commit();
    }

    public void atualizarNome(Long id, String nome) {
        this.em.getTransaction().begin();
        Tarefa tarefa = buscarPorId(id);
        tarefa.setTarefa(nome);
        this.em.getTransaction().commit();
    }

    public void atualizarUrgencia(Long id, NivelUrgencia urgencia) {
        this.em.getTransaction().begin();
        Tarefa tarefa = buscarPorId(id);
        tarefa.setNivelUrgencia(urgencia);
        this.em.getTransaction().commit();
    }

    public void remover(Long id) {
        this.em.getTransaction().begin();
        Tarefa tarefa = buscarPorId(id);
        this.em.remove(tarefa);
        this.em.getTransaction().commit();
    }

    private Tarefa buscarPorId(Long id) {
        return this.em.find(Tarefa.class, id);
    }


    public void cadastrar(Tarefa tarefa) {
        this.em.persist(tarefa);
    }
}
