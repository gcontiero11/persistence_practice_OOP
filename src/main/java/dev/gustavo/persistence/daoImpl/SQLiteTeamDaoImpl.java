package dev.gustavo.persistence.daoImpl;

import dev.gustavo.model.team.dto.TeamDto;
import dev.gustavo.persistence.ConnectionFactory;
import dev.gustavo.persistence.dao.TeamDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SQLiteTeamDaoImpl implements TeamDao {


    @Override
    public Set<TeamDto> findAll() {
        Set<TeamDto> dtos = new HashSet<>();
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM player");
            while (res.next()) {
                TeamDto dto = new TeamDto(res.getInt("id"),
                        res.getString("name"),
                        res.getString("base_location"),
                        UUID.fromString(res.getString("captain_id")),
//                      res.getObject("captain_id",UUID.class); -> função não implementada pelo driver do SQLite JDBC
                        res.getString("coach_name")
                );
                dtos.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtos;
    }

    @Override
    public Optional<TeamDto> findById(int id) {
        String sql = "SELECT * FROM team WHERE id  = ?;";
        try (PreparedStatement statement = ConnectionFactory.createPrepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                if (res.getString("captain_id") == null){
                    return Optional.of(new TeamDto(res.getInt("id"),
                            res.getString("name"),
                            res.getString("base_location"),
                            null,
                            res.getString("coach_name")
                    ));
                }
                return Optional.of(new TeamDto(res.getInt("id"),
                        res.getString("name"),
                        res.getString("base_location"),
                        UUID.fromString(res.getString("captain_id")),
                        res.getString("coach_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(TeamDto dto) {
        String sql = "INSERT INTO team(id,name,base_location,captain_id,coach_name) values(?,?,?,?,?);";
        try (PreparedStatement statement = ConnectionFactory.createPrepareStatement(sql)) {
            statement.setInt(1, dto.id());
            statement.setString(2, dto.name());
            statement.setString(3, dto.baseLocation());
            statement.setObject(4, dto.captainId());
            statement.setString(5, dto.coachName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TeamDto dto) {
        Optional<TeamDto> savedTeam = findById(dto.id());
        if (savedTeam.isPresent() && savedTeam.get().id() == dto.id()) {
            delete(savedTeam.get());
            save(dto);
        } else {
            System.err.println("Team does not exists or trying to update with different id");
        }
    }

    @Override
    public void delete(TeamDto dto) {
        String sql = "DELETE FROM team WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPrepareStatement(sql)) {
            statement.setObject(1, dto.id());
            statement.executeUpdate();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
