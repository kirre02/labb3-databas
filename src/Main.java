import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ProjectDAO projectDAO = new ProjectDAO();
    private static final TasksDAO taskDAO = new TasksDAO();

    public static void main(String[] args) {
        boolean quit = false;
        printActions();

        while (!quit) {
            System.out.println("\nVälj (6 för att visa val):");
            int action = scanner.nextInt();
            scanner.nextLine(); // Konsumera newline

            switch (action) {
                case 0 -> {
                    System.out.println("\nStänger ner...");
                    quit = true;
                }
                case 1 -> projectSelectAll();
                case 2 -> inputProjectInsert();
                case 3 -> inputTaskInsert();
                case 4 -> projectUpdate();
                case 5 -> listTasksForProject();
                case 6 -> printActions();
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void printActions() {
        System.out.println("\nVälj:\n");
        System.out.println("0  - Stäng av\n" +
                "1  - Visa alla projekt\n" +
                "2  - Lägg till ett nytt projekt\n" +
                "3  - Lägg till en uppgift till ett projekt\n" +
                "4  - Uppdatera ett projekt\n" +
                "5  - Visa uppgifter för ett projekt\n" +
                "6  - Visa en lista över alla val.");
    }

    private static void projectSelectAll() {
        projectDAO.listProjects();
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
}
