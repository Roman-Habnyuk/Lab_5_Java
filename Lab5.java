import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Створення таблиці для Zoo
            String createZooTable = "CREATE TABLE Zoo (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) NOT NULL," +
                    "location VARCHAR(255)" +
                    ")";
            statement.executeUpdate(createZooTable);

            // Створення таблиці для Animal
            String createAnimalTable = "CREATE TABLE Animal (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "species VARCHAR(255) NOT NULL," +
                    "age INT," +
                    "zoo_id INT," +
                    "FOREIGN KEY (zoo_id) REFERENCES Zoo(id)" +
                    ")";
            statement.executeUpdate(createAnimalTable);

            // Створення таблиці для Enclosure
            String createEnclosureTable = "CREATE TABLE Enclosure (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "type VARCHAR(255) NOT NULL," +
                    "capacity INT," +
                    "zoo_id INT," +
                    "FOREIGN KEY (zoo_id) REFERENCES Zoo(id)" +
                    ")";
            statement.executeUpdate(createEnclosureTable);

            System.out.println("Tables created successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
