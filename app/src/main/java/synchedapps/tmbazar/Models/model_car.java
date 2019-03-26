package synchedapps.tmbazar.Models;

public class model_car {

   public String place,title,date,price,Wagt;
    public   int image,kridit,obmen;

    public String getWagt() {
        return Wagt;
    }

    public void setWagt(String wagt) {
        Wagt = wagt;
    }

    public int getImage() {
        return image;
    }

    public int getKridit() {
        return kridit;
    }

    public int getObmen() {
        return obmen;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setKridit(int kridit) {
        this.kridit = kridit;
    }

    public void setObmen(int obmen) {
        this.obmen = obmen;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public model_car(int ImageID1, int kridit, int obmen, String title, String place, String date, String price) {
        this.place = place;
        this.title = title;
        this.date = date;
        this.price=price;
        this.image =ImageID1;
        this.kridit = kridit;
        this.obmen = obmen;

    }

}