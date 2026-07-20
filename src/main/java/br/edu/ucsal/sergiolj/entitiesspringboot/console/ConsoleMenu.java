package br.edu.ucsal.sergiolj.entitiesspringboot.console;

import br.edu.ucsal.sergiolj.entitiesspringboot.console.commands.Command;

import java.util.*;

public class ConsoleMenu {
    private final Map<Integer, Command> optionMenu = new TreeMap<>();
    private final Scanner sc = new Scanner(System.in);
    private boolean running = true;
    private int counter = 0;

    public void start(){
        int choice = 0;

        while (running){
            try{
                displayMenu();
                choice = sc.nextInt();
                Command command = optionMenu.get(choice);
                if(command != null){
                    this.running = command.execute(sc);
                }
            }catch (InputMismatchException e){
                System.out.println("Opção inválida. Apenas números inteiros [1-" + counter + "]. Tente novamente.");
                sc.nextLine();
            }
        }
        sc.close();
    }

    public void putOption(Command command){
        counter++;
        optionMenu.put(counter, command);
    }

    private void displayMenu(){
        for(Map.Entry<Integer,Command> entry : optionMenu.entrySet()){
            System.out.println("["+ entry.getKey() + "] " + entry.getValue().getDescription());
        }
        System.out.println("Digite uma opção de [1-" + counter + "]");
    }


}
