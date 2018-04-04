package cosw.eci.edu.Lab7_Salinas;

import java.io.Serializable;

/**
 * Created by Jhordy.
 */

public class Post implements Serializable {

    String text;
    String imagePath;

    public Post(){}

    public Post(String text,String imagePath){
        this.text=text;
        this.imagePath=imagePath;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
