package com.hibernate.dao;

import com.hibernate.HibernateUtil;
import com.hibernate.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorDAO {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public static Author getAuthorById(Long authorId) {

        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, authorId);
        session.close();

        return author;
    }

    public static List<Author> getAuthors() {

        Session session = sessionFactory.openSession();

        //Подготовка к запросу
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);
        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root); //Для получения всех данных не обязательно
        //Запрос
        Query query = session.createQuery(criteriaQuery);
        List<Author> authors = query.getResultList();

        session.close();

        return authors;
    }

    public static void addAuthor(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(author);
        session.getTransaction().commit();
        session.close();
    }
}
