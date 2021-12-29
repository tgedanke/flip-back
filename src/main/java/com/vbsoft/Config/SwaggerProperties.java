package com.vbsoft.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private String host;
    private String projectVersion;
    private boolean enabled;
}
