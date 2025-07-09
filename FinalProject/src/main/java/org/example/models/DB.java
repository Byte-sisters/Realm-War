//package org.example.models;
//
//import org.example.models.player.Player;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class DB {
//
//    private static final String URL = "jdbc:postgresql://localhost:5432/RealMWar";
//    private static final String USER = "postgres";
//    private static final String PASS = "Elahe@1385";
//
//    public static void createTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS RealMWarTable (" +
//                "turn INT, " +
//                "name VARCHAR(50), " +
//                "food INT, " +
//                "gold INT, " +
//                "structure TEXT, " +
//                "unit TEXT, " +
//                "blocks TEXT, " +
//                "trees TEXT)";
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
//             Statement stmt = conn.createStatement()) {
//
//            stmt.execute(sql);
//            System.out.println("Table created");
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//    }
//
//    public static void insertPlayer(Player player, int turn) {
//        String sql = "INSERT INTO RealMWarTable (turn,name,food,gold,structure,unit,blocks,trees) VALUES (?,?,?,?,?,?,?,?)";
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, turn);
//            pstmt.setString(2, player.getName());
//            pstmt.setInt(3, player.getFoodSupply());
//            pstmt.setInt(4, player.getGold());
//            pstmt.setString(5, null);
//            pstmt.setString(6, null);
//            pstmt.setString(7, null);
//            pstmt.setString(8, null);
//
//            pstmt.executeUpdate();
//
//            System.out.println("Player inserted");
//
//        } catch (SQLException e) {
//            System.out.println("Error: " + e);
//        }
//    }
//
//    public static boolean update(int turn, int food, int gold, String structure, String unit, String blocks, String trees, String name) {
//        String sql = "UPDATE RealMWarTable SET turn = ?, food = ?, gold = ?, structure = ?, unit = ?, blocks = ?, trees = ? WHERE name = ?";
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, turn);
//            pstmt.setInt(2, food);
//            pstmt.setInt(3, gold);
//            pstmt.setString(4, structure);
//            pstmt.setString(5, unit);
//            pstmt.setString(6, blocks);
//            pstmt.setString(7, trees);
//            pstmt.setString(8, name);
//
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0;
//
//        } catch (SQLException e) {
//            System.out.println("Error: " + e);
//            return false;
//        }
//    }
//
//    public static ArrayList<Player> getPlayers() {
//        ArrayList<Player> players = new ArrayList<>();
//        String sql = "SELECT * FROM RealMWarTable";
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                String name = rs.getString("name");
//                int food = rs.getInt("food");
//                int gold = rs.getInt("gold");
//                // بقیه فیلدها مثل structure, unit, blocks, trees اگر خواستی بخون
//
//                // فرض کن Player کانستراکتورش این شکلیه
//                Player player = new Player(name);
//                player.setFoodSupply(food);
//                player.setGold(gold);
//
//                players.add(player);
//            }
//            return players;
//
//        } catch (SQLException e) {
//            System.out.println("Error: " + e);
//            return null;
//        }
//    }
//}

