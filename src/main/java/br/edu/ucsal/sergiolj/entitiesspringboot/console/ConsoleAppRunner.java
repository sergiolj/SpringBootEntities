package br.edu.ucsal.sergiolj.entitiesspringboot.console;

import br.edu.ucsal.sergiolj.entitiesspringboot.console.commands.BuildingCmdFindAll;
import br.edu.ucsal.sergiolj.entitiesspringboot.console.commands.ConstructionProjectByBuilding;
import br.edu.ucsal.sergiolj.entitiesspringboot.console.commands.Exit;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
@RequiredArgsConstructor // Lombok vai se encarregar de criar o construtor e injetar as dependências
public class ConsoleAppRunner implements CommandLineRunner {
    private final Exit exit;
    private final BuildingCmdFindAll buildingCmdFindAll;
    private final ConstructionProjectByBuilding constructionProjectByBuilding;

    @Override
    public void run(String... args) throws Exception {
        ConsoleMenu menu = new ConsoleMenu();

        menu.putOption(exit);
        menu.putOption(buildingCmdFindAll);
        menu.putOption(constructionProjectByBuilding);
        menu.start();

    }
}
