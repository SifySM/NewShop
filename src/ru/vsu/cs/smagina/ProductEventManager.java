package ru.vsu.cs.smagina;

import ru.vsu.cs.smagina.Product.PartyOfProduct;
import ru.vsu.cs.smagina.Product.Product;
import ru.vsu.cs.smagina.Product.WithExpirationDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.vsu.cs.smagina.Actions.*;

public class ProductEventManager {
    private static final double INTERVAL = 3600;
    private static final Map<Actions, Double> probabilityOfActions = Map.of(
            buyProduct, 0.8 / 60, // 0,013
            addProductOnStorage, 0.1 / 60, // 0,0016
            addProductToMarket, 0.3 / 60 // 0,005
    );
    private static final Map<KindOfProduct, Double> maxBuyingCount = Map.of(
            KindOfProduct.Chicken, 10.0, // 0,013
            KindOfProduct.Chocolates, 5.0, // 0,0016
            KindOfProduct.Bag, 2.0 // 0,005
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
        double random = Math.random();
        listOfEvents.add(productEvent);
        switch (productEvent.action) {
            case addProductOnStorage: {
                Product curProduct = productEvent.product.product();
                if (WithExpirationDate.class.isAssignableFrom(curProduct.getClass())) {
                    ((WithExpirationDate) curProduct).setExpirationDate(productEvent.sec);
                }
                storage.addPartyOfProduct(new PartyOfProduct(curProduct,
                        random * storage.getMaxParty(productEvent.product)));
                break;
            }
            case buyProduct: {
                double countOfBuying = 1.0;
                if (maxBuyingCount.get(productEvent.product) != null) countOfBuying = maxBuyingCount.get(productEvent.product);
                countOfBuying *= random;
                while (countOfBuying > 0) {
                    PartyOfProduct currPartyOfProduct = supermarket.buyProduct(productEvent.product, countOfBuying);

                    if (currPartyOfProduct == null || currPartyOfProduct.getCount() == 0) break;
                    countOfBuying -= currPartyOfProduct.getCount();
                }
            }
            case addProductToMarket: {
                //число пустых мест под товар в магазине
                if (storage == null) break;
                double countEmptyPlaces = supermarket.getMaxParty(productEvent.product) -
                        supermarket.countOfProduct(productEvent.product);

                while (countEmptyPlaces > 0) {
                    //забираем со склада
                    PartyOfProduct currPartyOfProduct = storage.takeProduct(productEvent.product, countEmptyPlaces);
                    if (currPartyOfProduct == null || currPartyOfProduct.getCount() == 0) break;
                    countEmptyPlaces -= currPartyOfProduct.getCount();

                    //добавляем в магазин (партиями)
                    supermarket.addPartyOfProduct(currPartyOfProduct);
                }
            }
        }
    }
}
