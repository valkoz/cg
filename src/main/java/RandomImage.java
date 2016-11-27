/**
 * Created by valentin on 17.11.16.
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RandomImage{
static         ArrayList<Double> pixelList = new ArrayList<Double>();
static         ArrayList<Integer> gausspixelList = new ArrayList<Integer>();

    public static void main(String args[])throws IOException{
        //image dimension

    randomImage();
    // gaussImage();
    File f = new File("RandomImageBlack.png");
   // GaussImageMakerAlgo.fillStorageFromImage(ImageIO.read(f));


    }//main() ends here

    /*arrayList values instead of Math.random -> normalizing image*/
    private static double gauss(double param){
        double sum=0, x;

        for (int i=0;i<25;i++)
            sum+=1.0*Math.random();
        x = (Math.sqrt(2.0)*0.2*(sum-12.5)/1.99661) + 0.5;

        //System.out.println(x);
        return x;
    }

    private static void randomImage(){
        int width = 100;
        int height = 100;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        File f = null;
        int alpha = 255;
        int last = 0;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                double a = Math.random();
                int r = (int)(a*256);
                pixelList.add(a);
                int p = (alpha<<24) | (r<<16) | (r<<8) | r; //pixel
                img.setRGB(x, y, p);
                last = r;
            }
        }
        System.out.println(last);
        try (final FileWriter writer = new FileWriter("RandomFile.txt", false))
        {
            for (Double aPixelList : pixelList) {
                final String s = Integer.toString((int) (aPixelList * 256));
                writer.write(s);
                writer.write(System.lineSeparator());
                //System.out.println(s);
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        //write image
        try{
            f = new File("RandomImageBlack.png");
            ImageIO.write(img, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
        //System.out.println(img.getRGB(width - 1, height - 1) % 256);
    }

    private static void gaussImage(){
        int width = 240;
        int height = 160;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        File f = null;
        int alpha = 255;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                double value = gauss(pixelList.get(x + y * width));
                while (value<0 || value > 1)
                    value = gauss(pixelList.get(x + y * width));
                int r = (int)(value*256);
                gausspixelList.add(r);
                int p = (alpha<<24) | (r<<16) | (r<<8) | r; //pixel
                img.setRGB(x, y, p);
            }
        }


        try (final FileWriter writer = new FileWriter("GaussFile.txt", false))
        {
            for (Integer aPixelList : gausspixelList) {
                final String s = Integer.toString(aPixelList);
                writer.write(s);
                writer.write(System.lineSeparator());
                //System.out.println(s);
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        //write image
        try{
            f = new File("GaussImageBlack.png");
            ImageIO.write(img, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }




    }
}//class ends here
