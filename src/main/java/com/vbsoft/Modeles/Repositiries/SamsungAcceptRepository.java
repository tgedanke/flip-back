package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.Service.SamsungOrderAccept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SamsungAcceptRepository extends JpaRepository<SamsungOrderAccept, Long> {

    @Procedure("sp_samsungAcceptOrder")
    void acceptOrder(@Param("ID") Long ID, @Param("accepted") Boolean accepted);
}
