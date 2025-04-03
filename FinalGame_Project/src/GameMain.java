import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        //added my help menu can edit later just checking if it works
        Scanner scanner=new Scanner(System.in);
        System.out.println("testing help menu type help: ");
        String a=scanner.nextLine();
        if (a.equalsIgnoreCase("help")){
            helpMenu();
        }
    }

    private static void helpMenu(){
        Frame frame=new Frame();
        frame.helpmenu();
    }
}
