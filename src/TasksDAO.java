import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TasksDAO {
    public void addTask(String description, int projectId) {
        String insertSQL = "INSERT INTO tasks (description, project_id) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, description);
            pstmt.setInt(2, projectId);
            pstmt.executeUpdate();
            System.out.println("Task added to project " + projectId);
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void listTasksForProject(int projectId) {
        String query = """
                SELECT tasks.id, tasks.description, tasks.status, tasks.created_at
                FROM tasks
                WHERE project_id = ?
                """;

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, projectId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Tasks for project " + projectId + ":");
            while (rs.next()) {
                System.out.printf("[%d] %s (%s) - Created at: %s%n",
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing tasks: " + e.getMessage());
        }
    }

    public void markTaskAsCompleted(int taskId) {
        String updateSQL = "UPDATE tasks SET status = 'completed' WHERE id = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setInt(1, taskId);
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Task " + taskId + " marked as completed.");
            } else {
                System.out.println("Task not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating task: " + e.getMessage());
        }
    }
}

