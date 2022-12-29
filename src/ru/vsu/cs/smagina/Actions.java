package ru.vsu.cs.smagina;

import ru.vsu.cs.smagina.Product.PartyOfProduct;
import ru.vsu.cs.smagina.Product.Product;
import ru.vsu.cs.smagina.Product.WithExpirationDate;

interface Strategy {
    void execute(ProductEvent productEvent, Storage storage, Supermarket supermarket);
}

public enum Actions implements Strategy {
    addProductOnStorage {
        double random = Math.random();

        @Override
        public void execute(ProductEvent productEvent, Storage storage, Supermarket supermarket) {
            Product curProduct = productEvent.product.product();
            if (WithExpirationDate.class.isAssignableFrom(curProduct.getClass())) {
                ((WithExpirationDate) curProduct).setExpirationDate(productEvent.sec);
            }
            storage.addPartyOfProduct(new PartyOfProduct(curProduct,
                    random * storage.getMaxParty(productEvent.product)));
        }
    },
    addProductToMarket {
        @Override
        public void execute(ProductEvent productEvent, Storage storage, Supermarket supermarket) {
            //число пустых мест под товар в магазине
            if (storage != null) {
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
    },
    buyProduct {
        double random = Math.random();

        @Override
        public void execute(ProductEvent productEvent, Storage storage, Supermarket supermarket) {
            double countOfBuying = 1.0;
            if (ProductEventManager.maxBuyingCount(productEvent) != 0)
                countOfBuying = ProductEventManager.maxBuyingCount(productEvent);
            countOfBuying *= random;
            while (countOfBuying > 0) {
                PartyOfProduct currPartyOfProduct = supermarket.buyProduct(productEvent.product, countOfBuying);

                if (currPartyOfProduct == null || currPartyOfProduct.getCount() == 0) break;
                countOfBuying -= currPartyOfProduct.getCount();
            }
        }
    }
}