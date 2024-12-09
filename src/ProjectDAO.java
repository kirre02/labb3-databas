import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDAO {
    public void addProject(String name, String description) {
        String insertSQL = "INSERT INTO projects (name, description) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
            System.out.println("Project added: " + name);
        } catch (SQLException e) {
            System.out.println("Error adding project: " + e.getMessage());
        }
    }

    public void listProjects() {
        String query = "SELECT id, name, description FROM projects";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Projects:");
            while (rs.next()) {
                System.out.printf("[%d] %s - %s%n", rs.getInt("id"), rs.getString("name"), rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing projects: " + e.getMessage());
        }
    }

    public void listProjectTasksJoin() {
        String query = """
            SELECT p.name AS project_name, t.description AS task_description, t.status, t.created_at
            FROM projects p
            LEFT JOIN tasks t ON p.id = t.project_id
            ORDER BY p.id, t.id;
            """;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Project and Task Details:");
            while (rs.next()) {
                System.out.printf("Project: %s | Task: %s | Status: %s | Created At: %s%n",
                        rs.getString("project_name"),
                        rs.getString("task_description"),
                        rs.getString("status"),
                        rs.getString("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching project-task join: " + e.getMessage());
        }
    }


    public void deleteProject(int projectId) {
        String deleteSQL = "DELETE FROM projects WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, projectId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Projekt raderat.");
            } else {
                System.out.println("Projekt hittades inte.");
            }
        } catch (SQLException e) {
            System.out.println("Fel vid radering av projekt: " + e.getMessage());
        }
    }


    public void updateProject(int projectId, String name, String description) {
        String updateSQL = "UPDATE projects SET name = ?, description = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, projectId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Projekt uppdaterat.");
            } else {
                System.out.println("Projekt hittades inte.");
            }
        } catch (SQLException e) {
            System.out.println("Fel vid uppdatering av projekt: " + e.getMessage());
        }
    }

}