package org.example.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.models.blocks.EmptyBlock;
import org.example.models.blocks.ForestBlock;
import org.example.models.player.Player;
import org.example.models.structures.*;
import org.example.models.units.*;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    private static final String URL = "jdbc:postgresql://localhost:5432/RealMWar";
    private static final String USER = "postgres";
    private static final String PASS = "Elahe@1385";

    private static final Gson gson =new GsonBuilder()
        .registerTypeAdapter(Structures.class, new structuresAdapter())
            .create();
    ;


    public static void createPlayerTable() {
        String sql = "CREATE TABLE IF NOT EXISTS RealMWarTable (" +
                "turn INT, " +
                "name VARCHAR(50), " +
                "food INT, " +
                "gold INT, " +
                "structures TEXT, " +
                "units TEXT, " +
                "blocks TEXT, " +
                "trees TEXT" +
                ")";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created (if not exists)");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void insertPlayer(Player player, int turn) {
        String sql = "INSERT INTO RealMWarTable (turn, name, food, gold, structures, units, blocks, trees) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, turn);
            pstmt.setString(2, player.getName());
            pstmt.setInt(3, player.getFoodSupply());
            pstmt.setInt(4, player.getGold());

            String structuresJson = gson.toJson(player.getStructures());
            String unitsJson = gson.toJson(player.getUnits());

            pstmt.setString(5, structuresJson);
            pstmt.setString(6, unitsJson);
            pstmt.setString(7, gson.toJson(player.getBlocks()));
            pstmt.setString(8, gson.toJson(player.getTrees()));

            pstmt.executeUpdate();
            System.out.println("Player inserted");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static boolean updatePlayer(int turn, int food, int gold, ArrayList<Structures> structures,
                                       ArrayList<Units> units, ArrayList<EmptyBlock> blocks, ArrayList<ForestBlock> trees, String name) {
        String sql = "UPDATE RealMWarTable SET turn = ?, food = ?, gold = ?, structures = ?, units = ?, blocks = ?, trees = ? WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, turn);
            pstmt.setInt(2, food);
            pstmt.setInt(3, gold);
            pstmt.setString(4, gson.toJson(structures));
            pstmt.setString(5, gson.toJson(units));
            pstmt.setString(6, gson.toJson(blocks));
            pstmt.setString(7, gson.toJson(trees));
            pstmt.setString(8, name);

            int rowAffected = pstmt.executeUpdate();
            return rowAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public static ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM RealMWarTable";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int turn = rs.getInt("turn");
                int food = rs.getInt("food");
                int gold = rs.getInt("gold");

                String structuresJson = rs.getString("structures");
                String unitsJson = rs.getString("units");

                Type structureListType = new TypeToken<List<Structures>>() {}.getType();
                Type unitListType = new TypeToken<List<Units>>() {}.getType();

                ArrayList<Structures> structures = gson.fromJson(structuresJson, structureListType);
                ArrayList<Units> units = gson.fromJson(unitsJson, unitListType);

                Player player = new Player(name,turn);
                player.setFoodSupply(food);
                player.setGold(gold);
                player.setStructures(structures);
                player.setUnits(units);

                players.add(player);
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return players;
    }

    public static void deletePlayerTable() {
        String sql = "DROP TABLE IF EXISTS RealMWarTable";

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Table deleted successfully");
        }catch (SQLException e) {
                System.out.println("Error deleting table: " + e.getMessage());
            }
    }

    public static void insertOrUpdateBoardState(ArrayList<EmptyBlock> blocks, ArrayList<ForestBlock> trees) {
        String sql = "INSERT INTO BoardState (id, blocks, trees) VALUES (1, ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET blocks = EXCLUDED.blocks, trees = EXCLUDED.trees";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String blocksJson = gson.toJson(blocks);
            String treesJson = gson.toJson(trees);

            pstmt.setString(1, blocksJson);
            pstmt.setString(2, treesJson);

            pstmt.executeUpdate();
            System.out.println("BoardState inserted or updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static BoardState getBoardState() {
        String sql = "SELECT blocks, trees FROM BoardState WHERE id=1";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String blocksJson = rs.getString("blocks");
                String treesJson = rs.getString("trees");
                Type blockListType = new TypeToken<ArrayList<EmptyBlock>>() {}.getType();
                Type treeListType = new TypeToken<ArrayList<ForestBlock>>() {}.getType();
                ArrayList<EmptyBlock> blocks = gson.fromJson(blocksJson, blockListType);
                ArrayList<ForestBlock> trees = gson.fromJson(treesJson, treeListType);
                return new BoardState(blocks, trees);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static void createBoardTable() {
        String sql = "CREATE TABLE IF NOT EXISTS BoardState (" +
                "id INT PRIMARY KEY, " +
                "blocks TEXT, " +
                "trees TEXT" +
                ")";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("BoardState table created (if not exists).");
        } catch (SQLException e) {
            System.out.println("Error creating BoardState table: " + e.getMessage());
        }
    }

    public static void deleteBoardTable() {
        String sql = "DROP TABLE IF EXISTS BoardState";

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Table deleted successfully");
        }catch (SQLException e) {
            System.out.println("Error deleting table: " + e.getMessage());
        }
    }

}
