package synchedapps.tmbazar.Models;

public class model_lenta {
    public String content,title,date,like,dislike,watch;
    public   int ImageId1;


    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public void setImageId1(int imageId1) {
        ImageId1 = imageId1;
    }

    public String getContent() {

        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLike() {
        return like;
    }

    public String getDislike() {
        return dislike;
    }

    public String getWatch() {
        return watch;
    }

    public int getImageId1() {
        return ImageId1;
    }

    public model_lenta(int ImageID1, String content, String title, String date, String like, String dislike, String watch) {
        this.content = content;
        this.title = title;
        this.date = date;
        this.like = like;
        this.dislike = dislike;
        this.watch = watch;
        this.ImageId1=ImageID1;

    }

}