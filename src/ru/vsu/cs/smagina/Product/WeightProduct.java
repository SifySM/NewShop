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

   /* public double getOnStock() {
        return massOnStock;
    }

    public double getAtShop() {
        return massAtShop;
    }

    public void moveToShop(double mass) {
        if (mass > massOnStock) {
            mass = massOnStock;
        }
        massAtShop += mass;
        massOnStock -= mass;
    }

    public void buy(double mass) {
        if (mass > massAtShop) {
            mass = massAtShop;
        }
        massAtShop -= mass;
    }*/
}
