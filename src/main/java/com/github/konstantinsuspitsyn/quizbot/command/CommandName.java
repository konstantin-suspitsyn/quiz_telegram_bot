package com.github.konstantinsuspitsyn.quizbot.command;

/**
 * List of Command names in one place
 */
public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("nothing"),
    STAT("/stat"),
    GETNAME("/getname"),
    ASKQUESTION("/askquestion"),
    COMMANDFROMSTATE("commandfromstate");

    public String getCommandName() {
        return commandName;
    }

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }
}
