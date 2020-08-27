package com.flippost.DAO.Samsung.NewDelivery.DAO;

import com.flippost.DAO.Samsung.NewDelivery.Models.SamsungNewDelivery;

public interface SamsungNewDeliveryDAO {

    void insert(final SamsungNewDelivery DELIVERY);

    void update(final SamsungNewDelivery DELIVERY);

    void delete(final SamsungNewDelivery DELIVERY);

    SamsungNewDelivery findById(final int ID);


}
