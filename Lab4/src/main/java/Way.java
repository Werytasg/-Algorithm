import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Way {
    private int w;
    private int h;

    private  char[][] course = new char[h][w];

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public char[][] getCourse() {
        return course;
    }

    public void JonesWay () {
        try {
            // Зчитування вхідного файлу
            File inputFile = new File("Ijones.in");
            Scanner scanner = new Scanner(inputFile);
             w = scanner.nextInt();
            h = scanner.nextInt();
            scanner.nextLine();

            char[][] course = new char[h][w];
            for (int i = 0; i < h; i++) {
                String line = scanner.nextLine();
                course[i] = line.toCharArray();
            }

            this.course=course;
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}