package com.dashyl.entity;

import com.dashyl.DAO.ClientDAO;
import config.DBUnitConfig;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.*;

import javax.persistence.*;
import java.util.List;

public class ClientTest extends DBUnitConfig {

    private ClientDAO dao = new ClientDAO();
    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/client/client-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public ClientTest(String name) {
        super(name);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Client> products = dao.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build( Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("com/dashyl/entity/client/client-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("client").getRowCount(), products.size());
    }

    @Test
    public void testSave() throws Exception {
        Client client = new Client();
        client.setName("Vasya");
        client.setEmail("vasya@mail.ru");
        client.setPhoneNumber("459812");

        dao.save(client);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("com/dashyl/entity/client/client-data-save.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "client", ignore);
    }

    //others tests

}