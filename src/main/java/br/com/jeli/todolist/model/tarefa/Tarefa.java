package br.com.jeli.todolist.model.tarefa;

import br.com.jeli.todolist.model.urgencia.NivelUrgencia;
import br.com.jeli.todolist.model.usuario.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarefa")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String tarefa;
    @ManyToOne
    @JoinColumn(name = "nivel_urgencia_id")
    private NivelUrgencia nivelUrgencia;
    private Boolean completa = false;

    public Tarefa(Usuario usuario, String tarefa, NivelUrgencia urgencia) {
        this.usuario = usuario;
        this.tarefa = tarefa;
        this.nivelUrgencia = urgencia;
    }

    public Tarefa() {
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTarefa() {
        return tarefa;
    }

    public NivelUrgencia getUrgencia() {
        return nivelUrgencia;
    }

    public Boolean getCompleta() {
        return completa;
    }

    public void marcarTarefa() {
        this.completa = true;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public void setNivelUrgencia(NivelUrgencia nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", tarefa='" + tarefa + '\'' +
                ", nivelUrgencia=" + nivelUrgencia +
                ", completa=" + completa +
                '}';
    }
}
