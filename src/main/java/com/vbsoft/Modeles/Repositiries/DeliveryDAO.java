package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.In.PKFInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий Заказов Samsung.
 *
 * @author vd.zinovev
 */
@Repository
public interface DeliveryDAO extends CrudRepository<PKFInfo, Long> {

    /**
     * Находит информацию о заказе по номеру документа.
     *
     * @param number Номер документа
     * @return Список заказов
     */
    List<PKFInfo> findByDocumentNumber(String number);

    /**
     * Находит первый документ по его номеру.
     *
     * @param number Номер документа
     * @return Информация о заказе
     */
    PKFInfo findFirstByDocumentNumber(String number);

    /**
     * Находит информацию о неотправленном заказе.
     *
     * По условиям:
     * "askansSend" == false
     * И "askansSend" != Null
     * И "tryCount" != null
     * И "tryCount" != *Значение*
     * @return Заказы
     */
    List<PKFInfo> findByAskansSendIsFalseAndAskansSendIsNotNullAndTryCountIsNotNullAndTryCountIsNot(int tryCount);
}
