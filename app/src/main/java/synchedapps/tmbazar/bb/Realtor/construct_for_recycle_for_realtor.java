package synchedapps.tmbazar.bb.Realtor;

public class construct_for_recycle_for_realtor {
    String place,title,date,price,content;
    int image,index,show_property;

    public int getImage() {
        return image;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getShow_property() {
        return show_property;
    }

    public void setShow_property(int show_property) {
        this.show_property = show_property;
    }

    public construct_for_recycle_for_realtor(int ImageID1, String title, String content, String price, String place, String date, int show_property, int index) {
        this.place = place;
        this.title = title;
        this.date = date;
        this.price=price;
        this.image =ImageID1;
        this.content=content;
        this.index=index;
        this.show_property=show_property;

    }

}