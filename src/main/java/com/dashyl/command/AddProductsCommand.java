package com.dashyl.command;

import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Product;
import com.dashyl.util.DAOUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

/**
 * Created by Darya on 03.05.2015.
 */
public class AddProductsCommand implements Command {
    private JSONArray json;

    public AddProductsCommand(JSONArray json) {
        this.json = json;
    }

    public void execute() {
        if(json.isEmpty())
            return;

        for (Object aJson : json) {
            JSONObject arrayObject = (JSONObject) aJson;
            String barcode = (String) arrayObject.get("barcode");
            double price = ((Number) arrayObject.get("price")).doubleValue();
            int amount = ((Number) arrayObject.get("amount")).intValue();

            Product product = DAOUtil.getInstance().getProductDAO().getByBarcode(barcode);
            if (product == null) continue;

            DAOUtil.getInstance().getAvailableProductDAO().save(new AvailableProduct(product, amount, price));
        }
    }
}
