package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepo, GameRepository gameRepo, GamePlayerRepository gamePlayerRepo, ShipRepository shipRepo, SalvoRepository salvoRepo, ScoreRepository scoreRepo) {
        return (args) -> {
            Player p1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
            Player p2  = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
            Player p3 = new Player("Kim", "Bauer", "kim_bauer@gmail.com");
            Player p4 = new Player("Tony", "Almeida", "t.almeida@ctu.gov");

            playerRepo.save(p1);
            playerRepo.save(p2);
            playerRepo.save(p3);
            playerRepo.save(p4);

            Date now = new Date();

            Game g1 = new Game();
            Game g2 = new Game();
            Game g3 = new Game();
            Game g4 = new Game();
            Game g5 = new Game();
            Game g6 = new Game();
            Game g7 = new Game();
            Game g8 = new Game();

            g1.setCreationDate(now);
            g2.setCreationDate(Date.from(now.toInstant().plusSeconds(3600)));
            g3.setCreationDate(Date.from(now.toInstant().plusSeconds(3600 * 2)));
            g4.setCreationDate(Date.from(now.toInstant().plusSeconds(7200 * 2)));
            g5.setCreationDate(Date.from(now.toInstant().plusSeconds(7200 * 2)));
            g6.setCreationDate(Date.from(now.toInstant().plusSeconds(7200 * 2)));
            g7.setCreationDate(Date.from(now.toInstant().plusSeconds(7200 * 2)));
            g8.setCreationDate(Date.from(now.toInstant().plusSeconds(7200 * 2)));

            gameRepo.save(g1);
            gameRepo.save(g2);
            gameRepo.save(g3);
            gameRepo.save(g4);
            gameRepo.save(g5);
            gameRepo.save(g6);
            gameRepo.save(g7);
            gameRepo.save(g8);

            GamePlayer gp1 = new GamePlayer (g1, p1);
            GamePlayer gp2 = new GamePlayer (g1, p2);
            GamePlayer gp3 = new GamePlayer (g2, p1);
            GamePlayer gp4 = new GamePlayer (g2, p2);
            GamePlayer gp5 = new GamePlayer(g3, p2);
            GamePlayer gp6 = new GamePlayer(g3, p4);
            GamePlayer gp7 = new GamePlayer(g4, p2);
            GamePlayer gp8 = new GamePlayer(g4, p1);
            GamePlayer gp9 = new GamePlayer(g5, p4);
            GamePlayer gp10 = new GamePlayer(g5, p1);
            GamePlayer gp11 = new GamePlayer(g6, p3);
            GamePlayer gp12 = new GamePlayer(g7, p4);
            GamePlayer gp13 = new GamePlayer(g8, p3);
            GamePlayer gp14 = new GamePlayer(g8, p4);

            gamePlayerRepo.save(gp1);
            gamePlayerRepo.save(gp2);
            gamePlayerRepo.save(gp3);
            gamePlayerRepo.save(gp4);
            gamePlayerRepo.save(gp5);
            gamePlayerRepo.save(gp6);
            gamePlayerRepo.save(gp7);
            gamePlayerRepo.save(gp8);
            gamePlayerRepo.save(gp9);
            gamePlayerRepo.save(gp10);
            gamePlayerRepo.save(gp11);
            gamePlayerRepo.save(gp12);
            gamePlayerRepo.save(gp13);
            gamePlayerRepo.save(gp14);

            Ship s1 = new Ship("Destroyer", gp1, Arrays.asList("H2", "H3", "H4"));
            Ship s2 = new Ship("Submarine", gp1, Arrays.asList("E1", "F1", "G1"));
            Ship s3 = new Ship("Patrol Boat", gp1, Arrays.asList("B4", "B5"));
            Ship s4 = new Ship("Destroyer", gp2, Arrays.asList("B5", "C5", "D5"));
            Ship s5 = new Ship("Patrol Boat", gp2, Arrays.asList("F1", "F2"));
            Ship s6 = new Ship("Destroyer", gp3, Arrays.asList("B5", "C5", "D5"));
            Ship s7 = new Ship("Patrol Boat", gp3, Arrays.asList("C6", "C7"));
            Ship s8 = new Ship("Submarine", gp4, Arrays.asList("A2", "A3", "A4"));
            Ship s9 = new Ship("Patrol Boat", gp4, Arrays.asList("G6", "H6"));
            Ship s10 = new Ship("Destroyer", gp5, Arrays.asList("B5", "C5", "D5"));
            Ship s11 = new Ship("Patrol Boat", gp5, Arrays.asList("C6", "C7"));
            Ship s12 = new Ship("Submarine", gp6, Arrays.asList("A2", "A3", "A4"));
            Ship s13 = new Ship("Patrol Boat", gp6, Arrays.asList("G6", "H6"));
            Ship s14 = new Ship("Destroyer", gp7, Arrays.asList("B5", "C5", "D5"));
            Ship s15 = new Ship("Patrol Boat", gp7, Arrays.asList("C6", "C7"));
            Ship s16 = new Ship("Submarine", gp8, Arrays.asList("A2", "A3", "A4"));
            Ship s17 = new Ship("Patrol Boat", gp8, Arrays.asList("G6", "H6"));
            Ship s18 = new Ship("Destroyer", gp9, Arrays.asList("B5", "C5", "D5"));
            Ship s19 = new Ship("Patrol Boat", gp9, Arrays.asList("C6", "C7"));
            Ship s20 = new Ship("Submarine", gp10, Arrays.asList("A2", "A3", "A4"));
            Ship s21 = new Ship("Patrol Boat", gp10, Arrays.asList("G6", "H6"));
            Ship s22 = new Ship("Destroyer", gp11, Arrays.asList("B5", "C5", "D5"));
            Ship s23 = new Ship("Patrol Boat", gp11, Arrays.asList("C6", "C7"));
            Ship s24 = new Ship("Destroyer", gp13, Arrays.asList("B5", "C5", "D5"));
            Ship s25 = new Ship("Patrol Boat", gp13, Arrays.asList("C6", "C7"));
            Ship s26 = new Ship("Submarine", gp14, Arrays.asList("A2", "A3", "A4"));
            Ship s27 = new Ship("Patrol Boat", gp14, Arrays.asList("G6", "H6"));

            shipRepo.save(s1);
            shipRepo.save(s2);
            shipRepo.save(s3);
            shipRepo.save(s4);
            shipRepo.save(s5);
            shipRepo.save(s6);
            shipRepo.save(s7);
            shipRepo.save(s8);
            shipRepo.save(s9);
            shipRepo.save(s10);
            shipRepo.save(s11);
            shipRepo.save(s12);
            shipRepo.save(s13);
            shipRepo.save(s14);
            shipRepo.save(s15);
            shipRepo.save(s16);
            shipRepo.save(s17);
            shipRepo.save(s18);
            shipRepo.save(s19);
            shipRepo.save(s20);
            shipRepo.save(s21);
            shipRepo.save(s22);
            shipRepo.save(s23);
            shipRepo.save(s24);
            shipRepo.save(s25);
            shipRepo.save(s26);
            shipRepo.save(s27);

            Salvo sv1 = new Salvo(gp1,1, Arrays.asList("B5", "C5", "F1"));
            Salvo sv2 = new Salvo(gp2, 1, Arrays.asList("B4", "B5", "B6"));
            Salvo sv3 = new Salvo(gp1,2, Arrays.asList("F2", "D5"));
            Salvo sv4 = new Salvo(gp2,2, Arrays.asList("E1", "H3", "A2"));
            Salvo sv5 = new Salvo(gp3,1, Arrays.asList("A2", "A4", "G6"));
            Salvo sv6 = new Salvo(gp4,1, Arrays.asList("B5", "D5", "C7"));
            Salvo sv7 = new Salvo(gp3,2, Arrays.asList("A3", "H6"));
            Salvo sv8 = new Salvo(gp4,2, Arrays.asList("C5", "C6"));
            Salvo sv9 = new Salvo(gp5,1, Arrays.asList("G6", "H6", "A4"));
            Salvo sv10 = new Salvo(gp6,1, Arrays.asList("H1", "H2", "H3"));
            Salvo sv11 = new Salvo(gp5,2, Arrays.asList("A2", "A3", "D8"));
            Salvo sv12 = new Salvo(gp6,2, Arrays.asList("E1", "F2", "G3"));
            Salvo sv13 = new Salvo(gp7,1, Arrays.asList("A3", "A4", "F7"));
            Salvo sv14 = new Salvo(gp8,1, Arrays.asList("B5", "C6", "H1"));
            Salvo sv15 = new Salvo(gp7,2, Arrays.asList("A2", "G6", "H6"));
            Salvo sv16 = new Salvo(gp8,2, Arrays.asList("C5", "C7", "D5"));
            Salvo sv17 = new Salvo(gp9,1, Arrays.asList("A1", "A2", "A3"));
            Salvo sv18 = new Salvo(gp10,1, Arrays.asList("B5", "B6", "C7"));
            Salvo sv19 = new Salvo(gp9,2, Arrays.asList("G6", "7", "E6"));
            Salvo sv20 = new Salvo(gp10,2, Arrays.asList("C6", "D6", "E6"));
            Salvo sv21 = new Salvo(gp10,3, Arrays.asList("H1", "H8"));

            salvoRepo.save(sv1);
            salvoRepo.save(sv2);
            salvoRepo.save(sv3);
            salvoRepo.save(sv4);
            salvoRepo.save(sv5);
            salvoRepo.save(sv6);
            salvoRepo.save(sv7);
            salvoRepo.save(sv8);
            salvoRepo.save(sv9);
            salvoRepo.save(sv10);
            salvoRepo.save(sv11);
            salvoRepo.save(sv12);
            salvoRepo.save(sv13);
            salvoRepo.save(sv14);
            salvoRepo.save(sv15);
            salvoRepo.save(sv16);
            salvoRepo.save(sv17);
            salvoRepo.save(sv18);
            salvoRepo.save(sv19);
            salvoRepo.save(sv20);
            salvoRepo.save(sv21);

            Score sc1 = new Score (p1, g1, 1.0);
            Score sc2 = new Score (p2, g1, 0.0);
            Score sc3 = new Score (p1, g2, 0.5);
            Score sc4 = new Score (p2, g2, 0.5);
            Score sc5 = new Score (p2, g3, 1.0);
            Score sc6 = new Score (p4, g3, 0.0);
            Score sc7 = new Score (p2, g4, 0.5);
            Score sc8 = new Score (p1, g4, 0.5);

            scoreRepo.save(sc1);
            scoreRepo.save(sc2);
            scoreRepo.save(sc3);
            scoreRepo.save(sc4);
            scoreRepo.save(sc5);
            scoreRepo.save(sc6);
            scoreRepo.save(sc7);
            scoreRepo.save(sc8);
        };
    }
}

