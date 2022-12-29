package ru.vsu.cs.smagina;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.vsu.cs.smagina.Actions.*;

public class ProductEventManager {
    private static final Map<Actions, Double> probabilityOfActions = Map.of(
            buyProduct, 0.8 / 60, // 0,013
            addProductOnStorage, 0.1 / 60, // 0,0016
            addProductToMarket, 0.3 / 60 // 0,005
    );
    private static final Map<KindOfProduct, Double> maxBuyingCount = Map.of(
            KindOfProduct.Chicken, 10.0,
            KindOfProduct.Chocolates, 5.0,
            KindOfProduct.Bag, 2.0
    );
    static Supermarket supermarket = new Supermarket();
    static Storage storage = new Storage();
    private static List<ProductEvent> listOfEvents = new ArrayList<>();

    public static void generateEvents(int sec) {
        double random = Math.random();

        for (Actions action : Actions.values()) {
            if (random <= probabilityOfActions.get(action)) {
                //сгенерировать действие (покупка, добавление на склад, перенос со склада в магазина)
                KindOfProduct[] kindOfProducts = KindOfProduct.values();
                KindOfProduct product = kindOfProducts[(int) (kindOfProducts.length * Math.random())];
                addEvent(new ProductEvent(sec, action, product));
            }
        }
    }

    public static void printsEvents() {
        for (int i = 0; i < listOfEvents.size(); i++) {
            listOfEvents.get(i).toString();
        }
    }

    public static void addEvent(ProductEvent productEvent) {
        listOfEvents.add(productEvent);

        switch (productEvent.action) {
            case addProductOnStorage: {
                addProductOnStorage.execute(productEvent, storage, supermarket);
                break;
            }
            case buyProduct: {
                buyProduct.execute(productEvent, storage, supermarket);
                break;
            }
            case addProductToMarket: {
                addProductToMarket.execute(productEvent, storage, supermarket);
            }
        }
    }

    public static double maxBuyingCount(ProductEvent productEvent) {
        return maxBuyingCount.get(productEvent.product);
    }
}
