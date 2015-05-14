package com.dashyl.command.manager;

import com.dashyl.command.ServletCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Darya on 12.05.2015.
 */
public class CommandFactory {
    public static ServletCommand getCommand(HttpServletRequest request) {
       return CommandManager.getCommand( request.getParameter("event") );
    }
}
