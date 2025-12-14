package com.laamache.akram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe App
 * @author Laamache Akram
 */
class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App();
    }

    @Test
    @DisplayName("Test du message de bienvenue")
    void testGetMessage() {
        String message = app.getMessage();
        assertNotNull(message);
        assertEquals("Hello Jenkins Pipeline!", message);
    }

    @Test
    @DisplayName("Test addition de deux nombres positifs")
    void testAdditionner() {
        assertEquals(8, app.additionner(5, 3));
        assertEquals(0, app.additionner(0, 0));
        assertEquals(100, app.additionner(50, 50));
    }

    @Test
    @DisplayName("Test addition avec nombres négatifs")
    void testAdditionnerNegatifs() {
        assertEquals(-2, app.additionner(-5, 3));
        assertEquals(-8, app.additionner(-5, -3));
    }

    @Test
    @DisplayName("Test multiplication")
    void testMultiplier() {
        assertEquals(28, app.multiplier(4, 7));
        assertEquals(0, app.multiplier(0, 100));
        assertEquals(25, app.multiplier(5, 5));
    }

    @Test
    @DisplayName("Test multiplication avec négatifs")
    void testMultiplierNegatifs() {
        assertEquals(-20, app.multiplier(-4, 5));
        assertEquals(20, app.multiplier(-4, -5));
    }

    @Test
    @DisplayName("Test nombre pair")
    void testEstPair() {
        assertTrue(app.estPair(2));
        assertTrue(app.estPair(0));
        assertTrue(app.estPair(100));
        assertFalse(app.estPair(1));
        assertFalse(app.estPair(99));
    }

    @Test
    @DisplayName("Test maximum de deux nombres")
    void testMaximum() {
        assertEquals(10, app.maximum(5, 10));
        assertEquals(10, app.maximum(10, 5));
        assertEquals(5, app.maximum(5, 5));
        assertEquals(0, app.maximum(-5, 0));
    }
}
