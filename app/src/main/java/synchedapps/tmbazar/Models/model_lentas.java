package synchedapps.tmbazar.Models;

import android.graphics.Bitmap;

/**
 * Created by User on 23.06.2018.
 */

public class model_lentas {
    public String content,title,date,like,dislike,watch,image,id,table,pid,owntable;
    Bitmap imgbitmap,img1;
    String watched,liked,loved;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOwntable() {
        return owntable;
    }

    public void setOwntable(String owntable) {
        this.owntable = owntable;
    }

    public String getLoved() {
        return loved;
    }

    public void setLoved(String loved) {
        this.loved = loved;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getImgbitmap() {
        return imgbitmap;
    }

    public void setImgbitmap(Bitmap imgbitmap) {
        this.imgbitmap = imgbitmap;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getWatch() {
        return watch;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
