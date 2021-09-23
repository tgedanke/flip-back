package com.vbsoft.Tools;


import com.vbsoft.Utils.Tools;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolsTest {

    @Test
    public void toolsCheck() {
        Assertions.assertNotNull(Tools.getApplicationConfigContext(), "Не удалось получить контекст прилодения");
    }

}
