package ru.vsu.cs.smagina.Product.meet;

import ru.vsu.cs.smagina.KindOfProduct;
import ru.vsu.cs.smagina.Product.WeightProduct;
import ru.vsu.cs.smagina.Product.WithExpirationDate;

public class Beef extends WeightProduct implements WithExpirationDate {
    //Данный класс не реализован, потому что имеет такую же реализацию, что и другое мясо.
    //Реализован класс Chicken. Класс Beef лишь для понимания структуры проекта.

    private int expirationDate;
    public Beef() {
        super(KindOfProduct.Chicken);//В реалии в KindOFProduct добавится Beef аналог Chicken
    }

    @Override
    public String getUnitOfProduct() {
        return "";
    }

    @Override
    public int getExpirationDate() {
        return 0;
    }

    @Override
    public void setExpirationDate(int date) {

    }
}
