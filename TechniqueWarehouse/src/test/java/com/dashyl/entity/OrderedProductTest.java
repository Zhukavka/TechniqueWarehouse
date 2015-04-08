package com.dashyl.entity;

import com.dashyl.DAO.OrderedProductDAO;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.*;

import javax.persistence.*;
import java.util.List;

public class OrderedProductTest extends DBUnitConfig {

    private OrderedProductDAO dao = new OrderedProductDAO();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/orderedproduct/orderedproduct-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public OrderedProductTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        List<OrderedProduct> products = dao.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/orderedproduct/orderedproduct-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("orderedproduct").getRowCount(), products.size());
    }

    @Test
    public void testSave() throws Exception {
        OrderedProduct product = new OrderedProduct();
        product.setProduct(new Product("Микроволновая печь", new Category("Кухонная")));
        product.setAmount(15);

        dao.save(product);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/dashyl/entity/orderedproduct/orderedproduct-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "orderedproduct", ignore);
    }

    //others tests

}