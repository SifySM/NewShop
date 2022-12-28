package ru.vsu.cs.smagina;

import ru.vsu.cs.smagina.Product.PartyOfProduct;

import java.util.ArrayList;
import java.util.Map;

public class Storage {
    private static final Map<KindOfProduct, Double> MAX_PARTY_COUNT = Map.of(
            KindOfProduct.Chicken, 500.0,
            KindOfProduct.Chocolates, 100.0,
            KindOfProduct.Bag, 10.0
    );
    private ArrayList<PartyOfProduct> productList = new ArrayList<>();

    public double getMaxParty(KindOfProduct kindOfProduct) {
        return MAX_PARTY_COUNT.get(kindOfProduct);
    }

    public PartyOfProduct takeProduct(KindOfProduct kindOfProduct, double count) {
        int i = 0;
        while (productList.size() != 0) {
            if (productList.get(i).getProduct() == kindOfProduct.product()) {
                if (count >= productList.get(i).getCount()) {
                    productList.remove(i);
                    return new PartyOfProduct(productList.get(i).getProduct(), productList.get(i).getCount());
                } else {
                    productList.get(i).setCount(productList.get(i).getCount() - count);
                    return new PartyOfProduct(productList.get(i).getProduct(), count);
                }
            }
            i++;
        }
        return null;
    }


    public void addPartyOfProduct(PartyOfProduct partyOfProduct) {
        productList.add(partyOfProduct);
    }
}
