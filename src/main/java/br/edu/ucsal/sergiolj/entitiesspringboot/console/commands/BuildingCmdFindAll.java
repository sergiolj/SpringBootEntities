package br.edu.ucsal.sergiolj.entitiesspringboot.console.commands;

import br.edu.ucsal.sergiolj.entitiesspringboot.Building;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.BuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class BuildingCmdFindAll implements Command{
    private final BuildingRepository repository;

    @Override
    public boolean execute(Scanner sc) {
        List<Building> buildingList = repository.findAll();
        System.out.println("\n --- Edifícios cadastrados ---");
        for(Building b : buildingList){
            System.out.println("ID: "+b.getId()+ " | Nome: " + b.getName());
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "Lista edificações cadastradas";
    }

    @Override
    public String getErrorMsg() {
        return "";
    }
}
