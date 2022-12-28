package ru.vsu.cs.smagina.Product.meet;

import ru.vsu.cs.smagina.KindOfProduct;
import ru.vsu.cs.smagina.Product.WeightProduct;
import ru.vsu.cs.smagina.Product.WithExpirationDate;

public class Chicken extends WeightProduct implements WithExpirationDate {
    private int expirationDate;
    public Chicken() {
        super(KindOfProduct.Chicken);
    }

    @Override
    public int getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(int date) {
        expirationDate = date;
    }
}
