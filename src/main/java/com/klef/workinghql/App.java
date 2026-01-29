package com.klef.workinghql;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.klef.workinghql.entity.Product;
import com.klef.workinghql.util.HibernateUtil;


public class App {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        // Sorting by price ASC
        List<Product> products =
            session.createQuery("FROM Product p ORDER BY p.price ASC", Product.class).list();

        // Pagination
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(0);
        q.setMaxResults(3);

        // Aggregate
        Long count = session.createQuery(
            "SELECT COUNT(p) FROM Product p", Long.class).uniqueResult();

        System.out.println("Total products: " + count);

        session.close();
    }
}
