package com.vbsoft.Modeles.Service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedStoredProcedureQuery(
        name = "sp_createSamsungError",
        procedureName = "sp_createSamsungError",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "message", type = String.class)
        })
public class SamsungError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
}
