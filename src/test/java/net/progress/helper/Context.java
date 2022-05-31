package net.progress.helper;

import net.progress.POMs.ShopPOM;

import java.util.Map;

public class Context {
    public String user;
    public ShopPOM shopPOM;
    public Map<String, String> items;

    public int quantityAddedProducts;
    public double totalPriceAddedProducts;
}
