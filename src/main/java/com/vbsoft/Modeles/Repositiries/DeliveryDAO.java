package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.In.PKFInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDAO extends CrudRepository<PKFInfo, Long> {

    List<PKFInfo> findByDocumentNumber(String number);

    PKFInfo findFirstByDocumentNumber(String number);

}
