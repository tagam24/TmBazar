package synchedapps.tmbazar.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import synchedapps.tmbazar.Models.ImagesClicker;
import synchedapps.tmbazar.Models.MOdel_lenta_banner;
import synchedapps.tmbazar.Models.ModelRealtor;
import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.Models.model_lentas;
import synchedapps.tmbazar.Models.model_vip;
import synchedapps.tmbazar.Models.model_vip_check;
import synchedapps.tmbazar.Network.network;
import synchedapps.tmbazar.R;
import synchedapps.tmbazar.bb.Beylekiler.Bildiris_gos_Navigation.fragment_bildiris_gosmak2;
import synchedapps.tmbazar.bb.vip_show_details;
import synchedapps.tmbazar.chat.ChatAppMsgDTO;
import synchedapps.tmbazar.online_market.models.model_items;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Created by User on 21.06.2018.
 */

public class Db extends SQLiteOpenHelper {
    public static String db_name = "reklam.db";
    public static int db_ver = 7;
   Context x;
    public Db(Context context) {

        super(context, db_name, null, db_ver);
        this.x=context;
    }
  
    void createLiked(SQLiteDatabase e){
        e.execSQL("create table online_liked(id integer,name varchar(100),image varchar(100),image1 varchar(100),image2 varchar(100)," +
                "image3 varchar(100),content text,skidka varchar(100),price varchar(100),number varchar(100))");
    }
    void table_maxes(SQLiteDatabase s){
        s.execSQL("create table cars_maxs (cars integer)");
        s.execSQL("create table home_maxs (home integer)");
        s.execSQL("create table beylekiler_maxs (beylekiler integer)");
        s.execSQL("create table futbol_maxs (futbol integer)");
        s.execSQL("create table tasinlikler_maxs (tasinlikler integer)");
        s.execSQL("create table gymmatly_maxs (gymmatly integer)");
    }
    void createNotific(SQLiteDatabase s) {
        s.execSQL("create table dil(dili varhar(3))");
        s.execSQL("create table notif(cars integer,home integer,beylekiler integer,futbol integer,tasinlikler integer,gymmatly integer)");
    }

    void createChatDb(SQLiteDatabase s) {
        s.execSQL("create table myID(id integer)");
        s.execSQL("create table chat(pid integer primary key autoincrement,id integer,msgtype integer, content text, date varchar(20))");
    }

    void createRealtor(SQLiteDatabase s) {

        s.execSQL("create table home(pid integer primary key,id integer,category varchar(50),name varchar(50),price biginteger,description text,location varchar(50),\n" +
                "number varchar(50),vip integer, image text, image1 text,image2 text,image3 text," +
                "watch integer, watched integer,credit integer,obmen integer,time varchar(6),date varchar(15),category1 varchar(50),location1 varchar(50),loved integer)");
        s.execSQL("create table myAddsrealtor(id integer,category varchar(50),name varchar(50),price varchar(50),description text,location varchar(50),\n" +
                "number varchar(50),status integer, image text, image1 text,image2 text,image3 text,key1 varchar(5)," +
                "credit integer, obmen integer,time varchar(6),date varchar(15),vip integer,category1 varchar(50),location1 varchar(50))");
        s.execSQL("create table likedhome(pid integer primary key,id integer,category varchar(50),name varchar(50),price varchar(50),description text,location varchar(50),\n" +
                "number varchar(50),vip integer, image text, image1 text,image2 text,image3 text," +
                "watch integer, watched integer,credit integer,obmen integer,time varchar(6),date varchar(15),category1 varchar(50),location1 varchar(50))");

    }

    void createBanner(SQLiteDatabase s) {
        s.execSQL("create table bannerlenta(id integer, name varchar(50),description text,number varchar(20),image text, image1 text, image2 text, image3 text,endDate varchar(20))");

    }

    void createBeylekiler(SQLiteDatabase s) {
        s.execSQL("create table beylekiler(pid integer primary key,id integer,category varchar(50),name varchar(50),price biginteger,description text,location varchar(50),\n" +
                "number varchar(50),vip integer, image text, image1 text,image2 text,image3 text," +
                "watch integer, watched integer,credit integer,obmen integer,time varchar(6),date varchar(15),category1 varchar(50),location1 varchar(50),loved integer)");
        s.execSQL("create table myAddsbeylekiler(id integer,category varchar(50),name varchar(50),price varchar(50),description text,location varchar(50),\n" +
                "number varchar(50),status integer, image text, image1 text,image2 text,image3 text,key1 varchar(5)," +
                "credit integer, obmen integer,time varchar(6),date varchar(15),vip integer,category1 varchar(50),location1 varchar(50))");
        s.execSQL("create table likedbeylekiler(pid integer primary key,id integer,category varchar(50),name varchar(50),price varchar(50),description text,location varchar(50),\n" +
                "number varchar(50),vip integer, image text, image1 text,image2 text,image3 text," +
                "watch integer, watched integer,credit integer,obmen integer,time varchar(6),date varchar(15),category1 varchar(50),location1 varchar(50))");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        table_maxes(sqLiteDatabase);
        createNotific(sqLiteDatabase);
        createRealtor(sqLiteDatabase);
        createBeylekiler(sqLiteDatabase);
        createBanner(sqLiteDatabase);
        createChatDb(sqLiteDatabase);
        createLiked(sqLiteDatabase);
        sqLiteDatabase.execSQL("create table myadds(id integer,category varchar(50)," +
                "model varchar(50),image text,image1 text," +
                "image2 text,price varchar(50),description text,location varchar(50),vip integer," +
                "time varchar(30),number varchar(30)," +
                "watched varchar(3),date varchar(30),year varchar(3),credit varchar(3), " +
                "obmen varchar(3),satyldy varchar(3)," +
                "image3 text,probeg integer,kuzow varchar(100),watch integer," +
                "color varchar(50),karopka varchar(50),mator varchar(5),status varchar(3)," +
                "tabl varchar(50),key1 varchar(10))");

        sqLiteDatabase.execSQL("create table likedAdds(" +
                "id integer,category varchar(50),model varchar(50)," +
                "image text,image1 text,image2 text," +
                "price varchar(50),description text,location varchar(50)," +
                "vip integer,time varchar(30)," + "number varchar(30)," +
                "watched varchar(3),date varchar(30),year varchar(3)," +
                "credit varchar(3), obmen varchar(3),satyldy varchar(3)," +
                "image3 text," + "probeg integer,kuzow varchar(100),watch integer," +
                "color varchar(50),karopka varchar(50),mator varchar(5),tabl varchar(30))");
        sqLiteDatabase.execSQL("create table cars(id integer,category varchar(50),name varchar(50),image text,image1 text," +
                "image2 text,price biginteger,description text,location varchar(50),vip integer,time varchar(30),number varchar(30)," +
                "watched varchar(3),date varchar(30),year integer,credit varchar(3), obmen varchar(3),satyldy varchar(3)," +
                "image3 text,probeg integer,kuzow varchar(100),watch integer," +
                "color varchar(50),karopka varchar(50),mator varchar(5),pid integer primary key autoincrement,vipEndDate varchar(5),loved integer)");

        sqLiteDatabase.execSQL("create table gymmatly(id integer,name varchar(100),image text,content " +
                "text, date varchar(50),liked integer,disliked integer, watched integer,like integer,watch integer,loved integer)");
        sqLiteDatabase.execSQL("create table futbol(id integer,name varchar(100),image text,content " +
                "text, date varchar(50),liked integer, disliked integer,watched integer,like integer,watch integer,loved integer)");
        sqLiteDatabase.execSQL("create table tasinlikler(id integer,name varchar(100),image text,content " +
                "text, date varchar(50),liked integer,disliked integer, watched integer,like integer,watch integer,loved integer)");
        sqLiteDatabase.execSQL("create table checked(date text)");
        sqLiteDatabase.execSQL("create table vip_image(id integer,image blob,name varchar(50),line integer,image1 blob,image2 blob,shop_id varchar(10),shop_name varchar(100))");
        sqLiteDatabase.execSQL("create table vip(id integer, name varchar(50),description text,date varchar(30),line integer,updat varchar(3),watched integer,watch integer,number varchar(50),time varchar(10),link varchar(100))");
        sqLiteDatabase.execSQL("create table  liked_gyzykly(pid integer primary key autoincrement,name varchar(100),image text,content " +
                "text, date varchar(50),liked integer,disliked integer, watched integer,like integer,watch integer,loved integer,id integer,table1 varchar(50))");

        sqLiteDatabase.execSQL("create index id_db on cars(id,category,name,location,year,credit,obmen,color,price)");
        sqLiteDatabase.execSQL("create index id_d on home(id,category,name,location,price)");
        //sqLiteDatabase.execSQL("create index id_b on cars(id,category,name,location)");
        sqLiteDatabase.execSQL("create index id_bey on beylekiler(id,category,category1,name,location,location1,price)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("onupgrade","dede");
        sqLiteDatabase.execSQL("Drop table if Exists " + " checked");
        sqLiteDatabase.execSQL("Drop table if Exists " + "vip");
        sqLiteDatabase.execSQL("Drop table if Exists " + "vip_image");
        sqLiteDatabase.execSQL("Drop table if Exists " + "gymmatly");
        sqLiteDatabase.execSQL("Drop table if Exists " + "futbol");
        sqLiteDatabase.execSQL("Drop table if Exists " + "tasinlikler");
        sqLiteDatabase.execSQL("Drop table if Exists " + "liked_gyzykly");
        sqLiteDatabase.execSQL("Drop table if Exists " + "cars");
        sqLiteDatabase.execSQL("Drop table if exists " + " likedAdds");
        sqLiteDatabase.execSQL("Drop table if exists " + " myadds");
        sqLiteDatabase.execSQL("Drop table if exists " + " home");
        sqLiteDatabase.execSQL("Drop table if exists " + " myAddsrealtor");
        sqLiteDatabase.execSQL("Drop table if exists " + " beylekiler");
        sqLiteDatabase.execSQL("Drop table if exists " + " myAddsbeylekiler");
        sqLiteDatabase.execSQL("Drop table if exists " + " likedbeylekiler");
        sqLiteDatabase.execSQL("Drop table if exists " + "  likedhome");
        sqLiteDatabase.execSQL("Drop table if exists " + "  bannerlenta");
        sqLiteDatabase.execSQL("Drop table if exists " + "  chat");
        sqLiteDatabase.execSQL("Drop table if exists " + "  myID");
        sqLiteDatabase.execSQL("Drop table if exists " + "  notif");
        sqLiteDatabase.execSQL("Drop table if exists " + "  dil");
        sqLiteDatabase.execSQL("Drop table if exists " + "  cars_maxs");
        sqLiteDatabase.execSQL("Drop table if exists " + "  home_maxs");
        sqLiteDatabase.execSQL("Drop table if exists " + "  beylekiler_maxs");
        sqLiteDatabase.execSQL("Drop table if exists " + "  futbol_maxs");
        sqLiteDatabase.execSQL("Drop table if exists " + "  gymmatly_maxs");
        sqLiteDatabase.execSQL("Drop table if exists " + "  tasinlikler_maxs");
        sqLiteDatabase.execSQL("Drop table if exists " + "  online_liked");
        onCreate(sqLiteDatabase);


    }

    //dil
    public String get_dil() {
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            String dil = "";
            Cursor c = db.query("dil", null, null, null, null, null, null, null);
            if (c.moveToNext()) dil = c.getString(c.getColumnIndex("dili"));
            c.close();
            return dil;
        } catch (NullPointerException ss) {
        }

        return "";
    }

