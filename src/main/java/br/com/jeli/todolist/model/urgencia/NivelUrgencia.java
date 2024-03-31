package br.com.jeli.todolist.model.urgencia;

import javax.persistence.*;

@Entity
@Table(name = "niveis_urgencia")
public class NivelUrgencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public NivelUrgencia(Categoria categoria) {
        this.categoria = categoria;
    }

    public NivelUrgencia() {
    }

    public Long getId() {
        return id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Urgencia{" +
                "categoria='" + categoria + '\'' +
                '}';
    }
}
