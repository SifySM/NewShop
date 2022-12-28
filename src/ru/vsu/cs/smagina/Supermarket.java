package ru.vsu.cs.smagina;

import ru.vsu.cs.smagina.Product.PartyOfProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Supermarket {
    private static final Map<KindOfProduct, Double> MAX_PARTY_COUNT = Map.of(
            KindOfProduct.Chicken, 50.0,
            KindOfProduct.Chocolates, 20.0,
            KindOfProduct.Bag, 2.0
    );
    private ArrayList<PartyOfProduct> productList = new ArrayList();
    private HashMap<KindOfProduct, Double> priceList = new HashMap<>();

    public Double getMaxParty(KindOfProduct kindOfProduct) {
        return MAX_PARTY_COUNT.get(kindOfProduct);
    }

    public void addPartyOfProduct(PartyOfProduct partyOfProduct) {
        productList.add(partyOfProduct);
    }

    public double countOfProduct(KindOfProduct kindOfProduct) {
        double count = 0;
        for (PartyOfProduct currPartyOfProduct : productList) {
            if (currPartyOfProduct.getProduct().kindOfProduct == kindOfProduct) {
                count += currPartyOfProduct.getCount();
            }
        }

        return count;
    }

    public PartyOfProduct buyProduct(KindOfProduct kindOfProduct, double count) {
        int i = 0;
        while (true) {
            if (productList.size() == 0) return null;
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
    }
}
