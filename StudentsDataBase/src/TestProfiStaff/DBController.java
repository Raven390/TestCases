package TestProfiStaff;
import org.sqlite.*;
import java.sql.*;

public class DBController {

    private Connection connect() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        String url = "jdbc:sqlite:C:\\Users\\p_nik\\IdeaProjects\\Students\\db\\StudentsDB.db";
        dataSource.setUrl(url);
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void createTable() {
        String sql1 = "DROP TABLE IF EXISTS students;";

        try (Connection con = connect();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String createTable = "CREATE TABLE IF NOT EXISTS students (\n"
                            + " id INTEGER PRIMARY KEY,\n"
                            + " Фамилия TEXT NOT NULL,\n"
                            + " Имя TEXT NOT NULL,\n"
                            + " Отчество TEXT NOT NULL,\n"
                            + " Дата_Рождения DATE NOT NULL,\n"
                            + " Группа TEXT NOT NULL\n"
                            + ");";
        try (Connection con = connect();
             Statement stmt = con.createStatement() ) {
            stmt.execute(createTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(int id, String name, String surname, String fathersName, String birthday, String group) {
        String insert = "INSERT INTO students(id, Фамилия, Имя, Отчество, Дата_Рождения, Группа) VALUES(?,?,?,?,?,?)";

        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(insert)) {
             pstmt.setInt(1, id);
             pstmt.setString(2, name);
             pstmt.setString(3, surname);
             pstmt.setString(4, fathersName);
             pstmt.setString(5, birthday);
             pstmt.setString(6, group);
             pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String delete = "DELETE FROM students WHERE id = ?";

        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(delete)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAll() {
        String select = "SELECT * FROM students";

        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(select)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id") + " Фамилия: " + rs.getString("Фамилия") + " Имя: "
                        + rs.getString("Имя") + " Отчество: " + rs.getString("Отчество") + " Дата рождения: " + rs.getString("Дата_рождения") + " Группа: " + rs.getString("Группа"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getMaxId() {
        String select = "SELECT MAX(id) FROM students";
        int id = 0;
        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(select)) {
            ResultSet rs = pstmt.executeQuery();
            id = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

}
