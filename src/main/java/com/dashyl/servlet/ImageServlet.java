package com.dashyl.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Darya on 20.04.2015.
 */
@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");

        String contextPath = getServletConfig().getServletContext().getContext("").getRealPath("\\");

        String path = req.getRequestURI().substring(req.getContextPath().length());
        File f = new File("static/" + path);
        BufferedImage bi = ImageIO.read(f);
        OutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
