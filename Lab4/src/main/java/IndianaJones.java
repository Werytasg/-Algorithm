import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class IndianaJones {

    public static void main(String[] args) {

        Way way = new Way();
        Path path = new Path();
        way.JonesWay();
        int height = way.getH();
        int width = way.getW();
        char[][] course = way.getCourse();

        String fileName = "Ijones.out";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Number paths: " + path.getPathNumber(course, height, width));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
