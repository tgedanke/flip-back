package com.vbsoft.Modeles.Service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.type.NumericBooleanType;

import javax.persistence.*;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedStoredProcedureQuery(
        name = "sp_samsungAcceptOrder",
        procedureName = "sp_samsungAcceptOrder",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ID", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "accepted", type = NumericBooleanType.class),
        })
public class SamsungOrderAccept {

    /**
     * ID.
     */
    @Id
    Long ID;
}
