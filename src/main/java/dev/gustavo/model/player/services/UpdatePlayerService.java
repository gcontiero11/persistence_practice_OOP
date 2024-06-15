package dev.gustavo.model.player.services;

import dev.gustavo.model.player.dto.PlayerDto;
import dev.gustavo.persistence.dao.PlayerDao;
import dev.gustavo.persistence.daoImpl.PlayerDaoImpl;

public class UpdatePlayerService {
    private final PlayerDao playerDao;
    public UpdatePlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public void update(PlayerDto player){
        playerDao.update(player);
    }
}
