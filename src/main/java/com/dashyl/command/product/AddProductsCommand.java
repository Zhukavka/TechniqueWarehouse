package com.dashyl.command.product;

import com.dashyl.command.ServletCommand;
import com.dashyl.entity.AvailableProduct;
import com.dashyl.entity.Product;
import com.dashyl.servlet.manager.Page;
import com.dashyl.util.DAOFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

/**
 * Created by Darya on 03.05.2015.
 */
public class AddProductsCommand implements ServletCommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final Part filePart = request.getPart("file");

        final String fileName = getFileName(filePart);
        if(fileName != null && !fileName.isEmpty()) {
            InputStream fileContent = null;
            JSONObject json = null;
            try {
                fileContent = filePart.getInputStream();
                filePart.write("C:" + File.separator + "warehouse" + File.separator + fileName);
                FileReader reader = new FileReader("C:" + File.separator + "warehouse" + File.separator + fileName);
                JSONParser jsonParser = new JSONParser();
                json = (JSONObject) jsonParser.parse(reader);
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("messageType", "error");
                request.setAttribute("message", "Ошибка добавления товаров: " + e.getMessage());
            } finally {
                if (fileContent != null) {
                    fileContent.close();
                }
            }

            if (json != null && !json.isEmpty()) {
                JSONArray products = (JSONArray) json.get("products");
                for (Object aJson : products) {
                    JSONObject arrayObject = (JSONObject) aJson;
                    String barcode = (String) arrayObject.get("barcode");
                    double price = ((Number) arrayObject.get("price")).doubleValue();
                    int amount = ((Number) arrayObject.get("amount")).intValue();

                    Product product = DAOFactory.getInstance().getProductDAO().getByBarcode(barcode);
                    if (product == null) continue;

                    DAOFactory.getInstance().getAvailableProductDAO().update(new AvailableProduct(product, amount, price),
                                                                            true);
                    request.setAttribute("messageType", "success");
                    request.setAttribute("message", "Товары добавлены");
                }
            }
        }
        List<AvailableProduct> products = DAOFactory.getInstance().getAvailableProductDAO().getAll();
        request.setAttribute("products", products);
        return Page.HOME;
    }

    protected String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for(String content: part.getHeader("content-disposition").split(";")) {
            if(content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
