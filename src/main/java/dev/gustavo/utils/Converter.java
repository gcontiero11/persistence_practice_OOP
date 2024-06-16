package dev.gustavo.utils;

import dev.gustavo.model.player.dto.PlayerDto;
import dev.gustavo.model.player.entity.Player;
import dev.gustavo.model.team.dto.TeamDto;
import dev.gustavo.model.team.entity.Team;
import dev.gustavo.persistence.dao.PlayerDao;
import dev.gustavo.persistence.dao.TeamDao;
import dev.gustavo.persistence.daoImpl.SQLitePlayerDaoImpl;
import dev.gustavo.persistence.daoImpl.SQLiteTeamDaoImpl;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class Converter {
    private static final TeamDao teamDao = new SQLiteTeamDaoImpl();
    private static final PlayerDao playerDao = new SQLitePlayerDaoImpl();

    public static TeamDto teamToDto(Team t) {
        Objects.requireNonNull(t, "Team must not be null");
        if (t.getCaptain() == null){
            return new TeamDto(t.getId(),
                    t.getName(),
                    t.getBaseLocation(),
                    null,
                    t.getCoachName());
        }
        return new TeamDto(t.getId(),
                t.getName(),
                t.getBaseLocation(),
                t.getCaptain().getUuid(),
                t.getCoachName());
    }

    public static Team teamFromDto(TeamDto dto, PlayerDao playerDao) {
        Objects.requireNonNull(dto, "TeamDto must not be null");

        Map<UUID,Player> teamPlayers = playerDao.findAll().stream()
                .filter(playerDto -> playerDto.teamId() == dto.id())
                .collect(Collectors.toMap(PlayerDto::uuid, Converter::playerFromDto));

        return new Team(dto.id(),
                dto.name(),
                dto.baseLocation(),
                playerFromDto(playerDao.findById(dto.captainId()).orElse(null)),
                dto.coachName(),
                teamPlayers);
    }

    public static PlayerDto playerToDto(Player p){
        Objects.requireNonNull(p, "Team dto must not be null");
        if (p.getTeam() != null){
            return new PlayerDto(p.getUuid(),
                    p.getTeam().getId(),
                    p.getName(),
                    p.getNumber(),
                    p.getName(),
                    p.isFielded());
        }
        return new PlayerDto(p.getUuid(),
                -1,
                p.getName(),
                p.getNumber(),
                p.getName(),
                p.isFielded());
    }

    public static Player playerFromDto(PlayerDto dto){
        Objects.requireNonNull(dto, "Team dto must not be null");
        return new Player(dto.uuid(),
                teamFromDto(teamDao.findById(dto.teamId()).orElseThrow(),new SQLitePlayerDaoImpl()),
                dto.name(),
                dto.number(),
                dto.position(),
                dto.isFielded());
    }
}
