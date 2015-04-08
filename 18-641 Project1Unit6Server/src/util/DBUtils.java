package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import database.SQL;

public class DBUtils {
    private List<String> createTableList;
    public DBUtils() {
        createTableList = new ArrayList<>();
        createTableList.add(new SQL().createAutomobilesTable());
        createTableList.add(new SQL().createOptionSetsTable());
        createTableList.add(new SQL().createOptionsTable());
    }
    public void initializeDatabase() throws SQLException {
        createDateBase();
        createTablesInDB();
    }
    protected void createDateBase() throws SQLException {
        Properties props = new Properties();
        FileInputStream in;
        String url = null;
        String username = null;
        String password = null;
        try {
            in = new FileInputStream("Database.properties");
            props.load(in);
            String drivers = props.getProperty("jdbc.drivers");
            if(drivers != null)
                System.setProperty("jdbc.drivers", drivers);
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url, username, password);
                Statement stmt = conn.createStatement();) {
            stmt.execute(new SQL().createDB());
        }
    }
    protected void createTablesInDB() throws SQLException {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();) {
            if (!doesTableExistInDB(conn, "Automobile")) {
                for (String query: createTableList) {
                    System.out.println("Executing: " + query);
                    stmt.execute(query);
                }
            }
        }
    }
    public boolean doesTableExistInDB(Connection conn, String tableName) throws SQLException {
        if(conn == null || tableName == null || "".equals(tableName.trim()))
            return false;

        boolean tableExists = false;
        final String SELECT_QUERY = new SQL().checkTableExistInDB();
        ResultSet rs = null;
        try (PreparedStatement selectStmt = conn.prepareStatement(SELECT_QUERY)) {
            selectStmt.setString(1, tableName.toUpperCase());
            rs = selectStmt.executeQuery();
            int tableCount = 0;
            if (rs.next()) {
                tableCount = rs.getInt(1);
                if (tableCount > 0) {
                    tableExists = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return tableExists;
    }
    public Connection getConnection() throws SQLException {
        Properties props = new Properties();
        FileInputStream in;
        String url = null;
        String db = null;
        String username = null;
        String password = null;
        try {
            in = new FileInputStream("Database.properties");
            props.load(in);
            String drivers = props.getProperty("jdbc.drivers");
            if(drivers != null)
                System.setProperty("jdbc.drivers", drivers);
            url = props.getProperty("jdbc.url");
            db = props.getProperty("jdbc.db");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url+"/"+db, username, password);
    }
}
