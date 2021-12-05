package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.In.PKFInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDAO extends CrudRepository<PKFInfo, Long> {

    PKFInfo findByDocumentNumber(String number);

}
