package com.hibernate;

import com.hibernate.dao.AuthorDAO;
import com.hibernate.entity.Author;

import java.util.List;

public class Start {

    public static void main(String[] args) {
        System.out.println("Im worked");

        System.out.println(AuthorDAO.getAuthorById(1L).getName());

        List<Author> authors = AuthorDAO.getAuthors();

        for (Author author : authors) {
            System.out.println(author.getName());
        }
    }
}
