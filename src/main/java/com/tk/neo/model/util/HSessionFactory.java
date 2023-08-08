package com.tk.neo.model.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tk.neo.model.entity.Book;
import com.tk.neo.model.entity.Person;

/**
 * This class may be used if the application works without Spring beans
 */
@Deprecated 
public class HSessionFactory {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Book.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

}
