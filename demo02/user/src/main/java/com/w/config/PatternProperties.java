package com.w.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author me
 * @create 2022-08-17-22:24
 */
@Data
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    public String dateformat;
}
