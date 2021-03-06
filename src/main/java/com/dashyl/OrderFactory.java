package com.dashyl;

import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Order;
import com.dashyl.entity.OrderedProduct;
import com.dashyl.entity.User;
import com.dashyl.util.DAOFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Darya on 05.05.2015.
 */
public class OrderFactory {
    private static ConcurrentHashMap<String, Order> currentOrders;
    private static OrderFactory instance = null;

    static {
        currentOrders = new ConcurrentHashMap<String, Order>();
    }

    public static synchronized OrderFactory getInstance() {
        return instance == null ? new OrderFactory() : instance;
    }

    public void addProductToOrder(String username, AvailableProduct product, int amount) {
        if( currentOrders.containsKey(username) ) {
            Order order = currentOrders.get(username);
            for(OrderedProduct productInOrder: order.getProducts()) {
                if(productInOrder.getPrice() == product.getPrice()) {
                    productInOrder.setAmount(productInOrder.getAmount() + amount);
                    return;
                }
            }
            List<OrderedProduct> products = order.getProducts();
            int id;
            if(products.size() > 0) {
                id = products.get(products.size() - 1).getId() + 1;
            } else {
                List<OrderedProduct> productsInDB = DAOFactory.getInstance().getOrderedProductDAO().getAll();
                if(productsInDB.size() > 0) {
                    id = productsInDB.get(productsInDB.size() - 1).getId() + 1;
                } else
                    id = 0;
            }
            OrderedProduct productToOrder = new OrderedProduct(product, amount);
            productToOrder.setId(id);
            order.addProductToOrder(productToOrder);
        } else {
            Order order = new Order();
            order.setUser( new User( username ) );
            order.addProductToOrder(new OrderedProduct(product, amount) );
            currentOrders.put(username, order);
        }
    }

    public Order getOrder(String username) {
        return currentOrders.containsKey(username) ? currentOrders.get(username) : null;
    }

    public void deleteProduct(String username, OrderedProduct product) {
        if(currentOrders.containsKey(username)) {
            currentOrders.get(username).deleteProduct(product);
            if( currentOrders.get(username).getProducts().isEmpty() )
                currentOrders.remove(username);
        }

    }

    public void deleteAlProducts(String username) {
        if(currentOrders.containsKey(username)) {
            currentOrders.remove(username);
        }
    }
}
