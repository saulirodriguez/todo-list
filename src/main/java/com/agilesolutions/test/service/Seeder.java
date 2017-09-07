package com.agilesolutions.test.service;

import com.agilesolutions.test.exception.ExceptionHandlingController;
import com.agilesolutions.test.model.ToDo;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    private EntityManager em;
//    private PlayerRepository playerRepository;
//    private CategoryRepository categoryRepository;
//    private CategoryService categoryService;
private static final Logger LOGGER = Logger.getLogger(ExceptionHandlingController.class);
    public Seeder(EntityManager em) {
        this.em = em;
//        this.playerRepository = playerRepository;
//        this.categoryRepository = categoryRepository;
//        this.categoryService = categoryService;
    }

    @Override
    public void run(String ...args) {
        LOGGER.info("Running Seeder...");
//        ToDo todoList = new ToDo();
//        todoList.setName("My Todo List");
//        todoList.setDescription("My Todo List Description");
//        em.getTransaction().begin();
//        em.persist(todoList);
//        em.getTransaction().commit();
//
//        List<Player> players = new ArrayList<>();
//        for(int i = 1; i <21; i++) {
//            players.add(new Player("Player " + i));
//        }
//
//        for(int i = 0; i < categories.size(); i++) {
//            Category category = categories.get(i);
//            for(int j = 0; j<players.size(); j++) {
//                if(category.getId() != "overall") {
//                    double randLevel = Math.random() * 100 + 1;
//                    double randScore = Math.random() * 1000;
//
//                    Player player = players.get(j);
//                    PlayerCategory playerCategory = new PlayerCategory((int)randLevel, (int)randScore);
//                    playerCategory.setPlayer(player);
//                    playerCategory.setCategory(category);
//                    player.getPlayerCategories().add(playerCategory);
//                }
//            }
//        }
//
//        this.categoryRepository.save(categories);
//        this.playerRepository.save(players);
        LOGGER.info("DONE");
    }
}
