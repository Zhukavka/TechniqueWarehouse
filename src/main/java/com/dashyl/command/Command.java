package com.dashyl.command;

/**
 * Created by Darya on 21.04.2015.
 */
public interface Command {
    void execute();
    //void undo();

    @Override
    String toString();
}
