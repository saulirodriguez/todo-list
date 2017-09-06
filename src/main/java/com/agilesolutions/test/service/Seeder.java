package com.agilesolutions.test.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
//    private PlayerRepository playerRepository;
//    private CategoryRepository categoryRepository;
//    private CategoryService categoryService;
    private LoggerManager logger = LoggerManager.getInstance();

//    public Seeder(PlayerRepository playerRepository, CategoryRepository categoryRepository, CategoryService categoryService) {
//        this.playerRepository = playerRepository;
//        this.categoryRepository = categoryRepository;
//        this.categoryService = categoryService;
//    }

    @Override
    public void run(String ...args) {
        logger.log("Running Seeder...");
//        String[] categoryNames = { "Attack", "Defense", "Magic", "Cooking", "Crafting"};
//        List<Category> categories = new ArrayList<>();
//        Arrays.stream(categoryNames)
//                .forEach((c) -> categories.add(new Category(c.toLowerCase(), c, c + " Scores")));
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
        logger.log("DONE");
    }
}
