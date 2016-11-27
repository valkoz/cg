import java.util.ArrayList;

/**
 * Created by valentin on 27.11.16.
 */
public class Region {
    public static ArrayList<Pixel> pixels = new ArrayList<>();
    public void addPixeltoRegion(int x, int y, int c){
        pixels.add(new Pixel(x,y,c));
    }
    public void addPixeltoRegion(Pixel p){
        pixels.add(p);
    }

    @Override
    public String toString(){
        return pixels.toString();
    }

}
