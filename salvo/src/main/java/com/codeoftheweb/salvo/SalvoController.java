package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController{

    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private GamePlayerRepository gamePlayerRepo;
    @Autowired
    private PlayerRepository playerRepo;

    private List<String> salvoEnemy = new ArrayList<>();

    @RequestMapping("/games")
    public List<Map<String, Object>> getAll() {
        return gameRepo.findAll()
                .stream()
                .map(game -> gameDTO(game))
                .collect(Collectors.toList());
    }

    private Map<String, Object> gameDTO(Game game){
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", game.getId());
        dto.put("date", game.getCreationDate());
        dto.put("gamePlayer", game.getGamePlayers()
        .stream()
                        .sorted((a, b) -> {
                            return (int)(a.getId() - b.getId());
                        })
        .map(gamePlayer -> gamePlayerDTO(gamePlayer))
                .collect(Collectors.toList())
        );
        return dto;
    }

    private Map<String, Object> gamePlayerDTO(GamePlayer gamePlayer){
        Map<String, Object> gpdto = new HashMap<>();
        gpdto.put("id", gamePlayer.getId());
        gpdto.put("player", playerDTO(gamePlayer.getPlayer()));
        return gpdto;
    }

    private Map<String, Object> playerDTO(Player player){
        Map<String, Object> pdto = new HashMap<>();
        pdto.put("id", player.getId());
        pdto.put("user", player.getUserName());
        pdto.put("firstName", player.getFirstName());
        pdto.put("lastName", player.getLastName());
        return pdto;
    }

    @RequestMapping("/games_view/{gamePlayerId}")
    public Map<String, Object> findGamePlayer(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepo.getOne(gamePlayerId);
        Game game = gamePlayer.getGame();
        return gameDetailsDTO(game, gamePlayer);
    }

    private Map<String, Object> gameDetailsDTO(Game game, GamePlayer gamePlayer){
        Map<String, Object> gddto = new HashMap<>();
        gddto.put("id", game.getId());
        gddto.put("date", game.getCreationDate());
        gddto.put("gamePlayers", game.getGamePlayers()
                .stream()
                .map(gp -> gamePlayerDTO(gp))
                .collect(Collectors.toList())
        );
        gddto.put("ships", gamePlayer.getShips()
                .stream()
                .map(ship -> shipDTO(ship))
                .collect(Collectors.toList())
        );
        gddto.put("salvo", gamePlayer.getSalvos()
                .stream()
                .map(salvo -> salvoDTO(salvo))
                .collect(Collectors.toList())
        );
        gddto.put("salvoEnemy", getEnemy(gamePlayer).getSalvos()
                .stream()
                .map(salvo -> salvoDTO(salvo))
                .collect(Collectors.toList())
        );
        return gddto;
    }

    private Map<String, Object> shipDTO (Ship ship){
        Map<String, Object> sdto = new HashMap<>();
        sdto.put("type", ship.getType());
        sdto.put("location", ship.getLocation());
        return sdto;
    }

    private Map<String, Object> salvoDTO (Salvo salvo) {
        Map<String, Object> svdto = new HashMap<>();
        svdto.put("turn", salvo.getTurn());
        svdto.put("salvoLocations", salvo.getSalvoLocations());
        return svdto;
    }

    private GamePlayer getEnemy (GamePlayer gamePlayer){
       Game currentGame = gamePlayer.getGame();
       // si no existe uno de los jugadores enseñame el otro
       if(currentGame.getGamePlayers().isEmpty()){
           return gamePlayer;
       }
       //for each. por cada gp, mira si la ID del gp conincide con la ID del game
        //si no coincide devuelveme el gp, porque es el enemigo
        //(la ID del game es igual a la del player login)
        for ( GamePlayer gp: currentGame.getGamePlayers()) {
            if(gp.getId() != gamePlayer.getId()){
                return gp;
            }
        }
        //????????
        return gamePlayer;
    }

    @RequestMapping("/leader_board")
    public Map<String, Object> playersBoard (){
        Map<String, Object> pbdto = new HashMap<>();
        playerRepo.findAll()
                .stream()
                .forEach(player -> pbdto.put(player.getUserName(), getScore(player.getScores())));
        return pbdto;
    }

    private Map<String, Double> getScore(Set<Score> scores){
        Map<String, Double> gsdto = new HashMap<>();
        gsdto.put("points", 0.0);
        gsdto.put("wins", 0.0);
        gsdto.put("loses", 0.0);
        gsdto.put("draws", 0.0);

        scores.stream().forEach(score -> {
        if(score.getPoints() == 1.0){
            gsdto.put("points", gsdto.get("points") + 1.0);
            gsdto.put("wins", gsdto.get("wins") + 1.0);
        } else if (score.getPoints() == 0.5){
            gsdto.put("draws", gsdto.get("draws") + 1.0);
            gsdto.put("points", gsdto.get("points") + 0.5);
            } else {
            gsdto.put("loses", gsdto.get("loses") + 1.0);
            }
        });

        return gsdto;
    }
}