package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.Service.SamsungState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий создания ошибок сервиса.
 *
 * @author vd.zinovev
 */
@Repository
public interface SamsungErrorRepository extends JpaRepository<SamsungState, Long> {

    /**
     * Создать ошибку
     *
     * @param message Сообщение об ошибке
     */
    @Procedure(value = "sp_createSamsungError")
    void createError(@Param("message") String message);

}
