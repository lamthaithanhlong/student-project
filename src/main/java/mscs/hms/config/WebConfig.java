package mscs.hms.config;

import mscs.hms.controller.converters.StringToLegalEntityConverterFactory;
import mscs.hms.service.LegalEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LegalEntityService legalEntityService;
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToLegalEntityConverterFactory(legalEntityService));
    }
}
