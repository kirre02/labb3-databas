import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ProjectDAO projectDAO = new ProjectDAO();
    private static final TasksDAO taskDAO = new TasksDAO();

    public static void main(String[] args) {
        boolean quit = false;
        printActions();

        while (!quit) {
            System.out.println("\nVälj (9 för att visa val):");
            int action = scanner.nextInt();
            scanner.nextLine(); // Konsumera newline

            switch (action) {
                case 0 -> {
                    System.out.println("\nStänger ner...");
                    quit = true;
                }
                case 1 -> projectSelectAll();
                case 2 -> projectAndTasksSelectAll();
                case 3 -> inputProjectInsert();
                case 4 -> removeProject();
                case 5 -> inputTaskInsert();
                case 6 -> removeTaskFromProject();
                case 7 -> projectUpdate();
                case 8 -> listTasksForProject();
                case 9 -> printActions();
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void printActions() {
        System.out.println("\nVälj:\n");
        System.out.println("""
                0  - Stäng av
                1  - Visa alla projekt
                2  - Visa alla projekt med uppgifter
                3  - Lägg till ett nytt projekt
                4  - ta bort ett projekt
                5  - Lägg till en uppgift till ett projekt
                6  - Ta bort en uppgift till ett projekt
                7  - Uppdatera ett projekt
                8  - Visa uppgifter för ett projekt
                9  - Visa en lista över alla val.""");
    }

    private static void projectSelectAll() {
        projectDAO.listProjects();
    }

    private static void projectAndTasksSelectAll() {
        projectDAO.listProjectTasksJoin();
    }

    private static void inputProjectInsert() {
        System.out.print("Ange projektets namn: ");
        String name = scanner.nextLine();
        System.out.print("Ange projektets beskrivning: ");
        String description = scanner.nextLine();
        projectDAO.addProject(name, description);
    }

    private static void inputTaskInsert() {
        System.out.print("Ange projekt-ID för att lägga till uppgiften: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ange uppgiftsbeskrivning: ");
        String taskDescription = scanner.nextLine();
        taskDAO.addTask(taskDescription, projectId);
    }

    private static void projectUpdate() {
        System.out.print("Ange ID för projektet som ska uppdateras: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ange nytt projektets namn: ");
        String name = scanner.nextLine();
        System.out.print("Ange ny projektbeskrivning: ");
        String description = scanner.nextLine();
        projectDAO.updateProject(projectId, name, description);
    }

    private static void listTasksForProject() {
        System.out.print("Ange projekt-ID för att visa uppgifter: ");
        int projectId = scanner.nextInt();
        taskDAO.listTasksForProject(projectId);
    }

    private static void removeTaskFromProject() {
        System.out.print("Ange ID för uppgift som ska raderas: ");
        int taskId = scanner.nextInt();
        scanner.nextLine();
        taskDAO.deleteTask(taskId);
    }

    private static void removeProject() {
        System.out.print("Ange ID för projektet som ska raderas: ");
        int projectId = scanner.nextInt();
        scanner.nextLine();
        projectDAO.deleteProject(projectId);
    }
}
