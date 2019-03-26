package synchedapps.tmbazar.online_market.models;

/**
 * Created by User on 22.11.2018.
 */

public class shops {
    String id,kategoriya,name,nomer,content,skidka,Image;

    public shops(String id,String kategoriya, String name, String nomer, String content, String skidka,String image) {
        this.kategoriya = kategoriya;
        this.name = name;
        this.nomer = nomer;
        this.content = content;
        this.skidka = skidka;
        this.Image=image;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return Image;
    }


    public String getKategoriya() {
        return kategoriya;
    }

    public String getName() {
        return name;
    }

    public String getNomer() {
        return nomer;
    }

    public String getContent() {
        return content;
    }

    public String getSkidka() {
        return skidka;
    }
}
