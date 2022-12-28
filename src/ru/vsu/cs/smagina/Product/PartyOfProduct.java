package ru.vsu.cs.smagina.Product;

public class PartyOfProduct {
    private final Product product;
    private double count;

    public PartyOfProduct(Product product, double count) {
        this.product = product;
        this.count = count;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }
}
