package com.vbsoft.Tools;


import com.vbsoft.Modeles.In.Enums.BusinessTypeCode;
import com.vbsoft.Utils.Tools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToolsTest {

    @Test
    public void toolsCheck() {
        BusinessTypeCode code = Tools.getEnumByAnnotation(BusinessTypeCode.class, "EX");
        assertNotNull(code, "Не найден элемент перечесления со значением аннотации 'EX'");
        assertTrue(code.getValue().equalsIgnoreCase("Export"), "Значние перечесленеия не равно 'Export'");
        assertTrue(code.name().equalsIgnoreCase("Export"), "Имя перечесленеия не равно 'Export'");
    }

}
