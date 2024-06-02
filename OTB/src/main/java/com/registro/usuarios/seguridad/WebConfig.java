package com.registro.usuarios.seguridad;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*
     * 
     *   @Autowired
     private SessionInterceptor sessionInterceptor;

    @SuppressWarnings("null")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor);

    }
     */
  

    
    @SuppressWarnings("null")
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new org.springframework.format.Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, java.util.Locale locale) {
                return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

            @Override
            public String print(LocalDate object, java.util.Locale locale) {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
            }
        });
    }
}