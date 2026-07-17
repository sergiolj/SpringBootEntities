package br.edu.ucsal.sergiolj.entitiesspringboot.config;

import br.edu.ucsal.sergiolj.entitiesspringboot.Building;
import br.edu.ucsal.sergiolj.entitiesspringboot.BuildingModel;
import br.edu.ucsal.sergiolj.entitiesspringboot.ConstructionProject;
import br.edu.ucsal.sergiolj.entitiesspringboot.Design;
import br.edu.ucsal.sergiolj.entitiesspringboot.catalog.Architecture;
import br.edu.ucsal.sergiolj.entitiesspringboot.catalog.Electric;
import br.edu.ucsal.sergiolj.entitiesspringboot.catalog.Structure;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.BuildingRepository;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.ConstructionProjectRepository;
import br.edu.ucsal.sergiolj.entitiesspringboot.repositories.DesignRepository;
import br.edu.ucsal.sergiolj.entitiesspringboot.util.DesignStatus;
import br.edu.ucsal.sergiolj.entitiesspringboot.util.LevelOfDevelopment;
import br.edu.ucsal.sergiolj.entitiesspringboot.util.ProjectType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


import java.time.LocalDate;

import java.util.List;

@Configuration
public class Config{
    BuildingRepository buildingRepository;
    ConstructionProjectRepository constructionProjectRepository;
    DesignRepository designRepository;
    BuildingModel buildingModelRepository;

    public Config(BuildingRepository buildingRepository, ConstructionProjectRepository constructionProjectRepository,
                  DesignRepository designRepository) {
        this.buildingRepository = buildingRepository;
        this.constructionProjectRepository = constructionProjectRepository;
        this.designRepository = designRepository;
    }

    @Order(1)
    @Bean
    public CommandLineRunner initialConfig(){
        return args -> {
            //Define os edifícios
            Building b1 = new Building("Edifício Sede", "5Av CAB");
            Building b2 = new Building("Edifício Sede - Anexo I", "5Av CABa");
            Building b3 = new Building("Fórum Ruy Barbosa", "Campo da Pólvora");

            buildingRepository.saveAll(List.of(b1, b2, b3));

            /*
            Cria Projetos de Construção que serão atribuídos aos edifícios.
            Não faz sentido um projeto de construção sem um edifício inexistente, logo a instância já deve inicializar
            com um Building.
             */
            ConstructionProject cs1 =
                    new ConstructionProject(
                            "Cadastro Existente",
                            "Dados cadastrais não atualizados",
                            ProjectType.CONSTRUCTION,
                            DesignStatus.CONSTRUCTED,
                            LocalDate.of(2000, 1, 1),
                            LocalDate.now(),
                            b1
                    );
            ConstructionProject cs2 =
                    new ConstructionProject(
                            "Layout Gabinete 301",
                            "Estudo de novo layout e reforma do banheiro",
                            ProjectType.RENOVATION,
                            DesignStatus.UNDER_DEVELOPMENT,
                            LocalDate.of(2026, 10, 5),
                            null,
                            b1
                    );
            ConstructionProject cs3 =
                    new ConstructionProject(
                            "Reforma estacionamento Subsolo Anexo",
                            "Layout de vagas de estacionamento de motocicletas no 1° subsolo",
                            ProjectType.RENOVATION,
                            DesignStatus.DEVELOPED,
                            LocalDate.of(2026, 5, 5),
                            LocalDate.of(2026, 5, 30),
                            b1
                    );
            ConstructionProject cs4 =
                    new ConstructionProject(
                            "Reforma estacionamento",
                            "Ampliação do estacionamento externo",
                            ProjectType.ADDITION,
                            DesignStatus.UNDER_CONSTRUCTION,
                            LocalDate.of(2025, 10, 5),
                            LocalDate.of(2025, 5, 1),
                            b2
                    );

            b1.addConstructionProject(cs1);
            b1.addConstructionProject(cs2);
            b2.addConstructionProject(cs3);
            b3.addConstructionProject(cs4);

            constructionProjectRepository.saveAll(List.of(cs1,cs2,cs3, cs4));

            /* Os projetos de construção são compostos por conjuntos de um ou mais projetos específicos classificados 
            por disciplina de engenharia.
             */
            Design arq1 = new Architecture("TJBA", "Sérgio Lopes");
            Design str1 = new Structure("Eiffel CO", "Gustave Eiffel");
            Design elect1 = new Electric("NC Incorporated", "Nikola Tesla");
            Design arq2 = new Architecture("TJBA","Oscar Niemeyer");
            
            
            /* Cada projeto está associado a um modelo do edifício em uma relação OneToOne.
            No momento da criação do modelo o projeto tem que estar disponível em memória.
            Ao criar o modelo o mesmo altera o Design para ser inserido no objeto através do método setBuildingModel
             */
            BuildingModel arq1Model = new BuildingModel(arq1, "Autodesk Revit",
                    LevelOfDevelopment.LOD300,"RVT");
            
            BuildingModel str1Model = new BuildingModel(str1, "AltoQi Eberick",
                    LevelOfDevelopment.LOD200, "IFC");
            
            BuildingModel elect1Model = new BuildingModel(elect1, "AltoQi Builder (QiElétrico)",
                    LevelOfDevelopment.LOD200, "IFC");
            
            BuildingModel arq2Model = new BuildingModel(arq2, "Autodesk Revit",
                    LevelOfDevelopment.LOD300, "RVT");

            cs1.addDesign(arq1);
            cs1.addDesign(elect1);
            cs1.addDesign(str1);
            cs2.addDesign(arq2);

            designRepository.saveAll(List.of(arq1,arq2,str1,elect1));
        };
    }
}

