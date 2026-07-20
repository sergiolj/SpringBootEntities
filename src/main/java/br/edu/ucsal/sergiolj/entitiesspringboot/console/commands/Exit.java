package br.edu.ucsal.sergiolj.entitiesspringboot.console.commands;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Exit implements Command{
    @Override
    public boolean execute(Scanner sc) {
        System.out.println("Desligando aplicação...");
        return false;
    }

    @Override
    public String getDescription() {
        return "Sair da aplicação";
    }

    @Override
    public String getErrorMsg() {
        return "";
    }

}
