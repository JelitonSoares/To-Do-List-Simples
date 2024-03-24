package br.com.jeli.todolist.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("to_do_list");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
