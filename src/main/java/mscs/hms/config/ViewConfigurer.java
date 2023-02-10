package mscs.hms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //ViewConfigurer.super.addViewControllers(registry);
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/users").setViewName("user_list");
        registry.addViewController("/user_edit").setViewName("user_edit");
        registry.addViewController("/register").setViewName("signup");
        registry.addViewController("/register_success").setViewName("register_success");
        registry.addViewController("/property_list").setViewName("property_list");
        registry.addViewController("/company_list").setViewName("company_list");        
        registry.addViewController("/company_edit").setViewName("company_edit");
        registry.addViewController("/house_list").setViewName("house_list");
        registry.addViewController("/house_edit").setViewName("house_edit");
        registry.addViewController("/apartment_list").setViewName("apartment_list");
        registry.addViewController("/apartment_edit").setViewName("apartment_edit");
        registry.addViewController("/legal_entity_list").setViewName("legal_entity_list");
        registry.addViewController("/person_list").setViewName("person_list");
        registry.addViewController("/person_edit").setViewName("person_edit");        
        registry.addViewController("/address_list").setViewName("address_list");
        registry.addViewController("/address_edit").setViewName("address_edit");
        registry.addViewController("/tenant_list").setViewName("tenant_list");
        registry.addViewController("/tenant_edit").setViewName("tenant_edit");
        registry.addViewController("/admin_list").setViewName("admin_list");
        registry.addViewController("/admin_edit").setViewName("admin_edit");
        registry.addViewController("/landlord_list").setViewName("landlord_list");
        registry.addViewController("/landlord_edit").setViewName("landlord_edit");
        registry.addViewController("/inquiry_list").setViewName("landlord_list");
        registry.addViewController("/inquiry_edit").setViewName("landlord_edit");
        registry.addViewController("/rentalagreement_list").setViewName("landlord_list");
        registry.addViewController("/rentalagreement_edit").setViewName("landlord_edit");
        registry.addViewController("/rentapplication_list").setViewName("landlord_list");
        registry.addViewController("/rentapplication_edit").setViewName("landlord_edit");
    }
}
