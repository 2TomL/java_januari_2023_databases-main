package be.intecbrussel.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;

public class EMFConfiguration {
    public static EntityManagerFactory entityManagerFactory;
    public static EntityManagerFactory getConnection() {
        if (entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        }
        return entityManagerFactory;
    }
    private EMFConfiguration (){

    }
}