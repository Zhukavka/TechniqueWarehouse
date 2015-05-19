package com.dashyl.service;

import com.dashyl.entity.Order;
import com.dashyl.entity.OrderedProduct;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

public class CreateReport {

    private static ObjectFactory factory = null;
    private static WordprocessingMLPackage wordMLPackage = null;

    public static void generateReport(Order order) throws InvalidFormatException{
        try {
            wordMLPackage = WordprocessingMLPackage.createPackage();
            factory = Context.getWmlObjectFactory();

            Tbl table = createTable(order);
            addBorders(table);
            wordMLPackage.getMainDocumentPart()
                    .addStyledParagraphOfText("Title", "Отчёт по клиенту " + order.getClient().getName());
            wordMLPackage.getMainDocumentPart().addObject(table);
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                wordMLPackage.save(new java.io.File("c:\\warehouse\\reports" + "\\"
                        + order.getClient().getName() + df.format(order.getDate())  + ".docx"));
            } catch (Docx4JException e) {
                e.printStackTrace();
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);

        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }

    private static Tbl createTable(Order order) {
        Tbl table = factory.createTbl();
        Tr tableRow = factory.createTr();
        addTableCell(tableRow, "Название продукта");
        addTableCell(tableRow, "Категория продукта");
        addTableCell(tableRow, "Заказанное количество");
        addTableCell(tableRow, "Цена");
        table.getContent().add(tableRow);

        List<OrderedProduct> products = order.getProducts();
        for(OrderedProduct ordProd: products) {
            tableRow = factory.createTr();
            addTableCell(tableRow, ordProd.getProduct().getName());
            addTableCell(tableRow, ordProd.getProduct().getCategory().getName());
            addTableCell(tableRow, String.valueOf( ordProd.getAmount() ));
            addTableCell(tableRow, String.valueOf( ordProd.getPrice() ));
            table.getContent().add(tableRow);
        }
        return table;
    }

    private static void addTableCell(Tr tableRow, String content) {
        Tc tableCell = factory.createTc();
        tableCell.getContent().add(wordMLPackage.getMainDocumentPart().createParagraphOfText(content));
        tableRow.getContent().add(tableCell);
    }
}
