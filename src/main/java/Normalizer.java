import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;


/**
 * Created by valentin on 19.11.16.
 */
public class Normalizer {
    private static ArrayList<Integer> toNormilizeList = new ArrayList<>();
    public static void main(String args[])throws IOException {
        File f = null;
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("chairs.jpg"));
        } catch (IOException ignored) {
        }
        if (img != null){
            //File toNormilize = new File("toNormalize.txt");
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    int clr = img.getRGB(x, y);
                    int red = (clr & 0x00ff0000) >> 16;
                    int green = (clr & 0x0000ff00) >> 8;
                    int blue = clr & 0x000000ff;
                    //System.out.println(red);
                    //System.out.println(green);
                    //System.out.println(blue);
                    toNormilizeList.add(blue);
                }
            }
            for (int i = 0; i < 16000; i++){
                toNormilizeList.add(150);
            }
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    int newColor = (int)(normilize(x,y, width)*256);
                    //while (newColor < 0 || newColor > 255)
                    //    newColor = (int)(normilize(x,y, width)*256);
                    int alpha = 255;
                    int p = (alpha<<24) | (newColor<<16) | (newColor<<8) | newColor; //pixel
                    newImg.setRGB(y, x, p);
                }
            }
            try{
                f = new File("ChairsNormalized.png");
                ImageIO.write(newImg, "png", f);
            }catch(IOException e){
                System.out.println("Error: " + e);
            }

        }
    }

    private static double normilize(int x, int y, int width){
        double sum = 0, res;
        for (int i=0;i<25;i++)
            sum+=1.0*getNearPixel(x,y + 3,width).get(i)/256;
        res = Math.abs((Math.sqrt(2.0)*0.06/*0.2 in original*/*(sum-12.5)/1.99661) + 0.5); //0.05 is OK
        //System.out.println((int)(res * 256));


        while (res >1)
            res = 0.5;
        return res;
    }

    private static int getRandomPixel(){
        return toNormilizeList.get((int)(Math.random()*448900));
    }
    private static ArrayList<Integer> getNearPixel(int x, int y, int width){
        ArrayList<Integer> list = new ArrayList<>();

        list.add(toNormilizeList.get(x + y * width));
        list.add(toNormilizeList.get(x + y * width + 1));
        list.add(toNormilizeList.get(x + y * width - 1));
        list.add(toNormilizeList.get(x + (y + 1) * width));
        list.add(toNormilizeList.get(x + (y - 1) * width));
        list.add(toNormilizeList.get(x + (y + 1) * width + 1));
        list.add(toNormilizeList.get(x + (y + 1) * width - 1));
        list.add(toNormilizeList.get(x + (y - 1) * width + 1));
        list.add(toNormilizeList.get(x + (y - 1) * width - 1));


        list.add(toNormilizeList.get(x + y * width + 2));
        list.add(toNormilizeList.get(x + y * width - 2));
        list.add(toNormilizeList.get(x + (y + 2) * width));
        list.add(toNormilizeList.get(x + (y - 2) * width));
        list.add(toNormilizeList.get(x + (y + 2) * width + 2));
        list.add(toNormilizeList.get(x + (y + 2) * width - 2));
        list.add(toNormilizeList.get(x + (y - 2) * width + 2));
        list.add(toNormilizeList.get(x + (y - 2) * width - 2));

        list.add(toNormilizeList.get(x + (y + 1) * width + 2));
        list.add(toNormilizeList.get(x + (y + 1) * width - 2));
        list.add(toNormilizeList.get(x + (y - 1) * width + 2));
        list.add(toNormilizeList.get(x + (y - 1) * width - 2));
        list.add(toNormilizeList.get(x + (y + 2) * width + 1));
        list.add(toNormilizeList.get(x + (y + 2) * width - 1));
        list.add(toNormilizeList.get(x + (y - 2) * width + 1));
        list.add(toNormilizeList.get(x + (y - 2) * width - 1));
        return list;
    }
}
