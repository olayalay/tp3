package com.example.test;

import com.example.metier.IMetier;
import com.example.presentation.Presentation2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class SimpleProfileTest {

    @Test
    public void testProdProfile() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");
        ctx.register(Presentation2.class);
        ctx.refresh();
        
        IMetier m = ctx.getBean(IMetier.class);
        double result = m.calcul();
        System.out.println("Prod profile result: " + result);
        assertEquals(200.0, result, 1e-6);
        
        ctx.close();
    }

    @Test
    public void testDevProfile() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(Presentation2.class);
        ctx.refresh();
        
        IMetier m = ctx.getBean(IMetier.class);
        double result = m.calcul();
        System.out.println("Dev profile result: " + result);
        assertEquals(300.0, result, 1e-6);
        
        ctx.close();
    }

    @Test
    public void testFileProfile() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("file");
        ctx.register(Presentation2.class);
        ctx.refresh();
        
        IMetier m = ctx.getBean(IMetier.class);
        double result = m.calcul();
        System.out.println("File profile result: " + result);
        assertEquals(360.0, result, 1e-6);
        
        ctx.close();
    }

    @Test
    public void testApiProfile() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("api");
        ctx.register(Presentation2.class);
        ctx.refresh();
        
        IMetier m = ctx.getBean(IMetier.class);
        double result = m.calcul();
        System.out.println("Api profile result: " + result);
        assertEquals(440.0, result, 1e-6);
        
        ctx.close();
    }
}
