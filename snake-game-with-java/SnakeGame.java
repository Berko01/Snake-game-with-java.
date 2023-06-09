import java.net.http.HttpHeaders;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.lang.Math;
import javax.sound.sampled.Line;
import javax.swing.SpringLayout;

public class SnakeGame {
    int line = 10, column = 15;
    char[][] gameTable = new char[line][column];
    int headLine = 0, headColumn = 0;
    Queue LineSnake = new LinkedList<Integer>();
    Queue ColumnSnake = new LinkedList<Integer>();
    int foodLine, foodColumn;

    public SnakeGame() {

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                gameTable[i][j] = '#';
            }
        }
        gameTable[headLine][headColumn] = '*';

        LineSnake.add(headLine);
        ColumnSnake.add(headColumn);

        spawnFood();

    };

    public Queue CopyQueue(Queue q1, Queue q2) {
        Iterator<Integer> it = q1.iterator();
        while (it.hasNext()) {
            q2.add(it.next());

        }
        return q2;
    }

    public void refreshGameTable() {
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                gameTable[i][j] = '#';
            }
        }
    }

    public void DrawTable() {
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(" " + gameTable[i][j]);
            }
            System.out.println();

        }

    }

    public void UpdateTable() {
        Queue tempSnakeLine = new LinkedList<Integer>();
        Queue tempSnakeColumn = new LinkedList<Integer>();

        tempSnakeLine = CopyQueue(LineSnake, tempSnakeLine);
        tempSnakeColumn = CopyQueue(ColumnSnake, tempSnakeColumn);

        refreshGameTable();

        while (tempSnakeLine.isEmpty() != true) {
            gameTable[(int) tempSnakeLine.poll()][(int) tempSnakeColumn.poll()] = '*';
        }

        gameTable[foodLine][foodColumn] = 'f';
    }

    public void updateSnakeLenght() {

        Queue newLineSnake = new LinkedList<Integer>();
        Queue newColumnSnake = new LinkedList<Integer>();

        newLineSnake.add(headLine);
        newColumnSnake.add(headColumn);

        while (LineSnake.isEmpty() != true) {
            newLineSnake.add(LineSnake.poll());
            newColumnSnake.add(ColumnSnake.poll());
        }

        LineSnake = newLineSnake;
        ColumnSnake = newColumnSnake;

        UpdateTable();
    }

    public void UpdateSnakeLocation() {
        Queue newLineSnake = new LinkedList<Integer>();
        Queue newColumnSnake = new LinkedList<Integer>();

        newLineSnake.add(headLine);
        newColumnSnake.add(headColumn);

        while (LineSnake.isEmpty() != true) {
            if (LineSnake.size() == 1) {
                LineSnake.poll();
                break;
            }
            newLineSnake.add(LineSnake.poll());
            newColumnSnake.add(ColumnSnake.poll());
        }

        LineSnake = newLineSnake;
        ColumnSnake = newColumnSnake;

        UpdateTable();
    }

    public boolean ısGameOver() {
        if (gameTable[headLine][headColumn] == '*')
            return true;

        else
            return false;

    }

    public void spawnFood() {
        Random rand = new Random();

        foodLine = rand.nextInt(line);
        foodColumn = rand.nextInt(column);

        while (gameTable[foodLine][foodColumn] == '*') {
            foodLine = rand.nextInt(line);
            foodColumn = rand.nextInt(column);
        }

        gameTable[foodLine][foodColumn] = 'f';

    }

    public boolean IsFoodEated() {

        if (gameTable[headLine][headColumn] == 'f')
            return true;
        else
            return false;

    }

    public void SnakeHeadProcess(String direction, String process) {

        if (direction.equals("w")) {
            if (headLine == 0)
                headLine = line - 1;
            else
                headLine--;
        } else if (direction.equals("s")) {
            if (headLine == line - 1)
                headLine = 0;
            else
                headLine++;
        } else if (direction.equals("a")) {
            if (headColumn == 0)
                headColumn = column - 1;
            else
                headColumn--;
        } else if (direction.equals("d")) {
            if (headColumn == column - 1)
                headColumn = 0;
            else
                headColumn++;
        }

        if (ısGameOver() == true) {
            System.out.println("Kendini ısırdın oyunu kaybettin.");
            System.exit(0);
        }

        if (IsFoodEated() == true) {
            System.out.println("Yemek yendi.");
            spawnFood();
            updateSnakeLenght();

            return;

        }

        UpdateSnakeLocation();

    }

}
