package synchedapps.tmbazar.online_market.models;

/**
 * Created by User on 22.11.2018.
 */

public class bazarlar {
    String id;
    String name;
    String numberSh;
    String urlImage;
    String region;
    public bazarlar(String id,String name, String numberSh, String urlImage,String region) {
        this.id=id;
        this.name = name;
        this.numberSh = numberSh;
        this.urlImage = urlImage;
        this.region=region;
    }

    public String getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public String getNumberSh() {
        return numberSh;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
