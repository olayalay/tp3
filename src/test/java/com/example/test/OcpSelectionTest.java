package com.example.test;

import com.example.metier.IMetier;
import com.example.presentation.Presentation2;
import com.example.presentation.PresentationProperty;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class OcpSelectionTest {

    @Test
    public void devProfile_choisitDao2_300() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");     // DaoImpl2 (150)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(300.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void prodProfile_choisitDao_200() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");    // DaoImpl (100)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(200.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void fileProfile_choisitDaoFile_360() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("file");    // DaoFile (180)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(360.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void apiProfile_choisitDaoApi_440() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("api");     // DaoApi (220)
        ctx.register(Presentation2.class);
        ctx.refresh();
        IMetier m = ctx.getBean(IMetier.class);
        assertEquals(440.0, m.calcul(), 1e-6);
        ctx.close();
    }

    @Test
    public void propertyDaoApi_choisitDaoApi_440() {
        // Sauvegarde de la propriété système existante
        String originalValue = System.getProperty("dao.target");
        
        try {
            // Configuration de la propriété système pour le test
            System.setProperty("dao.target", "daoApi");
            
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            // Pas de profil actif - utilise la propriété système via PropertyDrivenConfig
            ctx.register(PresentationProperty.class);
            ctx.refresh();
            IMetier m = ctx.getBean(IMetier.class);
            assertEquals(440.0, m.calcul(), 1e-6);
            ctx.close();
        } finally {
            // Restauration de la propriété système originale
            if (originalValue != null) {
                System.setProperty("dao.target", originalValue);
            } else {
                System.clearProperty("dao.target");
            }
        }
    }

    @Test
    public void propertyDaoFile_choisitDaoFile_360() {
        String originalValue = System.getProperty("dao.target");
        
        try {
            System.setProperty("dao.target", "daoFile");
            
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(PresentationProperty.class);
            ctx.refresh();
            IMetier m = ctx.getBean(IMetier.class);
            assertEquals(360.0, m.calcul(), 1e-6);
            ctx.close();
        } finally {
            if (originalValue != null) {
                System.setProperty("dao.target", originalValue);
            } else {
                System.clearProperty("dao.target");
            }
        }
    }

    @Test
    public void propertyDao2_choisitDao2_300() {
        String originalValue = System.getProperty("dao.target");
        
        try {
            System.setProperty("dao.target", "dao2");
            
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(PresentationProperty.class);
            ctx.refresh();
            IMetier m = ctx.getBean(IMetier.class);
            assertEquals(300.0, m.calcul(), 1e-6);
            ctx.close();
        } finally {
            if (originalValue != null) {
                System.setProperty("dao.target", originalValue);
            } else {
                System.clearProperty("dao.target");
            }
        }
    }

    @Test
    public void propertyDao_choisitDao_200() {
        String originalValue = System.getProperty("dao.target");
        
        try {
            System.setProperty("dao.target", "dao");
            
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(PresentationProperty.class);
            ctx.refresh();
            IMetier m = ctx.getBean(IMetier.class);
            assertEquals(200.0, m.calcul(), 1e-6);
            ctx.close();
        } finally {
            if (originalValue != null) {
                System.setProperty("dao.target", originalValue);
            } else {
                System.clearProperty("dao.target");
            }
        }
    }
}
