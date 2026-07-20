package br.edu.ucsal.sergiolj.entitiesspringboot.console.commands;

import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionProject;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.ConstructionProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConstructionProjectByBuilding implements Command{

    private final ConstructionProjectRepository repository;
    @Transactional
    @Override
    public boolean execute(Scanner sc) {
        sc.nextLine();
        System.out.println("Informe o id do edifício: ");
        try {

            Long buildingId = Long.parseLong(sc.nextLine());

            List<ConstructionProject> projectList = repository.findByBuildingId(buildingId);
            String buildingName="";

            if (projectList.isEmpty()) {
                System.out.println("Nenhum projeto encontrado para a edificação solicitada: " + buildingId);
            } else {
                buildingName = projectList.getFirst().getBuilding().getName();
                System.out.println("\n--- Projetos encontrados ---");
                System.out.println("Edifício: " + buildingName);
                for (ConstructionProject cp : projectList) {
                    System.out.println("ID: " + cp.getId() + " | Nome: " + cp.getName() + " | Status: " + cp.getProjectStatus().name());
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Erro: Por favor, digite um número válido para o ID.");
        }

        return true;
    }

    @Override
    public String getDescription() {
        return "Lista todos os projetos criados para o edifício.";
    }

    @Override
    public String getErrorMsg() {
        return "";
    }

}
