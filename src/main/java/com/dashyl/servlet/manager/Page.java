package com.dashyl.servlet.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Darya on 12.05.2015.
 */
public class Page {
    public static final String HOME = "static/jsp/mainpage.jsp";
    public static final String AUTH = "static/jsp/auth.jsp";
    public static final String ORDERS = "static/jsp/all_orders.jsp";
    public static final String ORDER = "static/jsp/order.jsp";
    public static final String CLIENTS = "static/jsp/clients.jsp";

    protected static final Map<String, String> pages;

    static {
        pages = new HashMap<String, String>();
        //pages.put();
    }

    public static String getPageByEvent(String event) {
        return pages.getOrDefault(event, Page.HOME);
    }
}
