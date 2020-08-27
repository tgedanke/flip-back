package com.flippost.DAO.Samsung.NewDelivery.DAO;

import com.flippost.DAO.Samsung.NewDelivery.Models.SamsungNewDelivery;
import com.flippost.Tools.hibernate.HiberanteSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SamsungNewDeliveryDAOImpl implements SamsungNewDeliveryDAO {

    @Override
    public void insert(SamsungNewDelivery DELIVERY) {
        Session session = HiberanteSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(DELIVERY);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(SamsungNewDelivery DELIVERY) {
        Session session = HiberanteSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        session.update(DELIVERY);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(SamsungNewDelivery DELIVERY) {
        Session session = HiberanteSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        session.delete(DELIVERY);
        transaction.commit();
        session.close();
    }

    @Override
    public SamsungNewDelivery findById(int ID) {
        Session session = HiberanteSessionFactory.getSessionFactory().openSession();
        SamsungNewDelivery delivery = session.get(SamsungNewDelivery.class, ID);
        session.close();
        return delivery;
    }
}
