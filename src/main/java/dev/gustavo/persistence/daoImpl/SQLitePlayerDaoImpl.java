package dev.gustavo.persistence.daoImpl;

import dev.gustavo.model.player.dto.PlayerDto;
import dev.gustavo.persistence.ConnectionFactory;
import dev.gustavo.persistence.dao.PlayerDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class SQLitePlayerDaoImpl implements PlayerDao {
    @Override
    public Set<PlayerDto> findAll() {
        Set<PlayerDto> dtos = new HashSet<>();
        try (Statement statement = ConnectionFactory.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM player");
            while (res.next()) {
                PlayerDto dto = new PlayerDto(UUID.fromString(res.getString("uuid")),
                        res.getInt("team_id"),
                        res.getString("name"),
                        res.getInt("number"),
                        res.getString("position"),
                        res.getBoolean("is_fielded")
                );
                dtos.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtos;
    }

    @Override
    public Optional<PlayerDto> findById(UUID uuid) {
        String sql = "SELECT * FROM player WHERE uuid  = ?;";
        try (PreparedStatement statement = ConnectionFactory.createPrepareStatement(sql)) {
            statement.setObject(1, uuid);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                return Optional.of(new PlayerDto(UUID.fromString(res.getString("uuid")),
                        res.getInt("team_id"),
                        res.getString("name"),
                        res.getInt("number"),
                        res.getString("position"),
                        res.getBoolean("is_fielded")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(PlayerDto dto) {
        String sql = "INSERT INTO player(uuid, team_id, name, number, position, is_fielded) values (?,?,?,?,?,?);";
        try (PreparedStatement statement = ConnectionFactory.createPrepareStatement(sql)) {
            statement.setObject(1, dto.uuid());
            statement.setInt(2, dto.teamId());
            statement.setString(3, dto.name());
            statement.setInt(4, dto.number());
            statement.setString(5, dto.position());
            statement.setBoolean(6, dto.isFielded());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlayerDto dto) {
        Optional<PlayerDto> savedPlayer = findById(dto.uuid());
        if (savedPlayer.isPresent()) {
            delete(savedPlayer.get());
            save(dto);
        } else {
            System.err.println("Player does not exists");
        }
    }

    @Override
    public void delete(PlayerDto dto) {
        String sql = "DELETE FROM player WHERE uuid = ?";
        try (PreparedStatement statement = ConnectionFactory.createPrepareStatement(sql)) {
            statement.setObject(1, dto.uuid());
            statement.executeUpdate();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
