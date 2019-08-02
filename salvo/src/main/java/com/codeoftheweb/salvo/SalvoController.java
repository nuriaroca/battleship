package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController{

    @Autowired
    private GameRepository gameRepo;

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
        return pdto;
    }




}