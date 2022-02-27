package com.github.konstantinsuspitsyn.customsurveytelegrambot.command;

/**
 * List of Command names in one place
 */
public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("nothing");

    public String getCommandName() {
        return commandName;
    }

    private final String commandName;


    CommandName(String commandName) {
        this.commandName = commandName;
    }
}
