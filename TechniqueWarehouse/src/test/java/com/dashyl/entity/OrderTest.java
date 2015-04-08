package com.dashyl.entity;

import com.dashyl.DAO.OrderDAO;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTest extends DBUnitConfig {

    private OrderDAO dao = new OrderDAO();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/order/order-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public OrderTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Order> orders = dao.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/order/order-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("order").getRowCount(), orders.size());
    }

    @Test
    public void testSave() throws Exception {
        Order order = new Order();
        order.setClient(new Client("Вася", "vasy@mail.ru", "547824"));
        order.setCost(2147.67);
        order.setDate(new Date());
        List<OrderedProduct> products = new ArrayList<>();
        products.add(new OrderedProduct(new Product("Микскер", new Category("Кухонная")), 15));
        order.setProducts(products);

        dao.save(order);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/dashyl/entity/order/order-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "order", ignore);
    }

    //others tests

}