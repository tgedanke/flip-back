package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.Service.SamsungState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий обновления состояния сервиса.
 *
 * @author vd.zinovev
 */
@Repository
public interface SamsungStateRepository extends JpaRepository<SamsungState, Long> {

    /**
     * Обновить состояние.
     */
    @Procedure(value = "sp_samsungUpdateState")
    void updateState();


}
