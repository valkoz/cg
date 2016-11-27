/**
 * Created by valentin on 28.11.16.
 */
public class Pixel {
        Integer x;
        Integer y;
        Integer color;
        Pixel(Integer first, Integer second, Integer c){
            x = first;
            y = second;
            color = c;
        }
        public Integer getColor() {
            return color;
        }
        public Integer getX(){
            return x;
        }
        public Integer getY(){
            return y;
        }
        public void setColor(Integer c){
            color = c;
        }

        @Override
        public String toString(){
            return "Color: " + color.toString() + " X: " + x.toString() + " Y: " + y.toString();
        }
}
