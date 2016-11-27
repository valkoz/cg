import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by valentin on 27.11.16.
 */
public class RegionConstructor {
    private static ArrayList<Region> regions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File f = new File("RandomImageBlack.png");
        BufferedImage image = ImageIO.read(f);
        int width = image.getWidth();
        int height = image.getHeight();
        int size = width * height;
        firstRegionInit();
        for (int x = 0; x < width; x++){
            for( int y = 0; y < height; y++) {
                Pixel current = new Pixel(x,y, image.getRGB(0,0) % 256 + 255);
                if (regions.size() > 0) {
                    for (Region region : regions) {
                        for (Pixel pixel : region.pixels){
                            if ((Math.abs(pixel.getX() - x) == 1) || (Math.abs(pixel.getY() - y) == 1)){
                                if (Math.abs(current.getColor() - pixel.getColor()) < 5 ){
                                    //add pixel to this region
                                    addPixelToRegion(region, current);
                                }
                                else
                                {
                                    addNewRegion(current);
                                }

                            }
                        }
                    }
                }

            }

        }
    }


    static void firstRegionInit() throws IOException {
        File f = new File("RandomImageBlack.png");
        BufferedImage image = ImageIO.read(f);
        Pixel first = new Pixel(0, 0, image.getRGB(0,0) % 256 + 255);
        Region a = new Region();
        a.addPixeltoRegion(first);
        regions.add(a);
    }

    static private void addNewRegion(Pixel pixel){
        Region a = new Region();
        a.addPixeltoRegion(pixel);
        regions.add(a);
    }

    static private void addPixelToRegion(Region region, Pixel pixel){
        region.addPixeltoRegion(pixel);
    }
}
