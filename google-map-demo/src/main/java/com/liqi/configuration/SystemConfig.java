package com.liqi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.NumberFormat;

/**
 * Title: SystemConfig
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/24
 */
@Configuration
public class SystemConfig {
    @Value("${system.pros.name}")
    public String name;
    @Value("${system.pros.port}")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    public int port;
}
