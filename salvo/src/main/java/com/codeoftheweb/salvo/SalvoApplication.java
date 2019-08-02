package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}

    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepo, GameRepository gameRepo, GamePlayerRepository gamePlayerRepo) {
        return (args) -> {
            Player p1 = new Player("Jack", "Bauer", "j.bauer@ctu.gov");
            Player p2  = new Player("Chloe", "O'Brian", "c.obrian@ctu.gov");
            Player p3 = new Player("Kim", "Bauer", "kim_bauer@gmail.com");

            playerRepo.save(p1);
            playerRepo.save(p2);
            playerRepo.save(p3);

            Date now = new Date();

            Game g1 = new Game();
            Game g2 = new Game();
            Game g3 = new Game();

            g1.setCreationDate(now);
            g2.setCreationDate(Date.from(now.toInstant().plusSeconds(3600)));
            g3.setCreationDate(Date.from(now.toInstant().plusSeconds(3600 * 2)));

            gameRepo.save(g1);
            gameRepo.save(g2);
            gameRepo.save(g3);

            GamePlayer gp1 = new GamePlayer (g1, p1);
            GamePlayer gp2 = new GamePlayer (g1, p2);
            GamePlayer gp3 = new GamePlayer (g2, p1);
            GamePlayer gp4 = new GamePlayer (g2, p3);

            gamePlayerRepo.save(gp1);
            gamePlayerRepo.save(gp2);
            gamePlayerRepo.save(gp3);
            gamePlayerRepo.save(gp4);

            Ship s1 = new Ship();
            Ship s2 = new Ship();
            Ship s3 = new Ship();
            Ship s4 = new Ship();

        };
    }
}

