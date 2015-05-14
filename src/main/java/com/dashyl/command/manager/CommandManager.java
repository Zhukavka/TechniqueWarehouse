package com.dashyl.command.manager;

import com.dashyl.command.*;
import com.dashyl.command.auth.LoginCommand;
import com.dashyl.command.auth.LogoutCommand;
import com.dashyl.command.order.AddToOrderCommand;
import com.dashyl.command.order.ApplyOrderCommand;
import com.dashyl.command.order.CancelOrderCommand;
import com.dashyl.command.order.RemoveFromOrderCommand;
import com.dashyl.command.product.AddProductsCommand;
import com.dashyl.command.product.FindProductCommand;
import com.dashyl.command.product.SortProductCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Darya on 12.05.2015.
 */
public class CommandManager {
    private static final Map<String, ServletCommand> commands;

    static {
        commands = new HashMap<String, ServletCommand>();
        commands.put("add_client", new AddClientCommand());
        commands.put("add_products", new AddProductsCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("add_user", new AddUserCommand());
        commands.put("findProduct", new FindProductCommand());
        commands.put("sortProduct", new SortProductCommand());
        commands.put("addToOrder", new AddToOrderCommand());
        commands.put("cancelOrder", new CancelOrderCommand());
        commands.put("removeFromOrder", new RemoveFromOrderCommand());
        commands.put("applyOrder", new ApplyOrderCommand());
    }

    public static ServletCommand getCommand(String key) {
        return commands.getOrDefault(key, new EmptyCommand());
    }
}
