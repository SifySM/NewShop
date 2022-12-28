package ru.vsu.cs.smagina;

public class ProductEvent {
    public final int sec;
    public final KindOfProduct product;
    public final Actions action;

    public ProductEvent(int sec, Actions actions, KindOfProduct product) {
        this.sec = sec;
        this.product = product;
        this.action = actions;
    }
}
