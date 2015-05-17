package com.dashyl.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Darya on 21.04.2015.
 */
public interface ServletCommand {
    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
    //void undo();
    @Override
    String toString();
}
