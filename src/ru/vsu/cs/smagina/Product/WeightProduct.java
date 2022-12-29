package ru.vsu.cs.smagina.Product;

import ru.vsu.cs.smagina.KindOfProduct;

public class WeightProduct extends Product {
    private final String UnitOfMeasurement = "кг";

    public WeightProduct(KindOfProduct kindOfProduct) {
        super(kindOfProduct);
    }

    @Override
    public String getUnitOfProduct() {
        return UnitOfMeasurement;
    }
}
