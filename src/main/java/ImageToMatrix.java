import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by valentin on 28.11.16.
 */
public class ImageToMatrix {
    class Coordinate{
        Integer x;
        Integer y;
    }
    private static ArrayList<Coordinate> regions = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        File f = new File("RandomImageBlack.png");
        BufferedImage image = ImageIO.read(f);
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] matrix = new int[width][height];
        //Считывание по столбцам
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                matrix[x][y] = image.getRGB(x,y) % 256 + 255;
                //<logs>
                if (matrix[x][y] == 256)
                    System.out.println("!!!256!!!");
                if (matrix[x][y] == 0)
                    System.out.println("!!!0!!!");
                //</logs>
            }
        }
        //initialize first Region with 0,0 pixel
        //initFirstRegion();
        for (int x = 0; x < width - 1; x++) {
            for (int y = 0; y < height - 1; y++) {
                //Если текущий пиксель из матрицы matrix[x][y] есть в Области ->
                  //if (matrix[x][y+1] == matrix[x][y]) //if (Math.abs(matrix[x][y+1] - matrix[x][y]) < 3) {
                    //Если справа/снизу есть пиксель с таким же цветом, то добавить его в область
                  }
                    //Иначе создать новую область
                //Иначе создать новую область
            }
        }
        //Область содержит ArrayList из Пикселей (х, y - x,y можно представить одним числом и цвет
        // т.е. Можно обойтись Массивом интов и цветом!!! + id область и ее size, т.к отбор в область произовдится по принципу соседствования

        //System.out.println(Arrays.deepToString(matrix));
    }
}

//После прдусмотреть изменение гистограммы с помощью изменения цвета областей
//При необходимости делать сплит области
// Все это дело зациклить
// Неплохо было бы сделать сортировку по цвету (первичный параметр) и кол-ву пикселей (вторичный параметр) и идти упорядоченно по нему.
//Написать закон распределяющий пиксели в зависимости от исходного изображения взять дельту в пикселях если после нормализации входит в значение +-дельта значит все хорошо