package dev.gustavo.model.player.services;

import dev.gustavo.model.player.dto.PlayerDto;
import dev.gustavo.persistence.dao.PlayerDao;

public class AddPlayerService {
    private final PlayerDao playerDao;

    public AddPlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void add(PlayerDto player) {
        playerDao.save(player);
    }
}
