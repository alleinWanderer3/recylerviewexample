package com.hack.fragmentsinteractiondemo;

class Pair_crypto {
    public String title;
    public double price;
    public double volume;
    public String currency;

    public Pair_crypto(String title, double price, double volume) {
        this.title = title;//currency
        this.price = price;//cost
        this.volume = volume;//vsego torguetsya - obem
    }

    public Pair_crypto(String currency) {
        this.title = currency;
    }


}
