package dev.gustavo.model.player.services;

import dev.gustavo.model.player.dto.PlayerDto;
import dev.gustavo.model.player.entity.Player;
import dev.gustavo.persistence.dao.PlayerDao;

import java.util.List;
import java.util.Optional;

public class GetPlayersService {
    private final PlayerDao playerDao;

    public GetPlayersService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public List<PlayerDto> getAll(){
        return playerDao.findAll();
    }

    public Optional<PlayerDto> getById(Player player){
        return playerDao.findById(player.getUuid());
    }

}
