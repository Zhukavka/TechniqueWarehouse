package com.dashyl.util;

import com.dashyl.DAO.AvailableProductDAO;
import com.dashyl.DAO.ProductDAO;

/**
 * Created by Darya on 03.05.2015.
 */
public class DAOUtil {
    private static AvailableProductDAO availableProductDAO = null;
    private static ProductDAO productDAO = null;

    private static DAOUtil instance = null;

    public static synchronized DAOUtil getInstance() {
        return instance == null ? new DAOUtil() : instance;
    }

    public static AvailableProductDAO getAvailableProductDAO() {
        return availableProductDAO == null ? new AvailableProductDAO() : availableProductDAO;
    }

    public static ProductDAO getProductDAO() {
        return productDAO == null ? new ProductDAO() : productDAO;
    }
}
