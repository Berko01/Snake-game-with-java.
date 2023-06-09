import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String process;
        String direction;
        System.out.println("Merhaba Snake Game oyununa hosgeldiniz. Baslamak icin B tuşlayınız.");
        scanner.nextLine();

        SnakeGame snakeGame1 = new SnakeGame();
        snakeGame1.DrawTable();

        while (true) {
            System.out.println(
                    "Lutfen sırasıyla yapılacak islemi belirleyin ve yon belirtin.\nHareket icin h yazınız.\nArdından w,a,s,d olmak üzere bir yon belirtiniz.");
            process = scanner.nextLine();
            direction = scanner.nextLine();

            snakeGame1.SnakeHeadProcess(direction, process);
            snakeGame1.DrawTable();
            
        }

    }
}