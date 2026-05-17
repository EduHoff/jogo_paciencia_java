package utils;

public class ConsoleUtils {

    public static void clear() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            
            String[] command = os.contains("win") ? new String[]{"cmd", "/c", "cls"} : new String[]{"clear"};
            
            new ProcessBuilder(command)
                .inheritIO()
                .start()
                .waitFor();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}