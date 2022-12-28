package ru.vsu.cs.smagina.Product;

import ru.vsu.cs.smagina.KindOfProduct;

public abstract class Product {
    public final KindOfProduct kindOfProduct;

    protected Product(KindOfProduct kindOfProduct) {
        this.kindOfProduct = kindOfProduct;
    }

    public abstract String getUnitOfProduct();
}
