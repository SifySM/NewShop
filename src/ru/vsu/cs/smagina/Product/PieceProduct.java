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

    /*
    public int getOnStock() {
        return itemsOnStock;
    }

    public int getAtShop() {
        return itemsAtShop;
    }

    public void moveToShop(int items) {
        if (items > itemsOnStock) {
            items = itemsOnStock;
        }
        itemsAtShop += items;
        itemsOnStock -= items;
    }

    public void buy(int items) {
        if (items > itemsAtShop) {
            items = itemsAtShop;
        }
        itemsAtShop -= items;
    }*/
}
