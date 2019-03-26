package synchedapps.tmbazar.online_market.models;

/**
 * Created by User on 20.11.2018.
 */

public class model_premarkets {
    String name;
    int imageResource;

    public model_premarkets(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
