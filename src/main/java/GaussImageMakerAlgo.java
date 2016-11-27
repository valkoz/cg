import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by valentin on 27.11.16.
 */
public class GaussImageMakerAlgo {
    class Pixel{
        Integer x;
        Integer y;
        Integer color;
    }
    static private ArrayList<Integer> storage = new ArrayList<>();
    static private Integer [] stor = new Integer [256];
    //static private Map<Integer>
    public static void fillStorageFromImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        int size = width * height;
        for (int i = 0; i < 256; i++)
            stor[i] = 0;
        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //storage.add(image.getRGB(x, y) % 256 + 256);
                stor[image.getRGB(x, y) % 256 + 255]++;
            }
        }
        System.out.println(image.getRGB(width-1 , height -1) % 256 + 256);
        for (int i = 0; i < 256; i++)
            System.out.println(stor[i]);

    }


    public void normilize(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        int size = width * height;


    }
}
