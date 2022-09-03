package com.vbsoft.Modeles.Service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;

/**
 * Процедура обновления состояния сервиса.
 *
 * @author vd.zinovev
 */
@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NamedStoredProcedureQuery(
        name = "sp_samsungUpdateState",
        procedureName = "sp_samsungUpdateState"
)
public class SamsungState {

    /**
     * ID сервиса.
     */
    @Id
    String aName = "SamsungApi";

}
