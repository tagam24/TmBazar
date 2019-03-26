package synchedapps.tmbazar.online_market;

import android.graphics.Bitmap;

import java.util.ArrayList;

import synchedapps.tmbazar.online_market.models.bazarlar;
import synchedapps.tmbazar.online_market.models.model_items;
import synchedapps.tmbazar.online_market.models.shops;

/**
 * Created by User on 20.11.2018.
 */

public class Constants {
    public static Bitmap shopImage;
    public static  String category="", bazar="",Location="",market_category="",shop_id="",towar_id="",hb="",shcname="",item_name="",shop_name="",iter_id="";
    public  static  ArrayList<bazarlar> bazarlars=new ArrayList<>();
    public static ArrayList<String > barazid=new ArrayList<>();
    public static ArrayList<String > itemid=new ArrayList<>();
    public static ArrayList<String > shopid=new ArrayList<>();
    public  static  ArrayList<shops> shopses=new ArrayList<>();
    public  static  ArrayList<model_items> items=new ArrayList<>();
    public  static boolean iter=true;
}
