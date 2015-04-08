package com.dashyl.entity;

        import com.dashyl.DAO.AvailableProductDAO;
        import config.DBUnitConfig;
        import org.dbunit.Assertion;
        import org.dbunit.dataset.IDataSet;
        import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
        import org.junit.*;

        import javax.persistence.*;
        import java.util.List;

public class AvailableProductTest extends DBUnitConfig {

    private AvailableProductDAO dao = new AvailableProductDAO();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/availableproduct/availableproduct-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public AvailableProductTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        List<AvailableProduct> products = dao.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/availableproduct/availableproduct-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("availableproduct").getRowCount(), products.size());
    }

    @Test
    public void testSave() throws Exception {
        AvailableProduct product = new AvailableProduct();
        product.setProduct(new Product("������������� ����", new Category("��������")));
        product.setAmount(15);
        product.setPrice(28.5);

        dao.save(product);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/dashyl/entity/availableproduct/availableproduct-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "availableproduct", ignore);
    }

    //others tests

}