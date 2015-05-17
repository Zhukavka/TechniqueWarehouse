package com.dashyl.util;

import com.dashyl.DAO.*;
import com.dashyl.entity.OrderedProduct;
import com.dashyl.entity.User;

/**
 * Created by Darya on 03.05.2015.
 */
public class DAOFactory {
    private static AvailableProductDAO availableProductDAO = null;
    private static ProductDAO productDAO = null;
    private static ClientDAO clientDAO = null;
    private static OrderDAO orderDAO = null;
    private static OrderedProductDAO orderedProductDAO = null;
    private static UserDAO userDAO = null;

    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance() {
        return instance == null ? new DAOFactory() : instance;
    }

    public AvailableProductDAO getAvailableProductDAO() {
        return availableProductDAO == null ? new AvailableProductDAO() : availableProductDAO;
    }

    public ProductDAO getProductDAO() {
        return productDAO == null ? new ProductDAO() : productDAO;
    }

    public ClientDAO getClientDAO() {
        return clientDAO == null ? new ClientDAO() : clientDAO;
    }

    public OrderDAO getOrderDAO() {return orderDAO == null ? new OrderDAO() : orderDAO;}

    public OrderedProductDAO getOrderedProductDAO() { return orderedProductDAO == null ? new OrderedProductDAO() : orderedProductDAO; }

    public UserDAO getUserDAO() { return userDAO == null ? new UserDAO() : userDAO; }
}
