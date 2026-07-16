package br.edu.ucsal.sergiolj.entitiesspringboot.config;

import br.edu.ucsal.sergiolj.entitiesspringboot.Building;
import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionSet;
import br.edu.ucsal.sergiolj.entitiesspringboot.Design;
import br.edu.ucsal.sergiolj.entitiesspringboot.catalog.Architecture;
import br.edu.ucsal.sergiolj.entitiesspringboot.catalog.Electric;
import br.edu.ucsal.sergiolj.entitiesspringboot.catalog.Structure;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.BuildingRepository;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.ConstructionSetRepository;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.DesignRepository;
import br.edu.ucsal.sergiolj.entitiesspringboot.util.DesignStatus;
import br.edu.ucsal.sergiolj.entitiesspringboot.util.ProjectType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

import java.util.List;

@Configuration
public class Config implements CommandLineRunner{
    BuildingRepository buildingRepository;
    ConstructionSetRepository constructionSetRepository;
    DesignRepository designRepository;

    public Config(BuildingRepository buildingRepository, ConstructionSetRepository constructionSetRepository,
                  DesignRepository designRepository) {
        this.buildingRepository = buildingRepository;
        this.constructionSetRepository = constructionSetRepository;
        this.designRepository = designRepository;
    }

    @Order(1)
    @Bean
    public CommandLineRunner loadBuildings() {

        return args -> {
            Building b1 = new Building("Edifício Sede", "5Av CAB");
            Building b2 = new Building("Fórum Ruy Barbosa", "Campo da Pólvora");
            Building b3 = new Building("Fórum do Imbuí", "Imbuí");

            buildingRepository.saveAll(List.of(b1, b2, b3));
        };
    }

    @Order(2)
    @Bean
    public CommandLineRunner loadConstructionSchematics() {
        return args -> {
            ConstructionSet cs1 =
                    new ConstructionSet(
                            "Cadastro Existente",
                            "Dados cadastrais não atualizados",
                            ProjectType.CONSTRUCTION,
                            DesignStatus.CONSTRUCTED,
                            LocalDate.of(2000, 1, 1),
                            LocalDate.now(),
                            buildingRepository.findById(1L).orElseThrow()
                    );
            ConstructionSet cs2 =
                    new ConstructionSet(
                            "Layout Gabinete 301",
                            "Estudo de novo layout e reforma do banheiro",
                            ProjectType.RENOVATION,
                            DesignStatus.UNDER_DEVELOPMENT,
                            LocalDate.of(2026, 10, 5),
                            null,
                            buildingRepository.findById(1L).orElseThrow()
                    );
            ConstructionSet cs3 =
                    new ConstructionSet(
                            "Reforma estacionamento Subsolo Anexo",
                            "Layout de vagas de estacionamento de motocicletas no 1° subsolo",
                            ProjectType.RENOVATION,
                            DesignStatus.DEVELOPED,
                            LocalDate.of(2026, 5, 5),
                            LocalDate.of(2026, 5, 30),
                            buildingRepository.findById(1L).orElseThrow()
                    );
            ConstructionSet cs4 =
                    new ConstructionSet(
                            "Reforma estacionamento",
                            "Ampliação do estacionamento externo",
                            ProjectType.ADDITION,
                            DesignStatus.UNDER_CONSTRUCTION,
                            LocalDate.of(2025, 10, 5),
                            LocalDate.of(2025, 5, 1),
                            buildingRepository.findById(2L).orElseThrow()
                    );

            constructionSetRepository.saveAll(List.of(cs1, cs2, cs3, cs4));
        };
    }

    @Order(3)
    @Bean
    public CommandLineRunner loadDesigns(){
        return args -> {

            Building b1 = buildingRepository.findById(1L).orElseThrow();
            Building b2 = buildingRepository.findById(2L).orElseThrow();

            ConstructionSet cs1 = constructionSetRepository.findById(1L).orElseThrow();
            ConstructionSet cs2 = constructionSetRepository.findById(2L).orElseThrow();

            Design arq1 = new Architecture(b1, "TJBA", "Sérgio Lopes");
            Design str1 = new Structure(b1,"Eiffel CO", "Gustave Eiffel");
            Design elect1 = new Electric(b1, "NC Incorporated", "Nikola Tesla");
            Design arq2 = new Architecture(b2,"TJBA","Oscar Niemeyer");

            designRepository.saveAll(List.of(arq1,arq2,str1,elect1));

            cs1.addDesign(arq1);
            cs1.addDesign(elect1);
            cs1.addDesign(str1);
            cs2.addDesign(arq2);

            constructionSetRepository.saveAll(List.of(cs1,cs2));
        };
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

    }
}

