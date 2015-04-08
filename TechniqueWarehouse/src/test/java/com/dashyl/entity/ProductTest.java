package com.dashyl.entity;

import com.dashyl.DAO.ProductDAO;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.*;

import javax.persistence.*;
import java.util.List;

public class ProductTest extends DBUnitConfig {

    private ProductDAO dao = new ProductDAO();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/product/product-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public ProductTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Product> products = dao.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/product/product-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("product").getRowCount(), products.size());
    }

    @Test
    public void testSave() throws Exception {
        Product product = new Product();
        product.setName("Микроволновая печь");
        product.setCategory(new Category("Кухонная"));

        dao.save(product);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/dashyl/entity/product/product-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "product", ignore);
    }

    //others tests

}