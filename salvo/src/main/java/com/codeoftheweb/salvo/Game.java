package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    private Set<Score> scores = new HashSet<>();


    private Date creationDate;

    public Game() {
        // this.creationDate = new Date();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGamePlayers(Set<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public long getId() {
        return id;
    }

}
