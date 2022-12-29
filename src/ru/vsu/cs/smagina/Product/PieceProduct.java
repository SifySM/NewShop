package ru.vsu.cs.smagina.Product;

import ru.vsu.cs.smagina.KindOfProduct;

public class PieceProduct extends Product {
    private final String UnitOfMeasurement = "шт";

    public PieceProduct(KindOfProduct kindOfProduct) {
        super(kindOfProduct);
    }

    @Override
    public String getUnitOfProduct() {
        return UnitOfMeasurement;
    }
}
