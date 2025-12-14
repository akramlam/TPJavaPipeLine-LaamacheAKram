package com.laamache.akram;

/**
 * Application principale - TP Java Pipeline
 * @author Laamache Akram
 */
public class App {

    public static void main(String[] args) {
        App app = new App();
        System.out.println("=================================");
        System.out.println("  TP Java Pipeline - Jenkins CI  ");
        System.out.println("=================================");
        System.out.println(app.getMessage());
        System.out.println("Addition: 5 + 3 = " + app.additionner(5, 3));
        System.out.println("Multiplication: 4 x 7 = " + app.multiplier(4, 7));
        System.out.println("=================================");
    }

    /**
     * Retourne un message de bienvenue
     */
    public String getMessage() {
        return "Hello Jenkins Pipeline!";
    }

    /**
     * Additionne deux nombres
     */
    public int additionner(int a, int b) {
        return a + b;
    }

    /**
     * Multiplie deux nombres
     */
    public int multiplier(int a, int b) {
        return a * b;
    }

    /**
     * Vérifie si un nombre est pair
     */
    public boolean estPair(int nombre) {
        return nombre % 2 == 0;
    }

    /**
     * Retourne le maximum de deux nombres
     */
    public int maximum(int a, int b) {
        return Math.max(a, b);
    }
}
