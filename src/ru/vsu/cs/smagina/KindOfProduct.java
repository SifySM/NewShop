package ru.vsu.cs.smagina;

import ru.vsu.cs.smagina.Product.Things.Bag;
import ru.vsu.cs.smagina.Product.Sweet.Chocolates;
import ru.vsu.cs.smagina.Product.Product;
import ru.vsu.cs.smagina.Product.meet.Chicken;

public enum KindOfProduct {
    Chicken {
        @Override
        public Product product() {
            return new Chicken();
        }
    },
    Chocolates {
        @Override
        public Product product() {
            return new Chocolates();
        }
    },
    Bag {
        @Override
        public Product product() {
            return new Bag();
        }
    },
    Juice {
        @Override
        public Product product() {
            return null;
        }
    };


    public abstract Product product();
}
