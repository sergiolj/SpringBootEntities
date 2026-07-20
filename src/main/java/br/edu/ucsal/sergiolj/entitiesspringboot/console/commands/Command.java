package br.edu.ucsal.sergiolj.entitiesspringboot.console.commands;

import java.util.Scanner;

public interface Command {
    boolean execute(Scanner sc);
    String getDescription();
    String getErrorMsg();
}