    public void inser_dil(String dil) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("dil", null, null);
        ContentValues c = new ContentValues();
        c.put("dili", dil);
        db.insert("dil", null, c);
    }

    public void insert_tm() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("dili", "tm");
        db.insert("dil", null, cv);
    }

    //vip
    public String get_update(ArrayList<model_vip_check> in) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("vip", null, null, null, null, null, null);
        ArrayList<model_vip_check> data = new ArrayList<>();
        ArrayList<String> idlar = new ArrayList<>();
        model_vip_check m;
        Log.d("in.size", "" + in.size());
        for (int i = 0; i < in.size(); i++) {
            idlar.add(in.get(i).getId());
        }
        while (cursor.moveToNext()) {
            m = new model_vip_check();
            m.setId(cursor.getString(0));
            m.setUpd(cursor.getString(5));
            data.add(m);
            Log.d("id", cursor.getString(0));
            if (!idlar.contains(cursor.getString(0))) {
                Log.d("delete", cursor.getString(0));
                db.delete("vip", "id=?", new String[]{cursor.getString(0)});
                db.delete("vip_image", "id=?", new String[]{cursor.getString(0)});
            }
        }
        String upd = "";
        Log.d("in.size", "" + in.size());
        for (int i = 0; i < in.size(); i++) {
            boolean p = false;
            for (int j = 0; j < data.size(); j++) {
                if (in.get(i).getId().equals(data.get(j).getId())) {
                    p = true;
                    if (!in.get(i).getUpd().equals(data.get(j).getUpd())) {
                        db.delete("vip", "id=?", new String[]{in.get(i).getId()});
                        db.delete("vip_image", "id=?", new String[]{in.get(i).getId()});
                        upd += in.get(i).getId() + " ";
                    }
                    data.remove(j);
                    break;
                }
            }
            if (p == false) {
                upd += in.get(i).getId() + " ";
                Log.d("uddp", upd);
                db.delete("vip", "id=?", new String[]{in.get(i).getId()});
                db.delete("vip_image", "id=?", new String[]{in.get(i).getId()});
            }
        }

        Log.d("upddb", upd);
        return upd;
    }//checks from the server if it is changed

    public void insert_Vip(String id, String name, String description, String date, String line, String upd, int watched, String number, String time,String link) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into vip values(?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, name);
            statement.bindString(3, description);
            statement.bindString(4, date);
            statement.bindString(5, line);
            statement.bindString(6, upd);
            statement.bindLong(7, watched);
            statement.bindLong(8, 0);
            statement.bindString(9, number);
            statement.bindString(10, time);
            statement.bindString(11,link);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    } //inserts vip data

    public void insert_Vip_image(String id, String image, String image1, String image2, String name, String line,String shopId,String shopName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into vip_image values(?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {

            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, image);
            statement.bindString(3, name);
            statement.bindString(4, line);
            statement.bindString(5, image1);
            statement.bindString(6, image2);
            statement.bindString(7, shopId);
            statement.bindString(8, shopName);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }//inserts vip image

    public ArrayList<model_vip> get_data_vip() {
        ArrayList<model_vip> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            Cursor cursor = db.query("vip_image", null, null, null, null, null, null);
            model_vip m;
            while (cursor.moveToNext()) {
                m = new model_vip();
                m.setId(cursor.getString(0));
                m.setName(cursor.getString(2));
                m.setImage(cursor.getString(1));
                m.setLine(cursor.getString(3));
                m.setShopID(cursor.getString(6));
                m.setShopname(cursor.getString(7));
                data.add(m);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return data;
    }  //get_vip

    public void set_cheked(String date) {
        del_check();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into checked values(?)";

        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {

            statement.clearBindings();
            statement.bindString(1, date);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    } //every day sets checked

    public ArrayList<ImagesClicker> get_images_vip(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ImagesClicker> images = new ArrayList<>();
        ImagesClicker i;
        Cursor cursor = db.rawQuery("select *from vip_image where id=?", new String[]{id});
        while (cursor.moveToNext()) {
            try {
            if(!cursor.getString(1).equals("")) {   byte[] img = Base64.decode(cursor.getString(1), Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                i = new ImagesClicker();
                i.setImg1(image);
                i.setId(cursor.getString(0));
                images.add(i);}
            } catch (NullPointerException ss) {
            }
            try {
               if(!cursor.getString(4).equals("")){ byte[] img = Base64.decode(cursor.getString(4), Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                i = new ImagesClicker();
                i.setImg1(image);
                i.setId(cursor.getString(0));
                images.add(i);}
            } catch (NullPointerException ss) {
            }
            try {
                if(!cursor.getString(5).equals("")){
                byte[] img = Base64.decode(cursor.getString(5), Base64.DEFAULT);
                Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                i = new ImagesClicker();
                i.setImg1(image);
                i.setId(cursor.getString(0));
                images.add(i);}
            } catch (NullPointerException ss) {
            }
        }


        return images;
    } //gets_images in show details

    public model_vip get_detail_vip(String id) {
        model_vip m = new model_vip();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select *from vip where id=?", new String[]{id});
        if (c.moveToNext()) {
            m.setDate(c.getString(3));
            m.setName(c.getString(c.getColumnIndex("name")));
            m.setDescription(c.getString(2));
            m.setWatched(c.getString(6));
            m.setWatch(c.getString(7));
            m.setNumber(c.getString(8));
            m.setTime(c.getString(9));
            m.setLink(c.getString(10));
        }
        return m;
    }

    public model_vip get_detail_adds(String table, String id) {
        model_vip m = new model_vip();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select *from " + table + " where id=?", new String[]{id});
        if (c.moveToNext()) {
            m.setDate(c.getString(c.getColumnIndex("date")));
            m.setName(c.getString(c.getColumnIndex("name")));
            m.setDescription(c.getString(c.getColumnIndex("description")));
            m.setWatched(c.getString(c.getColumnIndex("watched")));
            m.setWatch(c.getString(c.getColumnIndex("watch")));
            m.setNumber(c.getString(c.getColumnIndex("number")));
            m.setTime(c.getString(c.getColumnIndex("time")));
        }
        return m;
    }

    public void del_check() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("checked", null, null);

    }

    public boolean check_isemptyvip() {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean b = false;
        Cursor cursor = db.query("vip", null, null, null, null, null, null, null);
        if (cursor.getCount() > 0) b = true;

        return b;
    }

    public String get_date() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("checked", null, null, null, null, null, null);
        String date = "";
        if (cursor.moveToNext()) date = cursor.getString(0);
        return date;
    }


    String column;

    public void lwatched(String table, String columnname, String id, int like) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (columnname == "liked" || columnname == "disliked") column = "like";
        else column = "watch";
        Log.d("wached", column + " " + like);
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(column, 1);
            cv.put(columnname, like);
            db.update(table, cv, "ID=?", new String[]{id});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


    }

    //gyzykly get-insert
    public void insert_gyzykly(String table, String id, String name, String image, String content, String date, int liked, int disliked, int watched) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into " + table + " values(?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {

            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, name);
            statement.bindString(3, image);
            statement.bindString(4, content);
            statement.bindString(5, date);
            statement.bindLong(6, liked);
            statement.bindLong(7, disliked);
            statement.bindLong(8, watched);
            statement.bindLong(9, 0);
            statement.bindLong(10, 0);
            statement.bindString(11, "0");
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }//insert gyzykly

    public ArrayList<model_lentas> get_gyzykly(String table, int limit) {
        ArrayList<model_lentas> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            Cursor cursor = db.query(table, null, null, null, null, null,null,""+limit);
            model_lentas m;
            while (cursor.moveToNext()) {
                m = new model_lentas();
                m.setTable(table);
                m.setId(cursor.getString(0));
                m.setTitle(cursor.getString(1));
                m.setImage(cursor.getString(2));
                m.setContent(cursor.getString(3));
                m.setDate(cursor.getString(4));
                m.setLike(cursor.getString(5));
                m.setDislike(cursor.getString(6));
                m.setWatch(cursor.getString(7));
                m.setLiked(cursor.getString(8));
                m.setWatched(cursor.getString(9));
                m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
                data.add(m);
            }
            if (!cursor.isClosed()) cursor.close();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return data;
    } //get gyzykly

    public void update_gyzykly(String table, String id, String image) {
        Log.d("update gyzykly", table + id + "  " + image);
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("image", image);
            db.update(table, cv, "id=?", new String[]{id});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


    }

    public ArrayList<model_lentas> get_Likedgyzykly() {
        ArrayList<model_lentas> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            Cursor cursor = db.query("liked_gyzykly", null, null, null, null, null, null);
            model_lentas m;
            while (cursor.moveToNext()) {
                m = new model_lentas();
                m.setTable("liked_gyzykly");
                m.setPid(cursor.getString(cursor.getColumnIndex("pid")));
                m.setId(cursor.getString(cursor.getColumnIndex("id")));
                m.setTitle(cursor.getString(1));
                m.setImage(cursor.getString(2));
                m.setContent(cursor.getString(3));
                m.setDate(cursor.getString(4));
                m.setLike(cursor.getString(5));
                m.setDislike(cursor.getString(6));
                m.setWatch(cursor.getString(7));
                m.setLiked(cursor.getString(8));
                m.setWatched(cursor.getString(9));
                m.setOwntable(cursor.getString(cursor.getColumnIndex("table1")));
                m.setLoved("1");
                data.add(m);
            }
            if (!cursor.isClosed()) cursor.close();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return data;
    }//get_likedGyzykly

    public void insert_Likegyzykly(String id, String name, String image, String content, String date, String liked, String disliked, String watched, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("name ", id + name + content);
        String sql = "insert into liked_gyzykly values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        Log.d("likedda", id + name);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(2, name);
            statement.bindString(3, image);
            statement.bindString(4, content);
            statement.bindString(5, date);
            statement.bindString(6, liked);
            statement.bindString(7, disliked);
            statement.bindString(8, watched);
            statement.bindLong(9, 1);
            statement.bindLong(10, 1);
            statement.bindString(11, "1");
            statement.bindString(12, id);
            statement.bindString(13, table);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    //insert_likegyzykly
    public model_lentas get_detail(String table, String id) {
        Cursor cursor = null;
        model_lentas m = new model_lentas();
        SQLiteDatabase db = this.getWritableDatabase();
        if (table.equals("liked_gyzykly"))
            cursor = db.rawQuery("Select *from " + table + " where pid=?", new String[]{id});
        else
            cursor = db.rawQuery("Select *from " + table + " where id=?", new String[]{id});
        if (cursor.moveToNext()) {
         //   byte[] img = Base64.decode(cursor.getString(2), Base64.DEFAULT);
      //      Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
            // img = Base64.decode(cursor.getString(2), Base64.DEFAULT);
            //Bitmap image1 = BitmapFactory.decodeByteArray(img, 0, img.length);
            m.setImage(cursor.getString(2));
            m.setTitle(cursor.getString(1));
         //   m.setImgbitmap(image);
            m.setImage(cursor.getString(2));
            Log.d("Image",cursor.getString(2));
            m.setContent(cursor.getString(3));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
            m.setLike(cursor.getString(cursor.getColumnIndex("liked")));
            m.setDislike(cursor.getString(cursor.getColumnIndex("disliked")));
            m.setWatch(cursor.getString(7));
            m.setLiked(cursor.getString(8));
            m.setWatched(cursor.getString(9));
            m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
        }

        return m;
    }//get_detail_lenta

    public void update_liked(String table, String id, String liked, String disliked, String watched) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("liked", liked);
        cv.put("disliked", disliked);
        cv.put("watched", watched);
        db.update(table, cv, "id=?", new String[]{id});
    }//like,dislike,watch update

    public String get_max(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String last = "";
        db.beginTransaction();
        try {
            Cursor cursor = db.query(table, null, null, null, null, null, "id desc");

            if (cursor.moveToLast()) last = cursor.getString(0);
            Log.d("last", last);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return last;
    }  //before send to the server
    public String get_max1(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String last = "";
        db.beginTransaction();
        try {
            Cursor cursor = db.query(table, null, null, null, null, null, "id ASC");

            if (cursor.moveToLast()) last = cursor.getString(0);
            Log.d("last", last);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return last;
    }
    public String get_min(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String first = "";
        db.beginTransaction();
        try {
            Cursor cursor = db.query(table, null, null, null, null, null, null);

            if (cursor.moveToNext()) first = cursor.getString(0);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return first;
    }  //before send to the server

    //detail image
    public void update_adds_detail_image(String table, String id, String image1, String image2, String image3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image1", image1);
        cv.put("image2", image2);
        cv.put("image3", image3);
        db.update(table, cv, "id=?", new String[]{id});
    }

    public ArrayList<ImagesClicker> get_adds_detail_image(String table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ImagesClicker> images = new ArrayList<>();
        Log.d("imagesVip",table+id);
        ImagesClicker i = new ImagesClicker();
        Cursor cursor = db.rawQuery("select * from " + table + " where ID=?", new String[]{id});
        while (cursor.moveToNext()) {
            Log.d("imageDetail", "  " + cursor.getString(cursor.getColumnIndex("image1")));
            if (!cursor.getString(cursor.getColumnIndex("image1")).equals("")) {

                i = new ImagesClicker();
                i.setId(cursor.getString(0));
                i.setImgG(cursor.getString(cursor.getColumnIndex("image1")));
                if(vip_show_details.flazhok==1&& cursor.getString(cursor.getColumnIndex("image1")).length()>100){  byte[] img = Base64.decode(cursor.getString(cursor.getColumnIndex("image1")), Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                    i.setImg1(image);}
                images.add(i);
            }
            if (!cursor.getString(cursor.getColumnIndex("image2")).equals("")) {
                i = new ImagesClicker();
                i.setImgG(cursor.getString(cursor.getColumnIndex("image2")));
                i.setId(cursor.getString(0));
                if(vip_show_details.flazhok==1&&cursor.getString(cursor.getColumnIndex("image2")).length()>100){  byte[] img = Base64.decode(cursor.getString(cursor.getColumnIndex("image2")), Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                    i.setImg1(image);}
                images.add(i);
            }
            if (!cursor.getString(cursor.getColumnIndex("image3")).equals("")) {
                i = new ImagesClicker();
                i.setId(cursor.getString(0));
                i.setImgG(cursor.getString(cursor.getColumnIndex("image3")));
                if(vip_show_details.flazhok==1&&cursor.getString(cursor.getColumnIndex("image3")).length()>100){  byte[] img = Base64.decode(cursor.getString(cursor.getColumnIndex("image3")), Base64.DEFAULT);
                    Bitmap image = BitmapFactory.decodeByteArray(img, 0, img.length);
                    i.setImg1(image);}
                images.add(i);
            }
        }
        return images;
    }

    public void update_liked(String table, String id, String watched) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("watched", watched);
        db.update(table, cv, "id=?", new String[]{id});
    }

    //Adds get insert
    public HashMap<String, String> get_abs(String Table_name, String category, String model) {
        Log.d("tableabs", Table_name);
        String catc = category;
        ArrayList<Map> data = new ArrayList<>();
        String idlar = "";
        String maxVip = "";
        int max = 0;
try{        SQLiteDatabase db = this.getWritableDatabase();
        if (category.equals("beylekiler")) {
            category = "BMW,TOYOTA,LEXUS,MERCEDES,HYUNDAI,NISSAN,OPEL,LADA,WOLKSWAGEN,INFINITY,KIA,FORD,MITSUBISHI,UAZ,GAZ,KAMAZ,ZIL,MAZ";
        }
        ArrayList<String> cat = new ArrayList<>();
        if (category.equals("")) {
        } else {
            for (String s : category.split(",")) {
                cat.add(s);
            }
        }

        ArrayList<String> mod = new ArrayList<>();

        if (model.equals("")) {
        } else {
            for (String s : model.split(",")) {
                mod.add(s);
            }
        }
        Cursor cursor = db.query(Table_name, null, null, null, null, null, null);
        Log.d("cursor.size", "" + cursor.getCount());
        while (cursor.moveToNext()) {
            if (category.equals("")) {
                Log.d("girya1", " girya");
                idlar = idlar + cursor.getString(cursor.getColumnIndex("id")) + ",";
                if (cursor.getInt(cursor.getColumnIndex("id")) > max)
                    max = cursor.getInt(cursor.getColumnIndex("id"));
            }
            if ((cursor.getString(cursor.getColumnIndex("vip")).equals("2") || cursor.getString(cursor.getColumnIndex("vip")).equals("1")) || cursor.getString(cursor.getColumnIndex("vip")).equals("3") ||
                    cursor.getString(cursor.getColumnIndex("vip")).equals("4")) {
                maxVip = maxVip + cursor.getString(cursor.getColumnIndex("id")) + ",";
                Log.d("girya2", " girya");
                Log.d("maxVip1", maxVip);
            }
            if (cursor.getInt(cursor.getColumnIndex("id")) > max)
                max = cursor.getInt(cursor.getColumnIndex("id"));
            if ((cat.contains(cursor.getString(cursor.getColumnIndex("category"))) && !catc.equals("beylekiler")) ||
                    (!cat.contains(cursor.getString(cursor.getColumnIndex("category"))) && catc.equals("beylekiler")) && mod.contains(cursor.getString(cursor.getColumnIndex("name")))) {
                Log.d("girya4", " girya");
                idlar = idlar + cursor.getString(cursor.getColumnIndex("id"));
                idlar = idlar + ",";
            } else if ((cat.contains(cursor.getString(cursor.getColumnIndex("category"))) && !catc.equals("beylekiler")) ||
                    (!cat.contains(cursor.getString(cursor.getColumnIndex("category"))) && catc.equals("beylekiler"))) {
                Log.d("girya5", " girya");
                idlar = idlar + cursor.getString(cursor.getColumnIndex("id"));
                idlar = idlar + ",";
                if (cursor.getInt(cursor.getColumnIndex("id")) > max)
                    max = cursor.getInt(cursor.getColumnIndex("id"));
            }
        }} catch(NullPointerException ss){}
//        Log.d("idlardb",idlar.substring(0,idlar.length()-1));
        HashMap<String, String> object = new HashMap<>();
        if (!idlar.equals("")) object.put("idlar", idlar.substring(0, idlar.length() - 1));
        else object.put("idlar", "");
        String mvip = "" + maxVip;
        if (!mvip.equals("")) object.put("maxVip", mvip.substring(0, mvip.length() - 1));
        else object.put("maxVip", "");
        mvip = "" + max;
        object.put("maxTable", mvip);
        Log.d("datasizeobject", "" + data.size());
        return object;

    }

    public ArrayList<model_cars> get_adds(String tabl, String categor, String mode,
                                          String year11, String year22, String price11, String price22,
                                          String credi, String obme, String locatio, String Vi, String gyssagl, String satyldy, String colo, int limit,String downup) {
        Log.d("year1", year11 + year22);


        Log.d("category", categor);
        ArrayList<model_cars> banner = new ArrayList<>();
        ArrayList<model_cars> data = new ArrayList<>();
        ArrayList<model_cars> vip = new ArrayList<>();
        ArrayList<model_cars> vip1 = new ArrayList<>();
        ArrayList<model_cars> Gyssagly = new ArrayList<>();
        ArrayList<model_cars> satylan = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String table;
        String category;
        String model;
        String year1;
        String year2;
        String price1;
        String price2;
        String credit;
        String obmen;
        String location;
        String Vip;
        String gyssagly;
        String color;
        String c1, m1, y1, y2, p1, p2, c, o, l, kola;
        table = tabl;
        category = categor;
        if (category.equals("beylekiler")) {
            category = "BMW,Toyota,Lexus,Mercedec-Benz,Hyundai,Nissan,Opel,Lada,Wolkswagen,Infiniti,Kia,Ford,Mitsubishi,UAZ,Gaz,Kamaz,Zil,Maz";
        }

        model = mode;
        year1 = year11;
        year2 = year22;
        price1 = price11;
        price2 = price22;
        credit = credi;
        obmen = obme;
        location = locatio;
        Vip = Vi;
        gyssagly = gyssagl;
        color = colo;
        String wagt="",wagt1="",day="",day1="";
        Log.d("gelyanlerdb", category + " " + model + " " + year1 + " " + year2 + " " + price1 + " " + price2 + " " + credit + " " + obmen + " " + location + " " + color);
        ArrayList<String> kat = new ArrayList<>();
        if (!category.equals("")) {
            for (String s : category.split(",")) {
                kat.add(s);
            }
        }
        Log.d("kat.size", "" + kat.size());
        ArrayList<String> mod = new ArrayList<>();
        if (!model.equals("")) {
            for (String s : model.split(",")) {
                mod.add(s);
            }
        }
        ArrayList<String> loc = new ArrayList<>();
        if (!location.equals("")) {
            for (String s : location.split(",")) {
                loc.add(s);
            }
        }
        ArrayList<String> col = new ArrayList<>();
        if (!color.equals("")) {
            for (String s : color.split(",")) {
                col.add(s);
            }
        }
        int pr1;
        if (!price1.equals("")) {
            pr1 = Integer.parseInt(price1);
        } else {
            pr1 = 0;
        }
        int pr2;

        if (!price2.equals("")) pr2 = Integer.parseInt(price2);
        else pr2 = 1000000000;
        int yr1;
        if(year1.equals("BAŞY"))yr1=0;else
        if (!year1.equals("")) yr1 = Integer.parseInt(year1);
        else yr1 = 0;
        int yr2;
        if(year2.equals("SOŇY"))yr2=10000;else
        if (!year2.equals("")) yr2 = Integer.parseInt(year2);
        else yr2 = 1000000;
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("dd.MM.yyyy");
        String date = ff.format(d);

        if (get_dil().equals("tm")) {
            wagt="Şu wagt";
           day="Şu gün";
        } else {
           wagt="Сейчас";
          day="Сегодня";}
 Cursor cursor = db.rawQuery("select *from " + table + " where vip>?  ORDER BY vip DESC", new String[]{"0"});
        model_cars m;
        while (cursor.moveToNext()) {
            if (obmen.equals(cursor.getString(16)) || obmen.equals(""))
                Log.d("obmencheck", cursor.getString(16) + "  zapros" + obmen);

            if ((((kat.contains(cursor.getString(1)) || kat.size() == 0) && !categor.equals("beylekiler")) ||
                    (!kat.contains(cursor.getString(1)) && categor.equals("beylekiler"))) &&
                    (mod.contains(cursor.getString(2)) || mod.size() == 0) &&
                    (loc.contains(cursor.getString(8)) || loc.size() == 0) &&
                    (col.contains(cursor.getString(cursor.getColumnIndex("color"))) || col.size() == 0) &&
                    (credit.equals(cursor.getString(15)) || credit.equals("")) &&
                    (obmen.equals(cursor.getString(16)) || obmen.equals("")) &&
                    (pr1 <= cursor.getInt(6) || pr1 == 0) &&
                    (pr2 >= cursor.getInt(6) || pr2 == 0)
                    &&
                    (yr1 <= cursor.getInt(14) || yr1 == 0) && (yr2 >= cursor.getInt(14) || yr2 == 0)) {
                m = new model_cars();
                m.setPid(cursor.getString(cursor.getColumnIndex("pid")));

                m.setId(cursor.getString(0));
                m.setCategory(cursor.getString(1));
                m.setModel(cursor.getString(2));
                m.setImg(cursor.getString(3));
                m.setPrice(divider(cursor.getString(6)));
                m.setDescription(cursor.getString(7));
                m.setLocation(cursor.getString(8));
                m.setVip(cursor.getString(9));
                m.setTime(cursor.getString(10));
                m.setNumber(cursor.getString(11));
                m.setWathced(cursor.getString(12));//watched number
                m.setDate(cursor.getString(13));
                m.setYear(cursor.getString(14));
                m.setCredit(cursor.getString(15));
                m.setObmen(cursor.getString(16));
                m.setSatyldy(cursor.getString(17));

                m.setProbeg(divider(cursor.getString(19)));
                m.setKuzow(cursor.getString(20));
                m.setWatch(cursor.getString(21));//is watched
                m.setColor(cursor.getString(cursor.getColumnIndex("color")));
                m.setKaropka(cursor.getString(cursor.getColumnIndex("karopka")));
                m.setMator(cursor.getString(cursor.getColumnIndex("mator")));
                m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
                m.setPid(cursor.getString(cursor.getColumnIndex("pid")));
                if (m.getDate().equals(date)) {
                    Date t = new Date();
                    ff = new SimpleDateFormat("hh");
                    String ti = ff.format(t);
                    String ti1 = m.getTime();

                    int k = Integer.parseInt(ti) - Integer.parseInt(ti1.substring(0, 2));
                  if(k<2)m.setWagt(wagt); else m.setWagt(day);
                    }
                //m.setIdimg(R.drawable.vip);
                switch (cursor.getString(cursor.getColumnIndex("vip"))) {
                    case "1":
                        vip.add(m);
                        break;
                    case "2":
                        Gyssagly.add(m);
                        break;
                    case "3":
                        banner.add(m);
                        break;
                    case "4":
                        satylan.add(m);
                    case "5":
                        vip1.add(m);
                }
            }
        }
        String  co="";
       // if(downup.equals("0")) co=" and  pid<? "; else if(downup.equals("1"))co=" and pid>? ";
        if (Vip.equals("1")) return vip;
        if (gyssagl.equals("1")) return Gyssagly;
        if (satyldy.equals("1")) return satylan;


        Log.d("category db", category);
        //"pid DESC" udalit etdik
        cursor = db.query("cars", null, "vip=? or vip=?", new String[]{"0", "4"}, null, null," id desc", "" + limit);
        if (!category.equals("") || !location.equals("") || !price1.equals("") || !price2.equals("")
                || !year1.equals("") || !year2.equals("") || !color.equals("") || !obmen.equals("")
                || !credit.equals(""))
            cursor = db.query("cars", null, "(vip=? or vip=? ) ", new String[]{"0", "4"}, null, null, null, null);
        Log.d("cursorsize", "" + cursor.getCount());

        int i = 2;
        if (cursor.moveToFirst()) i = 0;
        while ((i != 2)) {
            if (i != 0) if (cursor.moveToNext()) i = 1;
            else i = 2;
            if (i == 0) i = 1;
            if (i != 2) {
                Log.d("katsize", "" + kat.size());

                if ((((kat.contains(cursor.getString(1)) || kat.size() == 0) && !categor.equals("beylekiler")) ||
                        (!kat.contains(cursor.getString(1)) && categor.equals("beylekiler"))) &&
                        (mod.contains(cursor.getString(2)) || mod.size() == 0) &&
                        (loc.contains(cursor.getString(8)) || loc.size() == 0) &&
                        (col.contains(cursor.getString(cursor.getColumnIndex("color"))) || col.size() == 0) &&
                        (credit.equals(cursor.getString(15)) || credit.equals("")) &&
                        (obmen.equals(cursor.getString(16)) || obmen.equals("")) &&
                        (pr1 <= cursor.getInt(6) || pr1 == 0) &&
                        (pr2 >= cursor.getInt(6) || pr2 == 0)
                        &&
                        (yr1 <= cursor.getInt(14) || yr1 == 0) && (yr2 >= cursor.getInt(14) || yr2 == 0)) {
                    m = new model_cars();
                    Log.d("cursorin", cursor.getString(2));

                    m.setId(cursor.getString(0));
                    m.setCategory(cursor.getString(1));
                    m.setModel(cursor.getString(2));
                    m.setImg(cursor.getString(3));
                    m.setPrice(divider(cursor.getString(6)));
                    m.setDescription(cursor.getString(7));
                    m.setLocation(cursor.getString(8));
                    m.setVip(cursor.getString(9));
                    m.setTime(cursor.getString(10));
                    m.setNumber(cursor.getString(11));
                    m.setWathced(cursor.getString(12));
                    m.setDate(cursor.getString(13));
                    m.setYear(cursor.getString(14));
                    m.setCredit(cursor.getString(15));
                    m.setObmen(cursor.getString(16));
                    m.setSatyldy(cursor.getString(17));
                    m.setProbeg(divider(cursor.getString(19)));
                    m.setKuzow(cursor.getString(20));
                    m.setWatch(cursor.getString(21));//is watched
                    m.setColor(cursor.getString(cursor.getColumnIndex("color")));
                    m.setKaropka(cursor.getString(cursor.getColumnIndex("karopka")));
                    m.setMator(cursor.getString(cursor.getColumnIndex("mator")));
                    m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
                    m.setPid(cursor.getString(cursor.getColumnIndex("pid")));
                    m.setIdimg(R.drawable.tick);
                    if (m.getDate().equals(date)) {
                        Date t = new Date();
                        ff = new SimpleDateFormat("hh");
                        String ti = ff.format(t);
                        String ti1 = m.getTime();

                        int k = Integer.parseInt(ti) - Integer.parseInt(ti1.substring(0, 2));
                        if(k<2)m.setWagt(wagt); else m.setWagt(day);
                    }
                    data.add(m);
                    if (data.size() > limit) break;
                }

            }
        }

        i = 0;
        int k = 0;
        while (vip1.size() != i) {
            if (data.size() < k) data.add(vip1.get(i));
            else data.add(k, vip1.get(i));
            i++;
            k += 1;
        }
        i = 0;
        Log.d("vip.size", "" + vip.size());
        Log.d("data.size", "" + data.size());
        while (vip.size() != i) {
            if (data.size() < k) data.add(vip.get(i));
            else data.add(k, vip.get(i));
            i++;
            k += 2;
        }
        i = 0;
        k = 3;
        while (Gyssagly.size() != i) {
            if (data.size() < k) data.add(Gyssagly.get(i));
            else data.add(k, Gyssagly.get(i));
            i++;
            k += 3;
        }
        Log.d("datasizedb", "" + data.size());
        i = 0;
        Log.d("data.size", "" + data.size());
        Log.d("banner size", "" + banner.size());
        k = 5;
        while (banner.size() != i) {
            if (data.size() < k) data.add(banner.get(i));
            else data.add(k, banner.get(i));
            i++;
            k += 5;
        }
        Log.d("data.size1", "" + data.size());
        return data;


    }


    public void insert_adds(String table, String id, String category, String model, String price, String description, String location
            , String vip, String time, String number, String wathced, String date, String year, String credit, String obmen,
                            String satyldy, String probeg, String kuzow, String color, String karopka, String mator, String image, String vipEndDate,String image1,String image2,String image3) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into " + table + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, category);
            statement.bindString(3, model);
            statement.bindString(4, image);
            statement.bindString(5, image1);
            statement.bindString(6, image2);
            statement.bindString(7, price);
            statement.bindString(8, description);
            statement.bindString(9, location);
            statement.bindString(10, vip);
            statement.bindString(11, time);
            statement.bindString(12, number);
            statement.bindString(13, wathced);
            statement.bindString(14, date);
            statement.bindString(15, year);
            statement.bindString(16, credit);
            statement.bindString(17, obmen);
            statement.bindString(18, satyldy);
            statement.bindString(19, image3);
            statement.bindString(20, probeg);
            statement.bindString(21, kuzow);
            statement.bindString(22, "0");
            statement.bindString(23, color);
            statement.bindString(24, karopka);
            statement.bindString(25, mator);

            statement.bindString(27, vipEndDate);
            statement.bindString(28, "0");
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    public void insert_Likedadds(String table, String id, String category, String model, String price, String description, String location
            , String vip, String time, String number, String wathced, String date, String year, String credit, String obmen,
                                 String satyldy, String probeg, String kuzow, String color, String karopka, String mator, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into " + "likedAdds" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, category);
            statement.bindString(3, model);
            statement.bindString(4, image);
            statement.bindString(5, "");
            statement.bindString(6, "");
            statement.bindString(7, price);
            statement.bindString(8, description);
            statement.bindString(9, location);
            statement.bindString(10, vip);
            statement.bindString(11, time);
            statement.bindString(12, number);
            statement.bindString(13, wathced);
            statement.bindString(14, date);
            statement.bindString(15, year);
            statement.bindString(16, credit);
            statement.bindString(17, obmen);
            statement.bindString(18, satyldy);
            statement.bindString(19, "");
            statement.bindString(20, probeg);
            statement.bindString(21, kuzow);
            statement.bindString(22, "0");
            statement.bindString(23, color);
            statement.bindString(24, karopka);
            statement.bindString(25, mator);
            statement.bindString(26, "cars");
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    public void update_addsimage(String table, String id, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("image", image);
        try {
            db.update(table, cv, "ID=?", new String[]{id});
        } catch (SQLiteException ss) {
        }
    }

    public ArrayList<model_cars> get_like_adds(String table, int limit) {
        ArrayList<model_cars> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("likedAdds", null, "tabl=?", new String[]{table}, null, null, null, "" + limit);

        model_cars m;

        while (cursor.moveToNext()) {
            m = new model_cars();
            Log.d("cursorin", cursor.getString(2));
            m.setId(cursor.getString(0));
            m.setCategory(cursor.getString(1));
            m.setModel(cursor.getString(2));
            m.setImg(cursor.getString(3));
            m.setPrice(cursor.getString(6));
            m.setLoved("1");
            m.setDescription(cursor.getString(7));
            m.setLocation(cursor.getString(8));
            m.setVip(cursor.getString(9));
            m.setTime(cursor.getString(10));
            m.setNumber(cursor.getString(11));
            m.setWathced(cursor.getString(12));
            m.setDate(cursor.getString(13));
            m.setYear(cursor.getString(14));
            m.setCredit(cursor.getString(15));
            m.setObmen(cursor.getString(16));
            m.setMator(cursor.getString(cursor.getColumnIndex("mator")));
            m.setProbeg(cursor.getString(cursor.getColumnIndex("probeg")));
            m.setKaropka(cursor.getString(cursor.getColumnIndex("karopka")));
            m.setColor(cursor.getString(cursor.getColumnIndex("color")));
            m.setSatyldy(cursor.getString(17));
            m.setKuzow(cursor.getString(cursor.getColumnIndex("kuzow")));
            m.setWatch(cursor.getString(cursor.getColumnIndex("watch")));
            data.add(m);
        }
        return data;
    }

    //insert to my adds;
    public void insert_myadds(String table, String id, String category, String model, String price, String description,
                              String location, String vip, String time, String number, String wathced, String date,
                              String year, String credit, String obmen, String satyldy, String probeg, String kuzow,
                              String color, String karopka, String mator, String image, String image1, String image2, String image3,
                              String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into " + "myadds" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, category);
            statement.bindString(3, model);
            statement.bindString(4, image);
            statement.bindString(5, image1);
            statement.bindString(6, image2);
            statement.bindString(7, price);
            statement.bindString(8, description);
            statement.bindString(9, location);
            statement.bindString(10, vip);
            statement.bindString(11, time);
            statement.bindString(12, number);
            statement.bindString(13, wathced);
            statement.bindString(14, date);
            statement.bindString(15, year);
            statement.bindString(16, credit);
            statement.bindString(17, obmen);
            statement.bindString(18, satyldy);
            statement.bindString(19, image3);
            statement.bindString(20, probeg);
            statement.bindString(21, kuzow);
            statement.bindString(22, "0");
            statement.bindString(23, color);
            statement.bindString(24, karopka);
            statement.bindString(25, mator);
            statement.bindString(26, "0");
            statement.bindString(27, table);
            statement.bindString(28, key);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    public void update_id(String table, String key, String id) {
        Log.d("gelyanid", id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        db.update(table, cv, "key1=?", new String[]{key});
        if (table.equals("myAddsrealtor")) fragment_bildiris_gosmak2.get_local();
        Cursor cursor = db.rawQuery("select * from myAddsrealtor where id=? ", new String[]{id});
        if (cursor.moveToNext()) Log.d("dbdanc", cursor.getString(0));
    }

    public ArrayList<model_cars> get_myAddscar() {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<model_cars> data = new ArrayList<>();
        model_cars m;
        Cursor cursor = db.rawQuery("select *from myadds Order by id desc", new String[]{});
        Log.d("cursorsize", "" + cursor.getCount());
        while (cursor.moveToNext()) {
            m = new model_cars();
            m.setId(cursor.getString(0));
            m.setCategory(cursor.getString(1));
            m.setModel(cursor.getString(2));
            m.setImg(cursor.getString(3));
            m.setPrice(cursor.getString(6));
            m.setDescription(cursor.getString(7));
            m.setLocation(cursor.getString(8));
            m.setVip(cursor.getString(9));
            m.setTime(cursor.getString(10));
            m.setNumber(cursor.getString(11));
            m.setDate(cursor.getString(13));
            m.setYear(cursor.getString(14));
            m.setCredit(cursor.getString(15));
            m.setObmen(cursor.getString(16));
            m.setSatyldy(cursor.getString(17));
            m.setProbeg(cursor.getString(19));
            m.setKuzow(cursor.getString(20));
            m.setWatch("0");//is watched
            m.setWathced("0");
            m.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            m.setColor(cursor.getString(cursor.getColumnIndex("color")));
            m.setKaropka(cursor.getString(cursor.getColumnIndex("karopka")));
            m.setMator(cursor.getString(cursor.getColumnIndex("mator")));
            data.add(m);
        }
        return data;
    }

    public void sale_myadd(String table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            Log.d(table, id);
            ContentValues cv = new ContentValues();
            cv.put("vip", "4");
            db.update(table, cv, "id=?", new String[]{id});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void delete_myAdd(String table, String id) {
        Log.d("table",table+id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, "ID=?", new String[]{id});
    }

    //change etjek bolsan delete edip son insert et;
    public void update_status(String table, String status, String id) {
        Log.d("table,status,id", table + " " + status + " " + id);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status", status);
        db.update(table, cv, "ID=?", new String[]{id});

    } //statusy update etmeli

    public String getMyAddsId(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String idlar = "";
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            idlar = idlar + cursor.getString(0) + ",";
        }
        if (idlar.length() > 0) idlar = idlar.substring(0, idlar.length() - 1);
        Log.d("idlarsize", "" + idlar.length());
        return idlar;
    }

    public void delete_elements() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("cars", null, null, null, null, null, null);
        int i = 0;
        int pid = 0;
        while (cursor.moveToNext()) {
            i++;
            if (i > 100) {
                pid = cursor.getInt(cursor.getColumnIndex("pid"));
                break;
            }
        }
        String k = "" + pid;
        db.delete("cars", "pid<? and vip<?", new String[]{k, "1"});
    }

    public void insert_vipStatuses(String table, String id, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("vip", status);
        db.update(table, cv, "ID=?", new String[]{id});
    }

    public void vipCheker(String table, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("giryaCheker", "s");
        Cursor cursor = db.query("cars", null, "vip>?", new String[]{"0"}, null, null, null);
        while (cursor.moveToNext()) {
            Log.d("vipEndDate", cursor.getString(cursor.getColumnIndex("vipEndDate")));
            if (cursor.getString(cursor.getColumnIndex("vipEndDate")).equals(date)) {
                vipDeleter(table, cursor.getString(0));
            }
        }
    }

    public void vipDeleter(String table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("vip", 0);
        db.update(table, cv, "ID=?", new String[]{id});
    }


    //realtor database
    public void insert_realtor(String id, String category, String name, String price, String description,
                               String location, String number, String vip, String image, String key, String watch,
                               String watched, String credit, String obmen, String time, String date, String category1, String location1,String image1,String image2,String image3) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("insertion", "boldy");
        String sql = "insert into " + "home" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            Log.d("watch", " " + watch);
            Log.d("watched", " " + watched);
            statement.clearBindings();
            statement.bindString(2, id);
            statement.bindString(3, category);
            statement.bindString(4, name);
            statement.bindString(5, price);
            statement.bindString(6, description);
            statement.bindString(7, location);
            statement.bindString(8, number);
            statement.bindString(9, vip);
            statement.bindString(10, image);
            statement.bindString(11, image1);
            statement.bindString(12, image2);
            statement.bindString(13, image3);
            statement.bindString(14, watched);
            statement.bindString(15, watch);
            statement.bindString(16, credit);
            statement.bindString(17, obmen);
            statement.bindString(18, time);
            statement.bindString(19, date);
            statement.bindString(20, category1);
            statement.bindString(21, location1);
            statement.bindString(22, "0");
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void insert_myaddRealtor(String id, String category, String name, String price, String description, String location, String number,
                                    String image, String image1, String image2, String image3, String key, String credit, String obmen, String time, String date, String category1, String location1) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            Log.d("key", key);
            cv.put("id", "");
            cv.put("category", category);
            cv.put("name", name);
            cv.put("price", price);
            cv.put("description", description);
            cv.put("location", location);
            cv.put("number", number);
            cv.put("image", image);
            cv.put("image1", image1);
            cv.put("image2", image2);
            cv.put("image3", image3);
            cv.put("key1", key);
            cv.put("credit", credit);
            cv.put("obmen", obmen);
            cv.put("time", time);
            cv.put("date", date);
            cv.put("status", 0);
            cv.put("category1", category1);
            cv.put("location1", location1);
            db.insert("myAddsrealtor", null, cv);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void insert_likedaddRealtor(String table, String id, String category, String name, String price, String description, String location, String number,
                                       String image, String image1, String image2,String image3, String date, String watched,String vip) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            Log.d("Image1", "" + image1);
            ContentValues cv = new ContentValues();
            cv.put("id", id);
            cv.put("category", category);
            cv.put("name", name);
            cv.put("price", price);
            cv.put("description", description);
            cv.put("location", location);
            cv.put("number", number);
            cv.put("image", image);
            cv.put("image1", image1);
            cv.put("image2", image2);
            cv.put("image3", image3);
            cv.put("date", date);
            cv.put("vip", vip);
            cv.put("watched", watched);
            cv.put("watch", "1");
            db.insert(table, null, cv);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<ModelRealtor> getLikedRealtor(String table, int limit) {
        ArrayList<ModelRealtor> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(table, null, null, null, null, null, null, "" + limit);
        while (cursor.moveToNext()) {
            ModelRealtor m = new ModelRealtor();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            m.setName(cursor.getString(cursor.getColumnIndex("name")));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setWatched(cursor.getString(cursor.getColumnIndex("watched")));// count watch
            m.setWatch(cursor.getString(cursor.getColumnIndex("watch")));//is watched
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
            m.setLocation(cursor.getString(cursor.getColumnIndex("location")));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            m.setVip(cursor.getString(cursor.getColumnIndex("vip")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            m.setLoved("1");
            data.add(m);

        }
        return data;
    }

    public ArrayList<ModelRealtor> get_dataRealtor(String category, String name, String location, String price1, String price2, int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ModelRealtor> vip = new ArrayList<>();
        ArrayList<ModelRealtor> vip1 = new ArrayList<>();
        ArrayList<ModelRealtor> gyssagly = new ArrayList<>();
        ArrayList<ModelRealtor> banner = new ArrayList<>();
        ArrayList<ModelRealtor> data = new ArrayList<>();
        Log.d("localDb", category + "+" + name + "+" + limit);
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("dd.MM.yyyy");
        String date = ff.format(d);
      /*  Cursor cursor;
        if(!category.equals("")&& !name.equals("")) cursor=db.query("home",null,"vip>? and (category like ? or category1 like ? or location like ? or location1 like ?) and name like ? ",new String[]{"0",category,category,category,category,name},null,null,"  vip DESC");
  else      if(!category.equals(""))cursor=db.query("home",null,"vip>? and (category like ? or category1 like ? or location like ? or location1 like ?) ",new String[]{"0",category,category,category,category},null,null,"  vip DESC");
     else   if(!name.equals(""))cursor=db.query("home",null,"vip>? and name like ? ",new String[]{"0",name},null,null,"  vip DESC");
        else    cursor=db.query("home",null,"vip>? and vip<?",new String[]{"0","4"},null,null,"  vip DESC");


                while(cursor.moveToNext()){
            ModelRealtor m=new ModelRealtor();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
           if(get_dil().equals("tm")) m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            else m.setCategory(cursor.getString(cursor.getColumnIndex("category1")));
            m.setName(cursor.getString(cursor.getColumnIndex("name")));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setWatched(cursor.getString(cursor.getColumnIndex("watched")));// count watch
            m.setWatch(cursor.getString(cursor.getColumnIndex("watch")));//is watched
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
           m.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            if(get_dil().equals("tm"))   m.setLocation(cursor.getString(cursor.getColumnIndex("location")));
            else  m.setLocation(cursor.getString(cursor.getColumnIndex("location1")));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setVip(cursor.getString(cursor.getColumnIndex("vip")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            switch (cursor.getString(cursor.getColumnIndex("vip"))) {
                case "1":
                    vip.add(m);
                    break;
                case "2":
                    gyssagly.add(m);
                    break;
                case "3":
                   banner.add(m);
                    break;
            }}

        String n1="",c1="";
        if(!category.equals("")&&!name.equals("")){
            n1=" and name like ? and  (vip>? or vip=?)"; c1=" where (category like ? or location like ? )    " ;
            cursor=db.rawQuery("select *from "+"home "+c1+n1+" ORder by pid DESC limit ?",new String[]{category,category,name,"3","0",""+limit});
              Log.d("lkjh",""+cursor.getCount());
        }
        if(category.equals("")&& !name.equals("")) {
            n1=" where (name like ? or location like ? or category like ? )and (vip>? or vip=?) ";
            cursor=db.rawQuery("select *from "+"home "+c1+n1+" ORder by pid DESC limit ?",new String[]{name,name,name,"3","0",""+limit});}
        if(!category.equals("")&& name.equals("")) {
            c1=" where (category like ? or location like ? or name like ?) and (vip>? or vip=?) ";
            cursor=db.rawQuery("select *from "+"home "+c1+n1+"  ORder by pid DESC limit ?",new String[]{category,category,category,"3","0",""+limit});
        }
        if(category.equals("")&&name.equals("")) cursor=db.query("home",null,"vip>? or vip=? ",new String[]{"3","0"},null,null,"pid DESC",""+limit);
       Log.d("cursorsizerealtor",""+cursor.getCount());
        while(cursor.moveToNext()){
            Log.d("dbRealtor","geldi");
            ModelRealtor m=new ModelRealtor();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            if(get_dil().equals("tm")) m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            else m.setCategory(cursor.getString(cursor.getColumnIndex("category1")));
            m.setName(cursor.getString(cursor.getColumnIndex("name")));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setWatched(cursor.getString(cursor.getColumnIndex("watched")));// count watch
            m.setWatch(cursor.getString(cursor.getColumnIndex("watch")));//is watched
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
            m.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            if(get_dil().equals("tm"))   m.setLocation(cursor.getString(cursor.getColumnIndex("location")));
            else  m.setLocation(cursor.getString(cursor.getColumnIndex("location1")));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setVip(cursor.getString(cursor.getColumnIndex("vip")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
            data.add(m);
        }

        int i=0;
        int k=0;
        Log.d("vip.size",""+vip.size());
        Log.d("data.size",""+data.size());
        while(vip.size()!=i){
            if(data.size()<k)data.add(vip.get(i)); else data.add(k,vip.get(i));
            i++;
            k+=2;}
        i=0;
        k=3;
        while(gyssagly.size()!=i){
            if(data.size()<k)data.add(gyssagly.get(i)); else data.add(k,gyssagly.get(i));
            i++;
            k+=3;}
        Log.d("datasizedb",""+data.size());
        i=0;
        k=0;
        Log.d("vip.size",""+vip.size());
        Log.d("data.size",""+data.size());
        while(banner.size()!=i){
            if(data.size()<k)data.add(banner.get(i)); else data.add(k,banner.get(i));
            i++;
            k+=2;}*/
      Cursor c1=db.query("home",null,null,null,null,null,null,null);
        if(c1.moveToNext()) Log.d("barda","asda");
      String wagt,day;
        if (get_dil().equals("tm")) {
            wagt="Şu wagt";
            day="Şu gün";
        } else {
            wagt="Сейчас";
            day="Сегодня";}
        Cursor cursor = db.rawQuery("select *from home where vip>? and vip!=?", new String[]{"0", "4"});
        while (cursor.moveToNext()) {
            ModelRealtor m = new ModelRealtor();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            if (get_dil().equals("tm"))
                m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            else m.setCategory(cursor.getString(cursor.getColumnIndex("category1")));
            m.setName(cursor.getString(cursor.getColumnIndex("name")));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setWatched(cursor.getString(cursor.getColumnIndex("watched")));// count watch
            m.setWatch(cursor.getString(cursor.getColumnIndex("watch")));//is watched
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
            m.setPrice(divider(cursor.getString(cursor.getColumnIndex("price"))));
            m.setTime(cursor.getString(cursor.getColumnIndex("time")));
            if (get_dil().equals("tm"))
                m.setLocation(cursor.getString(cursor.getColumnIndex("location")));
            else m.setLocation(cursor.getString(cursor.getColumnIndex("location1")));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setVip(cursor.getString(cursor.getColumnIndex("vip")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
            Log.d("mgetgeate",m.getDate()+m.getTime());
            if (m.getDate().equals(date)) {
                Log.d("giryar","date");
                Date t = new Date();
                ff = new SimpleDateFormat("hh");
                String ti = ff.format(t);
                String ti1 = m.getTime();

                int k = Integer.parseInt(ti) - Integer.parseInt(ti1.substring(0, 2));
                if(k<2)m.setWagt(wagt); else m.setWagt(day);
            }
            if (m.getDate().equals(date)) {
                Log.d("giryar","date");
                Date t = new Date();
                ff = new SimpleDateFormat("hh");
                String ti = ff.format(t);
                String ti1 = m.getTime();

                int k = Integer.parseInt(ti) - Integer.parseInt(ti1.substring(0, 2));
                if(k<2)m.setWagt(wagt); else m.setWagt(day);
            }
            switch (cursor.getString(cursor.getColumnIndex("vip"))) {
                case "1":
                    vip.add(m);
                    break;
                case "2":
                    gyssagly.add(m);
                    break;
                case "3":
                    banner.add(m);
                    break;
                case "5":
                    vip1.add(m);
            }
        }
        Collections.shuffle(banner,new Random());
        String c, n, l1, p1, p2;
        if (category.equals("")) c = "!=?";
        else c = "=?";
        if (location.equals("")) l1 = "!=?";
        else l1 = "=?";
        if (name.equals("")) n = "!=?";
        else n = "=?";
        if (price1.equals("")) p1 = "!=?";
        else p1 = ">=?";
        if (price2.equals("")) p2 = "!=?";
        else p2 = "<=?";
        String l="location1";
        if (get_dil().equals("tm")) l="location";
        Log.d("sqlRealtor","select *from home where (category" + c + " or category1 " + c + ") and  name" + n + " and (location" + l1 + "or location1" + l1 + ") and  price" + p1 + " and price" + p2 + " and (vip=? or vip=?)");
        Log.d("gelyanRealtor",category+" "+name+" "+location+" "+price1+" "+price2);
        Cursor cursor1 = db.rawQuery("select *from home where (category" + c + " or category1 " + c + ") and  name" + n + " and (location" + l1 + "or location1" + l1 + ") and  price" + p1 + " and price" + p2 + " and (vip=? or vip=?) Order by id DESC limit ?", new String[]
                {category, category, name, location, location, price1, price2, "0", "4", "" + limit});
        Log.d("sqlRealtor", "select *from home where category" + c + " and  name" + n + " and location" + l1 + " and  price" + p1 + " and price" + p2 + " and (vip=? or vip=?)  Order by id DESC limit ?");
        while (cursor1.moveToNext()) {
            Log.d("dbRealtor", "geldi");
            ModelRealtor m = new ModelRealtor();
            m.setId(cursor1.getString(cursor1.getColumnIndex("id")));
            if (get_dil().equals("tm"))
                m.setCategory(cursor1.getString(cursor1.getColumnIndex("category")));
            else m.setCategory(cursor1.getString(cursor1.getColumnIndex("category1")));
            m.setName(cursor1.getString(cursor1.getColumnIndex("name")));
            m.setDescription(cursor1.getString(cursor1.getColumnIndex("description")));
            m.setWatched(cursor1.getString(cursor1.getColumnIndex("watched")));// count watch
            m.setWatch(cursor1.getString(cursor1.getColumnIndex("watch")));//is watched
            m.setDate(cursor1.getString(cursor1.getColumnIndex("date")));
            m.setPrice(divider(cursor1.getString(cursor1.getColumnIndex("price"))));
            m.setTime(cursor1.getString(cursor1.getColumnIndex("time")));
                m.setLocation(cursor1.getString(cursor1.getColumnIndex(l)));
            m.setImage(cursor1.getString(cursor1.getColumnIndex("image")));
            m.setVip(cursor1.getString(cursor1.getColumnIndex("vip")));
            m.setNumber(cursor1.getString(cursor1.getColumnIndex("number")));
            m.setLoved(cursor1.getString(cursor1.getColumnIndex("loved")));
            if (m.getDate().equals(date)) {
                Log.d("giryar","date");
                Date t = new Date();
                ff = new SimpleDateFormat("hh");
                String ti = ff.format(t);
                String ti1 = m.getTime();

                int k = Integer.parseInt(ti) - Integer.parseInt(ti1.substring(0, 2));
                if(k<2)m.setWagt(wagt); else m.setWagt(day);
            }
            data.add(m);
        }
        int i = 0;
        int k = 0;
        while (vip1.size() != i) {
            if (data.size() < k) data.add(vip1.get(i));
            else data.add(k, vip1.get(i));
            i++;
            k += 1;
        }
        Log.d("vip.size", "" + vip.size());
        Log.d("data.size", "" + data.size());
        while (vip.size() != i) {
            if (data.size() < k) data.add(vip.get(i));
            else data.add(k, vip.get(i));
            i++;
            k += 2;
        }
        i = 0;
        k = 3;
        while (gyssagly.size() != i) {
            if (data.size() < k) data.add(gyssagly.get(i));
            else data.add(k, gyssagly.get(i));
            i++;
            k += 3;
        }
        Log.d("datasizedb", "" + data.size());
        i = 0;
        k = 5;
        Log.d("vip.size", "" + vip.size());
        Log.d("data.size", "" + data.size());
        while (banner.size() != i) {
            if (data.size() < k) data.add(banner.get(i));
            else data.add(k, banner.get(i));
            i++;
            k += 5;
        }
   cursor.close();

        return data;
    }

    public ArrayList<ModelRealtor> getMyaddsRealtor() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ModelRealtor> data = new ArrayList<>();
        ModelRealtor m;
        Cursor cursor = db.query("myAddsrealtor", null, null, null, null, null, "id desc");
        while (cursor.moveToNext()) {
            m = new ModelRealtor();
            Log.d("id",cursor.getString(cursor.getColumnIndex("id")));
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            if (get_dil().equals("tm"))
                m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            else m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            m.setName((cursor.getString(cursor.getColumnIndex("name"))));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            m.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setImage1(cursor.getString(cursor.getColumnIndex("image1")));
            m.setImage2(cursor.getString(cursor.getColumnIndex("image2")));
            m.setImage3(cursor.getString(cursor.getColumnIndex("image3")));
            m.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            if (get_dil().equals("tm"))
                m.setLocation(cursor.getString(cursor.getColumnIndex("location")));
            else m.setLocation(cursor.getString(cursor.getColumnIndex("location1")));
            m.setVip("0");
            m.setCredit(cursor.getString(cursor.getColumnIndex("credit")));
            m.setObmen(cursor.getString(cursor.getColumnIndex("obmen")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));

            data.add(m);
        }
        return data;
    }

  public boolean check_in(String table ,String id){
      SQLiteDatabase db=this.getWritableDatabase();
      boolean k=false;
      Cursor c=db.rawQuery("select *from "+table+" where ID=?",new String[]{id});
      if(c.moveToNext()) k=true;
      return  k;
  }
    //beylekiler database
    public void insert_beylekiler(String table, String id, String category, String name, String price, String description, String location, String number,
                                  String vip, String image, String key, String watch, String watched, String credit, String obmen, String time, String date, String category1, String location1,String image1,String image2,String image3) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into " + "beylekiler" + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(2, id);
            statement.bindString(3, category);
            statement.bindString(4, name);
            statement.bindString(5, price);
            statement.bindString(6, description);
            statement.bindString(7, location);
            statement.bindString(8, number);
            statement.bindString(9, vip);
            statement.bindString(10, image);
            statement.bindString(11, image1);
            statement.bindString(12, image2);
            statement.bindString(13, image3);
            statement.bindString(14, watched);
            statement.bindString(15, watch);
            statement.bindString(16, credit);
            statement.bindString(17, obmen);
            statement.bindString(18, time);
            statement.bindString(19, date);
            statement.bindString(20, category1);
            statement.bindString(21, location1);
            statement.bindString(22, "0");
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void insert_myaddBeylekiler(String id, String category, String name, String price, String description, String location, String number,
                                       String image, String image1, String image2, String image3, String key, String credit, String obmen, String time, String date, String vip, String category1, String location1) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put("id", "");
            cv.put("category", category);
            cv.put("name", name);
            cv.put("price", price);
            cv.put("description", description);
            cv.put("location", location);
            cv.put("number", number);
            cv.put("image", image);
            cv.put("image1", image1);
            cv.put("image2", image2);
            cv.put("image3", image3);
            cv.put("key1", key);
            cv.put("credit", credit);
            cv.put("obmen", obmen);
            cv.put("time", time);
            cv.put("date", date);
            cv.put("status", 0);
            cv.put("vip", vip);
            cv.put("category1", category1);
            cv.put("location1", location1);
            db.insert("myAddsbeylekiler", null, cv);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    public ArrayList<ModelRealtor> get_dataBeylekiler(String table, String category, String name, int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ModelRealtor> vip = new ArrayList<>();
        ArrayList<ModelRealtor> vip1 = new ArrayList<>();
        ArrayList<ModelRealtor> gyssagly = new ArrayList<>();
        ArrayList<ModelRealtor> banner = new ArrayList<>();
        ArrayList<ModelRealtor> data = new ArrayList<>();
        Log.d("localDb", category + "+" + name + "+" + limit);
        Cursor cursor;
        Date d = new Date();
        SimpleDateFormat ff = new SimpleDateFormat("dd.MM.yyyy");
        String date = ff.format(d);
        if (!category.equals("") && !name.equals(""))
            cursor = db.query("beylekiler", null, "vip>? and (category like ? or category1 like ? or location like ? or location1 like ?) and name like ? ", new String[]{"0", category, category, category, category, name}, null, null, "  vip DESC");
        else if (!category.equals(""))
            cursor = db.query("beylekiler", null, "vip>? and (category like ? or category1 like ? or location like ? or location1 like ?) ", new String[]{"0", category, category, category, category}, null, null, "  vip DESC");
        else if (!name.equals(""))
            cursor = db.query("beylekiler", null, "vip>? and name like ? ", new String[]{"0", name}, null, null, "  vip DESC");
        else
            cursor = db.query("beylekiler", null, "vip>? and vip!=?", new String[]{"0", "4"}, null, null, "  vip DESC");
        String wagt,day;

        if (get_dil().equals("tm")) {
            wagt="Şu wagt";
            day="Şu gün";
        } else {
            wagt="Сейчас";
            day="Сегодня";}
        String loc="location1";
        if(get_dil().equals("tm"))loc="location";
        while (cursor.moveToNext()) {
            ModelRealtor m = new ModelRealtor();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            if (get_dil().equals("tm"))
                m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            else m.setCategory(cursor.getString(cursor.getColumnIndex("category1")));
            m.setName(cursor.getString(cursor.getColumnIndex("name")));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setWatched(cursor.getString(cursor.getColumnIndex("watched")));// count watch
            m.setWatch(cursor.getString(cursor.getColumnIndex("watch")));//is watched
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
            m.setPrice(divider(cursor.getString(cursor.getColumnIndex("price"))));
            m.setLocation(cursor.getString(cursor.getColumnIndex(loc)));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setVip(cursor.getString(cursor.getColumnIndex("vip")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
            m.setTime(cursor.getString(cursor.getColumnIndex("time")));
            if (m.getDate().equals(date)) {
                Log.d("giryar","date");
                Date t = new Date();
                ff = new SimpleDateFormat("hh");
                String ti = ff.format(t);
                String ti1 = m.getTime();

                int k = Integer.parseInt(ti) - Integer.parseInt(ti1.substring(0, 2));
                if(k<2)m.setWagt(wagt); else m.setWagt(day);
            }
            switch (cursor.getString(cursor.getColumnIndex("vip"))) {
                case "1":
                    vip.add(m);
                    break;
                case "2":
                    gyssagly.add(m);
                    break;
                case "3":
                    banner.add(m);
                    break;
                case "5":
                    vip1.add(m);
                    break;
            }
        }
Log.d("namegelyar","kptx "+name+" "+ category);
        String n1 = "", c1 = "";
        if (!category.equals("") && !name.equals("")) {
            n1 = " and name like ? and  (vip>? or vip=?)";
            c1 = " where (category like ? or location like ? or category1 like ? or location1 like ? )    ";
            cursor = db.rawQuery("select *from " + "beylekiler " + c1 + n1 + " ORder by id DESC limit ?", new String[]{category, category,category, category,name, "4", "0", "" + limit});
            Log.d("lkjh", "" + cursor.getCount());
        }
        if (category.equals("") && !name.equals("")) {
            n1 = " where (name like ? or location like ? or category like ? or location1 like ? or category1 like ? )and (vip>? or vip=?) ";
            cursor = db.rawQuery("select *from " + "beylekiler " + c1 + n1 + " ORder by id DESC limit ?", new String[]{name, name, name,name, name, "3", "0", "" + limit});
        }
        if (!category.equals("") && name.equals("")) {
            c1 = " where (category like ? or location like ? or name like ? or category1 like ? or location1 like ?) and (vip>? or vip=?) ";
            cursor = db.rawQuery("select *from " + "beylekiler " + c1 + n1 + "  ORder by id DESC limit ?", new String[]{category, category, category,category, category, "3", "0", "" + limit});
        }
        if (category.equals("") && name.equals(""))
            cursor = db.query("beylekiler", null, "vip>? or vip=? ", new String[]{"3", "0"}, null, null, "id DESC", "" + limit);

        Log.d("cursorsizerealtor", "" + cursor.getCount());
        while (cursor.moveToNext()) {
            Log.d("dbRealtor", "geldi");
            ModelRealtor m = new ModelRealtor();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            if (get_dil().equals("tm"))
                m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            else m.setCategory(cursor.getString(cursor.getColumnIndex("category1")));
            m.setName(cursor.getString(cursor.getColumnIndex("name")));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setWatched(cursor.getString(cursor.getColumnIndex("watched")));// count watch
            m.setWatch(cursor.getString(cursor.getColumnIndex("watch")));//is watched
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
            m.setLocation(cursor.getString(cursor.getColumnIndex(loc)));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setVip(cursor.getString(cursor.getColumnIndex("vip")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            m.setPrice(divider(cursor.getString(cursor.getColumnIndex("price"))));
            m.setLoved(cursor.getString(cursor.getColumnIndex("loved")));
            m.setTime(cursor.getString(cursor.getColumnIndex("time")));
            if (m.getDate().equals(date)) {
                Log.d("giryar","date");
                Date t = new Date();
                ff = new SimpleDateFormat("hh");
                String ti = ff.format(t);
                String ti1 = m.getTime();

try                {                int k = Integer.parseInt(ti) - Integer.parseInt(ti1.substring(0, 2));
                if(k<2)m.setWagt(wagt); else m.setWagt(day);} catch (NullPointerException ss){}
            }
            data.add(m);
        }

        int i = 0;
        int k = 0;
        while (vip1.size() != i) {
            if (data.size() < k) data.add(vip1.get(i));
            else data.add(k, vip1.get(i));
            i++;
            k += 1;
        }
        i = 0;
        Log.d("vip.size", "" + vip.size());
        Log.d("data.size", "" + data.size());
        while (vip.size() != i) {
            if (data.size() < k) data.add(vip.get(i));
            else data.add(k, vip.get(i));
            i++;
            k += 2;
        }
        i = 0;
        k = 3;
        while (gyssagly.size() != i) {
            if (data.size() < k) data.add(gyssagly.get(i));
            else data.add(k, gyssagly.get(i));
            i++;
            k += 3;
        }
        Log.d("datasizedb", "" + data.size());
        i = 0;
        k = 4;
        Log.d("vip.size", "" + vip.size());
        Log.d("data.size", "" + data.size());
        while (banner.size() != i) {
            if (data.size() < k) data.add(banner.get(i));
            else data.add(k, banner.get(i));
            i++;
            k += 2;
        }

        return data;
    }

    public ArrayList<ModelRealtor> getMyaddsBeylekiler() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ModelRealtor> data = new ArrayList<>();
        ModelRealtor m;
        Cursor cursor = db.query("myAddsbeylekiler", null, null, null, null, null, "id desc");
        while (cursor.moveToNext()) {
            m = new ModelRealtor();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            m.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            m.setName((cursor.getString(cursor.getColumnIndex("name"))));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            m.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            m.setImage(cursor.getString(cursor.getColumnIndex("image")));
            m.setImage1(cursor.getString(cursor.getColumnIndex("image1")));
            m.setImage2(cursor.getString(cursor.getColumnIndex("image2")));
            m.setImage3(cursor.getString(cursor.getColumnIndex("image3")));
            m.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            m.setVip(cursor.getString(cursor.getColumnIndex("vip")));
            m.setCredit(cursor.getString(cursor.getColumnIndex("credit")));
            m.setObmen(cursor.getString(cursor.getColumnIndex("obmen")));
            m.setDate(cursor.getString(cursor.getColumnIndex("date")));
            if (get_dil().equals("tm"))
                m.setLocation(cursor.getString(cursor.getColumnIndex("location")));
            else m.setLocation(cursor.getString(cursor.getColumnIndex("location1")));
            data.add(m);
        }
        return data;
    }

    public HashMap<String, String> get_absRealtor(String table, String category, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from " + table + " where vip>?  Order by vip DESC", new String[]{"0"});
        String vip_id = "", maxtable_id = "", maxid = "", idlar = "";
        Log.d("vip_id", vip_id);
        while (cursor.moveToNext()) {
            Log.d("vip", " vipname " + cursor.getString(cursor.getColumnIndex("vip")));
            vip_id = vip_id + cursor.getString(cursor.getColumnIndex("id")) + ",";
        }
        Log.d("vip_id1", vip_id);
        String n1 = "", c1 = "";
    /*    if(!category.equals("")&&!name.equals("") && ){
            n1=" and name like ?"; c1=" where (category like ? or location like ? or category1 like ? or location1 like ?) " ;
            cursor=db.rawQuery("select *from "+table+c1+n1,new String[]{category,location,category,location,name});
        }else
        if(category.equals("")&& !name.equals("")) {
            n1=" where name like ? or location like ? or category like ? or category1 like ? or location1 like ? ";
            cursor=db.rawQuery("select *from "+table+c1+n1,new String[]{name,name,name,name,name});}else
        if(!category.equals("")&& name.equals("")) {
            c1=" where category like ? or location like ? or name like ?";
            cursor=db.rawQuery("select *from "+table+c1+n1,new String[]{category,category,category});
        }else
        if(category.equals("")&&name.equals("")) */
        cursor = db.query(table, new String[]{"id"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            idlar += cursor.getString(cursor.getColumnIndex("id")) + ",";
        }
        cursor = db.query(table, new String[]{"id"}, null, null, null, null, "id DESC");
        if (cursor.moveToFirst()) maxtable_id += cursor.getString(cursor.getColumnIndex("id"));
        HashMap<String, String> data = new HashMap<>();
        if (!vip_id.equals("")) vip_id = vip_id.substring(0, vip_id.length() - 1);
        if (!idlar.equals("")) idlar = idlar.substring(0, idlar.length() - 1);
        data.put("vip_id", vip_id);
        data.put("maxtable", maxtable_id);
        data.put("idlar", idlar);

        return data;
    }

    public ArrayList<ImagesClicker> getBanner(String table) {
        Log.d("tableName",table);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ImagesClicker> images = new ArrayList<>();
        Cursor cursor = db.rawQuery("select  * from " + table + " where vip=?", new String[]{"3"});
        Log.d("GetCount", "   " + cursor.getCount());

        while (cursor.moveToNext()) {
            ImagesClicker im = new ImagesClicker();
            if (!cursor.getString(cursor.getColumnIndex("image")).equals("")) {
                try {
                   im.setImg1( Glide.with(x)
                            .load("http://"+network.address+"/Reklama/adds/images/"+cursor.getString(cursor.getColumnIndex("image")))
                            .asBitmap()
                            .into(240, 320)
                            .get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                im.setId(cursor.getString(cursor.getColumnIndex("id")));
                images.add(im);
            }
        }
        Log.d("images.sizere",""+images.size());
        return images;
    }

    public void insert_bannerLenta(String id, String name, String number, String description, String image, String image1, String image2, String image3, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Log.d("inssert banner", number);
        Log.d("id", id);
        cv.put("id", id);
        cv.put("name", name);
        cv.put("number", number);
        cv.put("description", description);
        cv.put("image", image);
        cv.put("image1", image1);
        cv.put("image2", image2);
        cv.put("image3", image3);
        cv.put("endDate", endDate);
        db.insert("bannerlenta", null, cv);
    }

    public ArrayList<MOdel_lenta_banner> getBannerLenta() {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("getBannerda", "da");
        ArrayList<MOdel_lenta_banner> images = new ArrayList<>();
        MOdel_lenta_banner m;
        Cursor cursor = db.query("bannerlenta", null, null, null, null, null, null, null);
        Log.d("GetCount", "       " + cursor.getCount());
        while (cursor.moveToNext()) {
            m = new MOdel_lenta_banner();
            Log.d("nace sapr", "i");
            try {
                Log.d("imageNAme",cursor.getString(cursor.getColumnIndex("image")));
                m.setImage1(  Glide.with(x)
                        .load("http://"+network.address+"/Reklama/adds/images/"+cursor.getString(cursor.getColumnIndex("image")))
                        .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(240, 320)
                        .get());
                Log.d("msetImage",""+m.getImage1().getByteCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
                    m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
                    m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    m.setTitle(cursor.getString(cursor.getColumnIndex("name")));

            m.setImg1(cursor.getString(cursor.getColumnIndex("image1")));
            m.setImg2(cursor.getString(cursor.getColumnIndex("image2")));
            m.setImg3(cursor.getString(cursor.getColumnIndex("image3")));
            images.add(m);

        }
        Log.d("images.size", "" + images.size());
        return images;
    }

    public String get_maxBanner() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("bannerlenta", null, null, null, null, null, null);
        String max = "0";
        if (cursor.moveToLast()) max = cursor.getString(0);
        return max;
    }

    // chat bilen ishlemek
    public void insert_chat(String id, String msgType, String content, String date) {
        Log.d("insertionChat", msgType + " " + content + " " + date);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("msgtype", msgType);
        cv.put("content", content);
        cv.put("date", date);
        db.insert("chat", null, cv);
    }

    public ArrayList<ChatAppMsgDTO> get_chat(int limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("chat", null, null, null, null, null, "pid Asc", "" + limit);
        ArrayList<ChatAppMsgDTO> chat = new ArrayList<>();
        ChatAppMsgDTO raw;
        Log.d("chat cursor size", "" + cursor.getCount());
        while (cursor.moveToNext()) {
            raw = new ChatAppMsgDTO();
            raw.setMsgType(cursor.getString(cursor.getColumnIndex("msgtype")));
            raw.setMsgContent(cursor.getString(cursor.getColumnIndex("content")));
            raw.setTime(cursor.getString(cursor.getColumnIndex("date")));
            chat.add(raw);
        }
        return chat;
    }

    public void chatMsgdeleter() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("chat", null, null, null, null, null, null, null);
        if (cursor.getCount() > 250) {
            int i = 0;
            if (cursor.moveToLast()) i = Integer.parseInt(cursor.getString(0)) - 50;
            db.delete("chat", "pid<?", new String[]{"" + i});
        }
    }

    public void insert_myID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
      db.delete("myID",null,null);
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        db.insert("myID", null, cv);
    }

    public String getmyID() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query("myID", null, null, null, null, null, null);
        String id = "";
      try{  if (c.moveToNext()) id = c.getString(0);}catch (SQLiteCantOpenDatabaseException ss){}
        return id;
    }

    public String getMAxMessageID() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("chat", null, null, null, null, null, " id DESC", " 1");
        String maxID = "0";
        if (cursor.moveToNext()) maxID = cursor.getString(cursor.getColumnIndex("id"));
        return maxID;
    }

    public void insert_loved(String table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(table, id);
        ContentValues cv = new ContentValues();
        cv.put("loved", "1");
        db.update(table, cv, "id=?", new String[]{id});
    }

    public void delete_loved(String table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(table, id);
        ContentValues cv = new ContentValues();
        cv.put("loved", "0");
        db.update(table, cv, "id=?", new String[]{id});
    }

    //notification
    public void insert_count_not(String cars, String home, String beylekiler, String futbol, String tasinlikler, String gymmatly) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        db.delete("notif", null, null);
        Log.d("cars", cars);
        cv.put("cars", cars);
        cv.put("home", home);
        cv.put("beylekiler", beylekiler);
        cv.put("futbol", futbol);
        cv.put("tasinlikler", tasinlikler);
        cv.put("gymmatly", gymmatly);
        try {
            db.insert("notif", null, cv);
        } catch (SQLiteDatabaseLockedException ss) {
        }
        ;
    }

    //tables
    public HashMap<String, String> get_maxTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        HashMap<String, String> tables = new HashMap<>();
       // Cursor cursor = db.query("cars", null, null, null, null, null, " id DESC", "1");
        tables.put("A",""+ getMAxs("cars"));

     tables.put("B", ""+ getMAxs("home"));

      tables.put("C", ""+ getMAxs("beylekiler"));

      tables.put("D",""+ getMAxs("futbol"));
       tables.put("E", ""+ getMAxs("tasinlikler"));
        tables.put("F", ""+ getMAxs("gymmatly"));

        return tables;
    }

    //
    public String get_notifies(String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String s = "0";
        Cursor c = db.rawQuery("select " + type + " from notif", new String[]{});
//        Log.d("asdfg", "" + c.getCount());
        if (c.moveToNext()) s = c.getString(c.getColumnIndex(type));
        Random random=new Random();

        if(s=="0") s=""+random.nextInt(30);
        return s;
    }

    //home
    public HashMap<String, String> get_newNothome() {
        SQLiteDatabase db = this.getWritableDatabase();
        HashMap<String, String> tabl = new HashMap<>();
        Cursor cursor = db.query("home", null, "category like?", new String[]{"Satlyk Elitkalar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("A", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("A", "0");
        cursor = db.query("home", null, "category like?", new String[]{"Arenda Elitkalar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("B", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("B", "0");
        cursor = db.query("home", null, "category like?", new String[]{"Satlyk Jaýlar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("D", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("D", "0");
        cursor = db.query("home", null, "category like?", new String[]{"Arenda Jaýlar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("E", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("E", "0");
        cursor = db.query("home", null, "category like ?", new String[]{"Satlyk we Arenda Ýerler"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("F", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("F", "0");
        cursor = db.query("home", null, "category like?", new String[]{"Satlyk Ofislar we Marketlar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("G", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("G", "0");
        cursor = db.query("home", null, "category like?", new String[]{"Arenda Ofislar we Marketlar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("H", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("H", "0");
        cursor = db.query("home", null, "location like?", new String[]{"Aşgabat"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("A1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("A1", "0");
        cursor = db.query("home", null, "location like?", new String[]{"Ahal"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("B1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("B1", "0");
        cursor = db.query("home", null, "location like?", new String[]{"Balkan"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("C1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("C1", "0");
        cursor = db.query("home", null, "location like?", new String[]{"Lebap"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("D1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("D1", "0");
        cursor = db.query("home", null, "location like?", new String[]{"Mary"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("E1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("E1", "0");
        cursor = db.query("home", null, "location like?", new String[]{"Daşoguz"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("F1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("F1", "0");
        return tabl;
    }

    //cars
    public HashMap<String, String> get_newNotcars() {
        SQLiteDatabase db = this.getWritableDatabase();
        HashMap<String, String> tabl = new HashMap<>();
        Cursor cursor = null;
        cursor = db.query("cars", null, "location like?", new String[]{"1"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("A", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("A", "0");
        cursor = db.query("cars", null, "location like?", new String[]{"2"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("B", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("B", "0");
        cursor = db.query("cars", null, "location like?", new String[]{"3"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("C", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("C", "0");
        cursor = db.query("cars", null, "location like?", new String[]{"4"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("D", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("D", "0");
        cursor = db.query("cars", null, "location like?", new String[]{"5"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("E", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("E", "0");
        cursor = db.query("cars", null, "location like?", new String[]{"6"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("F", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("F", "0");

        return tabl;
    }

    public HashMap<String, String> get_newNotBeylekiler() {
        SQLiteDatabase db = this.getWritableDatabase();
        HashMap<String, String> tabl = new HashMap<>();
        Cursor cursor = db.query("beylekiler", null, "category like?", new String[]{"Hyzmatar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("A", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("A", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Öý tehnikasy"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("B", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("B", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Telefonlar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("D", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("D", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Kompýuterlar we enjamlary'"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("E", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("E", "0");
        cursor = db.query("beylekiler", null, "category like ?", new String[]{"Iş we işgär"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("F", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("F", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Öý goşlary"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("G", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("G", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Egin-Eşiklar we aksesuarlar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("H", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("H", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Çagalar üçin ähli zatlar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("J", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("J", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Awtoşaylar"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("K", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("K", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Sport we dynç alyş"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("L", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("L", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Haryt gözleýälär-maňa gerek"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("M", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("M", "0");
        cursor = db.query("beylekiler", null, "category like?", new String[]{"Ýitirlen we tapylan"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("N", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("N", "0");
        cursor = db.query("beylekiler", null, "location like?", new String[]{"Aşgabat"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("A1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("A1", "0");
        cursor = db.query("beylekiler", null, "location like?", new String[]{"Ahal"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("B1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("B1", "0");
        cursor = db.query("beylekiler", null, "location like?", new String[]{"Balkan"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("C1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("C1", "0");
        cursor = db.query("beylekiler", null, "location like?", new String[]{"Lebap"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("D1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("D1", "0");
        cursor = db.query("beylekiler", null, "location like?", new String[]{"Mary"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("E1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("E1", "0");
        cursor = db.query("beylekiler", null, "location like?", new String[]{"Daşoguz"}, null, null, " id DESC", "1");
        if (cursor.moveToNext()) tabl.put("F1", cursor.getString(cursor.getColumnIndex("id")));
        else tabl.put("F1", "0");
        return tabl;
    }

    public void date_checker(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("girdi","bannerChekcer");
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd");
        String date = f.format(d);
        Log.d("dateaa", date);
        db.delete(table, " endDate<=?", new String[]{date});
        Cursor c = db.query(table,null,null,null,null,null,null);
        if (c.moveToNext()) {
            Log.d("endDAte cr", c.getString(c.getColumnIndex("endDate")));
        }
    }

    public void add_deleter(final String table) {
        Thread d = new Thread(new Runnable() {
            @Override
            public void run() {

                SQLiteDatabase db = Db.this.getWritableDatabase();
                Cursor cursor = db.query(table, null, null, null, null, null, null);
                if (cursor.getCount() >10) {
                    if (cursor.moveToLast()) {
                        int id =cursor.getColumnIndex("id") - 10;
                        Log.d("pid",""+id);
                        db.delete(table, "id<?", new String[]{"" + id});
                    }
                }

            }
        });
        d.start();
    }

    public void delete_like(String table, String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        if (table.equals("liked_gyzykly")) db.delete(table, "pid=?", new String[]{id});
        else
            db.delete(table, "id=?", new String[]{id});
    }

    public void delete_liked(String table, String id, String table1) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, "id=? and table1=?", new String[]{id, table1});
    }

    public int get_cursor_count(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        int count = 0;
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        count = cursor.getCount();
        return count;
    }

    public ArrayList<MOdel_lenta_banner> getbannerRealtor() {
        ArrayList<MOdel_lenta_banner> data = new ArrayList<>();
        MOdel_lenta_banner m;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select *from home where vip=? ", new String[]{"3"});
        while (cursor.moveToNext()) {
            m = new MOdel_lenta_banner();
            m.setId(cursor.getString(cursor.getColumnIndex("id")));
            m.setTitle(cursor.getString(cursor.getColumnIndex("name")));
            m.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            m.setNumber(cursor.getString(cursor.getColumnIndex("number")));
            try {
                m.setImage1(  Glide.with(x)
                        .load("http://"+ network.address+"/Reklama/adds/images/"+cursor.getString(cursor.getColumnIndex("image1")))
                        .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(240, 320)
                        .get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            m.setImg1(cursor.getString(cursor.getColumnIndex("image1")));
            m.setImg2(cursor.getString(cursor.getColumnIndex("image2")));
            m.setImg3(cursor.getString(cursor.getColumnIndex("image3")));
            data.add(m);
        }

        return data;
    }
  public void deletedb(){
      Log.d("deleteDb","deleted");
      SQLiteDatabase db=this.getWritableDatabase();
      db.delete("cars",null,null);
      db.delete("home",null,null);
      db.delete("beylekiler",null,null);
      db.delete("gymmatly",null,null);
      db.delete("tasinlikler",null,null);
      db.delete("futbol",null,null);
      db.delete("bannerlenta",null,null);

  }
  public int getMAxs(String table){
      SQLiteDatabase db=this.getWritableDatabase();
      int k=0;
      Cursor c=db.rawQuery("select * from "+table+"_maxs ",new String []{});
while(c.moveToNext())k=c.getInt(c.getColumnIndex(table));
      Log.d("maxID",""+k);
  return  k;}

  public void insert_max(String table, String max){
      SQLiteDatabase db=this.getWritableDatabase();
      db.delete(table+"_maxs",null,null);
      ContentValues cv=new ContentValues();
      cv.put(table,max);
      db.insert(table+"_maxs",null,cv);
  }
  public  void deleteTable(String table){
    SQLiteDatabase db=this.getWritableDatabase();
      db.delete(table,null,null);

  }
  public boolean isIn(String table, String id){
      SQLiteDatabase db=this.getWritableDatabase();
      boolean s=false;
      Cursor c=db.rawQuery("Select ID from "+table +" where id=?",new String[]{id});
      if(c.moveToNext())s=true;
      c.close();
      return  s;

  }
  public String divider(String dividr){
      String div="";
      int k=0;
      if(dividr.length()>3){
      for(int i=dividr.length()-1; i>=  0; i--){
         if(k==3){ div="."+div; k=0;}
             div=dividr.charAt(i)+div;
          k++;
      }} else div=dividr;
  return div;}
    public  void insert_liked_market(String itemID, String name, String image, String image1, String image2,String image3, String content, String skidka, String price, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", itemID);
        cv.put("name", name);
        cv.put("image", image);
        cv.put("image1", image1);
        cv.put("image2", image2);
        cv.put("image3", image3);
        cv.put("content", content);
        cv.put("skidka", skidka);
        cv.put("price", price);
        cv.put("number", number);
        try {
            db.insert("online_liked", null, cv);
        } catch (SQLiteDatabaseLockedException ss) {
        };
    }
     public ArrayList<model_items> get_online_like(){
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.query("online_liked", null, null, null, null, null,null);
         ArrayList<model_items>  liked= new ArrayList<>();
         model_items raw;
         while (cursor.moveToNext()) {
             raw = new model_items(cursor.getString(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("image")),cursor.getString(cursor.getColumnIndex("image1")),
                     cursor.getString(cursor.getColumnIndex("image2")),cursor.getString(cursor.getColumnIndex("image3")),cursor.getString(cursor.getColumnIndex("content")),cursor.getString(cursor.getColumnIndex("skidka")),cursor.getString(cursor.getColumnIndex("price")),cursor.getString(cursor.getColumnIndex("number")));
           liked.add(raw);
         }
         return liked;
     }
     public  void delete_online_liked(String itemID){

         SQLiteDatabase db = this.getWritableDatabase();
             db.delete("online_liked", "id=?", new String[]{itemID});
     }
     public  String isInLiked(String ItemID){
         SQLiteDatabase db=this.getWritableDatabase();
        String s="0";
         Cursor c=db.rawQuery("Select ID from online_liked where id=?",new String[]{ItemID});
         if(c.moveToNext())s="1";
         c.close();
         return  s;
     }
}
