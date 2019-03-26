package synchedapps.tmbazar.online_market.models;

/**
 * Created by User on 29.11.2018.
 */

public class model_items {
    String itemID,name,image,image1,image2,image3,content,skidka,price,number;

    public String getImage3() {
        return image3;
    }

    public String getSkidka() {
        return skidka;
    }

    public String getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getContent() {
        return content;
    }

    public String getPrice() {
        return price;
    }

    public String getNumber() {
        return number;
    }

    public model_items(String itemID, String name, String image, String image1, String image2,String image3, String content, String skidka, String price, String number) {
        this.itemID = itemID;
        this.skidka=skidka;
        this.name = name;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3=image3;
        this.content = content;
        this.price=price;
        this.number=number;
    }
}
