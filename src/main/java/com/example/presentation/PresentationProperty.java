package com.example.presentation;

import com.example.metier.IMetier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier", "com.example.config"})
public class PresentationProperty {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // Mode propriété : utilise PropertyDrivenConfig selon app.properties
        // Pas de profils actifs - laisse PropertyDrivenConfig choisir

        ctx.register(PresentationProperty.class);
        ctx.refresh();

        IMetier metier = ctx.getBean(IMetier.class);
        System.out.println("Résultat (propriété) = " + metier.calcul());
        ctx.close();
    }
}
