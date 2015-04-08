package com.dashyl.entity;

import com.dashyl.DAO.CategoryDAO;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.*;

import javax.persistence.*;
import java.util.List;

public class CategoryTest extends DBUnitConfig {

    private CategoryDAO dao = new CategoryDAO();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/category/category-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public CategoryTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Category> categories = dao.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/category/category-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("category").getRowCount(), categories.size());
    }

    @Test
    public void testSave() throws Exception {
        Category category = new Category();
        category.setName("Кухонная");

        dao.save(category);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/dashyl/entity/category/category-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "category", ignore);
    }

    //others tests

}