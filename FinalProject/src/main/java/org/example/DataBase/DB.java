package org.example.DataBase;
import org.example.models.player.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost:5432/RealMwar";
    private static final String USER = "postgres";
    private static final String PASS = "RAyasana_Ziba#1379";

    public void createTable() {
        String createGamesTable = "CREATE TABLE IF NOT EXISTS games (" +
                "id SERIAL PRIMARY KEY, " +
                "played_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        String createWinnersTable = "CREATE TABLE IF NOT EXISTS winners (" +
                "id SERIAL PRIMARY KEY, " +
                "game_id INTEGER REFERENCES games(id) ON DELETE CASCADE, " +
                "name TEXT NOT NULL, " +
                "food INTEGER NOT NULL, " +
                "gold INTEGER NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createGamesTable);
            stmt.execute(createWinnersTable);
            logAction("Tables 'games' and 'winners' created successfully.");

        } catch (SQLException e) {
            logAction("Failed to create table: " + e.getMessage());
        }
    }

    public int saveGameWinners(ArrayList<Player> winners) {
        String insertGameSQL = "INSERT INTO games DEFAULT VALUES RETURNING id;";
        String insertWinnerSQL = "INSERT INTO winners(game_id, name, food, gold) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement insertGameStmt = conn.prepareStatement(insertGameSQL);
             PreparedStatement insertWinnerStmt = conn.prepareStatement(insertWinnerSQL)) {

            conn.setAutoCommit(false);

            ResultSet rs = insertGameStmt.executeQuery();
            int gameId = -1;
            if (rs.next()) {
                gameId = rs.getInt(1);
            } else {
                conn.rollback();
                throw new SQLException("Failed to insert new game.");
            }

            for (Player p : winners) {
                insertWinnerStmt.setInt(1, gameId);
                insertWinnerStmt.setString(2, p.getName());
                insertWinnerStmt.setInt(3, p.getFoodSupply());
                insertWinnerStmt.setInt(4, p.getGold());
                insertWinnerStmt.executeUpdate();
            }

            conn.commit();
            logAction("Game saved successfully with " + winners.size() + " winners.");
            return gameId;

        } catch (SQLException e) {
            logAction("Failed to save game winners: " + e.getMessage());
            return -1;
        }
    }

    public List<List<Player>> getAllGameResults() {
        List<List<Player>> allResults = new ArrayList<>();

        String sql = "SELECT g.id as game_id, g.played_at, w.name, w.food, w.gold " +
                "FROM games g LEFT JOIN winners w ON g.id = w.game_id " +
                "ORDER BY g.played_at ASC, w.id ASC;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Map<Integer, List<Player>> resultsByGame = new HashMap<>();

            while (rs.next()) {
                int gameId = rs.getInt("game_id");

                resultsByGame.putIfAbsent(gameId, new ArrayList<>());

                String name = rs.getString("name");
                if (name != null) {
                    Player p = new Player(
                            name,
                            rs.getInt("food"),
                            rs.getInt("gold")
                    );
                    resultsByGame.get(gameId).add(p);
                }
            }

            resultsByGame.keySet().stream()
                    .sorted()
                    .forEachOrdered(id -> allResults.add(resultsByGame.get(id)));

        } catch (SQLException e) {
            System.err.println("Error fetching all game results: " + e.getMessage());
        }

        return allResults;
    }

    public void dropTable() {
        String sqlWinners = "DROP TABLE IF EXISTS winners CASCADE;";
        String sqlGames = "DROP TABLE IF EXISTS games CASCADE;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sqlWinners);
            stmt.executeUpdate(sqlGames);
            logAction("Tables 'games' and 'winners' dropped successfully.");

        } catch (SQLException e) {
            logAction("Error dropping tables: " + e.getMessage());
        }
    }

    private void logAction(String message) {
        try (FileWriter fw = new FileWriter("src/main/java/org/example/DataBase/Log.txt", true)) {
            fw.write("[" + new Timestamp(System.currentTimeMillis()) + "] " + message + "\n");
        } catch (IOException e) {
            System.err.println("error in writing log: " + e.getMessage());
        }
    }

}
