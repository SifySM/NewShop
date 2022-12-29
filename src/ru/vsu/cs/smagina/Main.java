package ru.vsu.cs.smagina;

public class Main {
    private static final double INTERVAL = 3600;
    private static int time = 0;

    public static void main(String[] args) {
        for (time = 0; time <= INTERVAL; time++) {
            ProductEventManager.generateEvents(time);
        }
        ProductEventManager.printsEvents();
    }
}
