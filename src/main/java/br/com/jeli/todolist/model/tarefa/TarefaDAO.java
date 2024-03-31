package br.com.jeli.todolist.model.tarefa;



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


    public void cadastrar(Tarefa tarefa) {
        this.em.persist(tarefa);
    }
}
