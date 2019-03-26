package synchedapps.tmbazar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;

import synchedapps.tmbazar.Database.Db;
import synchedapps.tmbazar.bb.MainActivity;

/**
 * Created by User on 26.07.2018.
 */

public class dil {
    public static String dili = "ru";
    Db db;
    public Handler s;

    public dil() {
        s = new Handler();
    }

    String[] listItems = new String[2];
    public static String tm_opublikowan,tm_nav_Esasy_sahypa, tm_nav_avtoulaglar, tm_nav_realtor, tm_nav_beylekiler, tm_nav_futbal, tm_nav_tasinlekler,
            tm_nav_gyzyldan_gymmatly, tm_navbildiri_gos, tm_nav_dili, tm_nav_duzgunnama, tm_nav_habarlas, tm_toolbar_title_esasy_sahypa, tm_bildirisler,
            tm_grid_awtoulaglar, tm_grid_realtor, tm_grid_beylekiler, tm_grid_futbal, tm_grid_tasinlikler, tm_grid_gyzyldan_gymmatly, tm_toolbar_recycle_car,
            tm_tablayout_autolugalar_recycle, tm_tablayout_autaulaglar_saylanan_recycle, tm_kredit_car, tm_obmen_car, tm_gyssagly_car, tm_car_satylan,
            tm_car_menin_awtoulagym, tm_asgabat, tm_ahal, tm_lebap, tm_mary, tm_dasoguz, tm_car_marka, tm_car_yyly, tm_car_renki, tm_car_bahasy, tm_yerlesyan_yeri,
            tm_kredit_beryanler, tm_obmen_edyanler, tm_filter_toolbar, tm_bildiris_gos, tm_kuzow, tm_model, tm_probeg, tm_mator, tm_karopka, tm_kredit_bolya,
            tm_obmen_bolya, tm_gosmaca, tm_surat_gos, tm_telefon_belgi, tm_kody_giriz, tm_sms_ugrat, tm_tassykla, tm_goyuldy, tm_garasylyar, tm_kredit, tm_obmen,
            tm_kabul_edilmedi, tm_satyjt_nomeri, tm_bildirisin_yagdayy, tm_ginisleyib_maglumat, tm_ayyr, tm_satyldy, tm_gyzykly_maglumatlar, tm_nomeri,
            tm_satlyk_elitkalar, tm_arenda_elitkalar, tm_satlyk_jaylar, tm_arenda_jaylar, tm_satlyk_we_arenda_yerler, tm_satlyk_ofislar_we_marketlar,
            arenda_ofislar_we_marketlar, tm_dusundirisi, tm_ady, tm_kategoriya, tm_su_wagt, tm_gozleg, tm_hyzmatlar, tm_oy_tehnikasy, tm_telefonlar,
            tm_kampyuterlar, tm_is_we_isgar, tm_oy_goslary, tm_egin_esikler, tm_cagalar_ucin, tm_awtosaylar, tm_sport_we_dunc, tm_haryt_gozleyan,
            tm_yitirilen_tapylan, tm_halanlarym, tm_saylanylmadyk, tm_bahasy_manatda, tm_probegi_giriz, tm_small_asgabat, tm_small_ahal, tm_small_mary,
            tm_small_balkan, tm_small_lebap, tm_small_dasoguz, tm_men_awtoulagym, tm_balkan, tm_nomer_giriz, tm_yza, tm_sayla, tm_marka_saylanylmady, tm_nomeri_kod_ugradyldy,
            tm_kod_yalnys, tm_in_arzan, tm_in_gymmat, status_yagdayy_garasylyar, status_yagdayy_goyuldy, status_yagdayy_kabul_edilmedi, jan_etmek,
            reklama_halanlaryma_gouldy, reklama_halanlarymdan_ayryldy, tm_otagyn_sany,tm_bildiris_gorkez,hemme_maglumaty_giriz;

    public void dil(Context ctx) {
        db = new Db(MainActivity.ctx);
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(ctx, R.style.MaterialThemeDialog);
        if (db.get_dil().equals("ru")) {
            mBuilder.setTitle("Выберите язык");
            listItems[0] = "Русский язык";
            listItems[1] = "Туркменский язык";
            mBuilder.setSingleChoiceItems(listItems, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mBuilder.setSingleChoiceItems(listItems, i, this);
                            Log.d("icinde", "dfg");
                            if (i == 0) {
                                dili = "ru";
                                db.inser_dil("ru");
                            } else {
                                dili = "tm";
                                db.inser_dil("tm");
                            }
                        }
                    }
            );
            mBuilder.setPositiveButton("Выберите", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                    Intent in = MainActivity.ctx.getPackageManager()
                            .getLaunchIntentForPackage(MainActivity.ctx.getPackageName());
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    MainActivity.ctx.startActivity(in);
                }
            });
        } else {

            mBuilder.setTitle("Dili Sayla");
            listItems[0] = "Rus dili";
            listItems[1] = "Turkmen dili";
            mBuilder.setSingleChoiceItems(listItems, 1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mBuilder.setSingleChoiceItems(listItems, i, this);

                    if (i == 0) {
                        dili = "ru";
                        db.inser_dil("ru");
                    } else {
                        dili = "tm";
                        db.inser_dil("tm");
                    }


                }

            });
            mBuilder.setPositiveButton("Saýlaň", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                    Intent in = MainActivity.ctx.getPackageManager()
                            .getLaunchIntentForPackage(MainActivity.ctx.getPackageName());
                    in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    MainActivity.ctx.startActivity(in);
                }
            });

        }


        AlertDialog dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(1, 1, 1));

    }

    public void set_text() {
        db = new Db(MainActivity.ctx);
        if (db.get_dil().equalsIgnoreCase("ru")) {
            tm_nav_Esasy_sahypa = "Главная";
            tm_nav_avtoulaglar = "Автомобили";
            tm_nav_realtor = "REALTOR";
            tm_nav_beylekiler = "Другие объявления";
            tm_nav_futbal = "100% Футбол";
            tm_nav_tasinlekler = "Интересный Мир";
            tm_nav_gyzyldan_gymmatly = "Онлайн Маркет";
            tm_navbildiri_gos = "Добавить объявления";
            tm_nav_dili = "Язык";
            tm_nav_duzgunnama = "Соглашение";
            tm_nav_habarlas = "Администратор";
            tm_toolbar_title_esasy_sahypa = "ТМБАЗАР";
            tm_bildirisler = "ОБЪЯВЛЕНИЕ";
            tm_grid_awtoulaglar = "АВТОМОБИЛИ";
            tm_grid_realtor = "REALTOR";
            tm_grid_futbal = "100% ФУТБОЛ";
            tm_grid_tasinlikler = "ИНТЕРЕСНЫЙ МИР";
            tm_grid_gyzyldan_gymmatly = "ОНЛАЙН МАРКЕТ";
            tm_grid_beylekiler = "ДРУГИЕ ОБЪЯВЛЕНИЕ";
            tm_tablayout_autolugalar_recycle = "ВСЕ";
            tm_tablayout_autaulaglar_saylanan_recycle = "ВЫБОРОЧНЫЕ";
            tm_kredit_car = "КРЕДИТ";
            tm_obmen_car = "ОБМЕН";
            tm_gyssagly_car = "СРОЧНО";
            tm_car_satylan = "ПРОДАН";
            tm_car_menin_awtoulagym = "МОЙ АВТОМОБИЛЬ";
            tm_asgabat = "АШГАБАТ";
            tm_ahal = "АХАЛ";
            tm_balkan = "БАЛКАН";
            tm_lebap = "ЛЕБАП";
            tm_mary = "МАРЫ";
            tm_dasoguz = "ДАШОГУЗ";
            tm_car_marka = "Марка";
            tm_car_yyly = "Год";
            tm_car_renki = "Цвет";
            tm_car_bahasy = "Цена";
            tm_yerlesyan_yeri = "Место";
            tm_kredit_beryanler = "Кредит";
            tm_obmen_edyanler = "Обмен";
            tm_filter_toolbar = "ФИЛТЕР";
            tm_bildiris_gos = "ДОБАВИТЬ ОБЪЯВЛЕНИЕ";
            tm_kuzow = "Кузов";
            tm_model = "Модель";
            tm_probeg = "Пробег";
            tm_saylanylmadyk = "Не выбрано";
            tm_bahasy_manatda = "Цена в манатах";
            tm_probegi_giriz = "Введите пробег";
            tm_small_asgabat = "Ашгабат";
            tm_small_ahal = "Ахал";
            tm_small_mary = "Мары";
            tm_small_balkan = "Балкан";
            tm_small_lebap = "Лебап";
            tm_small_dasoguz = "Дашогуз";
            tm_mator = "Двигатель";
            tm_karopka = "Коробка передач";
            tm_kredit_bolya = "Кредит";
            tm_obmen_bolya = "Обмен";
            tm_gosmaca = "Дополнительно";
            tm_surat_gos = "Выберите фото";
            tm_telefon_belgi = "Номер тел.";
            tm_kody_giriz = "Введите код";
            tm_sms_ugrat = "Добавить объявление";
            tm_tassykla = "ПРОДВЕРЖДАТЬ";
            tm_opublikowan="Отправьте смс на номер +99365166556";
            tm_goyuldy = "Опубликован";
            tm_garasylyar = "Ожидается";
            tm_kredit = "Кредит";
            tm_obmen = "Обмен";
            tm_kabul_edilmedi = "Не принято";
            tm_satyjt_nomeri = "Номер тел.";
            tm_bildirisin_yagdayy = "Статус объявления";
            tm_ginisleyib_maglumat = "Дополнительная информация";
            tm_ayyr = "Удалить";
            tm_satyldy = "Продано";
            tm_gyzykly_maglumatlar = "ИНТЕРЕСНЫЕ ИНФОРМАЦИИ";
            tm_nomeri = "Номер";
            tm_satlyk_elitkalar = "ЭЛИТКА НА ПРОДАЖУ";
            tm_arenda_elitkalar = "ЭЛИТКА НА АРЕНДУ";
            tm_satlyk_jaylar = "ДОМ НА ПРОДАЖУ";
            tm_arenda_jaylar = "ДОМ НА АРЕНДУ";
            tm_satlyk_we_arenda_yerler = "ПЛОШАДЬ НА АРЕНДУ И ПРОДАЖУ";
            tm_satlyk_ofislar_we_marketlar = "ОФИСЫ И МАРКЕТЫ НА ПРОДАЖУ";
            arenda_ofislar_we_marketlar = "ОФИСЫ И МАРКЕТЫ НА АРЕНДУ";
            tm_dusundirisi = "Объяснение";
            tm_ady = "Название";
            tm_kategoriya = "Категория";
            tm_su_wagt = "СЕЙЧАС";
            tm_gozleg = "Поиск";
            tm_hyzmatlar = "УСЛУГИ";
            tm_oy_tehnikasy = "БЫТОВАЯ ТЕХНИКА";
            tm_telefonlar = "МОБИЛЬНЫЕ ТЕЛЕФОНЫ";
            tm_kampyuterlar = "КОМПЬЮТЕРЫ И КОМПЛЕКТУЮЩИЕ";
            tm_is_we_isgar = "ИЩУ ИЛИ ПРЕДЛАГАЮ РАБОТУ";
            tm_oy_goslary = "ВЕЩИ ДЛЯ ДОМА";
            tm_egin_esikler = "ОДЕЖДЫ И АКСЕССУАРЫ";
            tm_cagalar_ucin = "МАЛЕНЬКИЕ ДЕТИ И ИХ РОДИТЕЛИ";
            tm_awtosaylar = "АВТОЗАПЧАСТИ";
            tm_sport_we_dunc = "СПОРТ И ОТДЫХ";
            tm_haryt_gozleyan = "ИЩУ ИЛИ НУЖНО";
            tm_yitirilen_tapylan = "УТЕРЯНО И НАЙДЕНО";
            tm_halanlarym = "ИЗБРАННЫЕ";
            tm_men_awtoulagym = "МОИ АВТОМОБИЛИ";
            tm_nomer_giriz = "Введите правильный номер";
            tm_yza = "Назад";
            tm_sayla = "Bыберите";
            tm_marka_saylanylmady = "Марка не выбрана";
            tm_nomeri_kod_ugradyldy = "На ваш номер отправлен код подтверждения";
            tm_kod_yalnys = "Введенный код неверен, попробуйте еще раз";
            tm_in_arzan = "от";
            tm_in_gymmat = "до";
            status_yagdayy_garasylyar = "Статус объявление: Ожидается";
            status_yagdayy_goyuldy = "Статус объявление: Опубликован";
            status_yagdayy_kabul_edilmedi = "Статус объявление: Не принято";
            jan_etmek = "Позвонить";
            reklama_halanlaryma_gouldy = "Объявления добавлено в избранное";
            reklama_halanlarymdan_ayryldy = "Объявления удалено из избранных ";
            tm_otagyn_sany = "Количество комнат";
            tm_bildiris_gorkez="Показать объявления";
            hemme_maglumaty_giriz="заполните всю информацию";

        } else {
            hemme_maglumaty_giriz="hemme maglumatlary giriziň";
            tm_nav_Esasy_sahypa = "Esasy Sahypa";
            tm_nav_avtoulaglar = "Awtoulaglar";
            tm_nav_realtor = "Realtor";
            tm_nav_beylekiler = "Beýleki Bildirişler";
            tm_nav_futbal = "100% Futbol";
            tm_nav_tasinlekler = "Täsinlikler";
            tm_nav_gyzyldan_gymmatly = "Online Market";
            tm_navbildiri_gos = "Bildiriş Goş";
            tm_nav_dili = "Dili";
            tm_nav_duzgunnama = "Düzgünnama";
            tm_nav_habarlas = "Habarlaşyň";
            tm_toolbar_title_esasy_sahypa = "TMBAZAR";
            tm_bildirisler = "BILDIRIŞLER";
            tm_grid_awtoulaglar = "AWTOULAGLAR";
            tm_grid_realtor = "REALTOR";
            tm_grid_futbal = "100% FUTBOL";
            tm_grid_tasinlikler = "TÄSINLIKLER";
            tm_grid_gyzyldan_gymmatly = "ONLINE MARKET";
            tm_grid_beylekiler = "BEÝLEKILER";
            tm_tablayout_autolugalar_recycle = "HEMMESI";
            tm_tablayout_autaulaglar_saylanan_recycle = "SAÝLANAN";
            tm_kredit_car = "KREDIT";
            tm_obmen_car = "OBMEN";
            tm_gyssagly_car = "GYSSAGLY";
            tm_car_satylan = "SATYLAN";
            tm_car_menin_awtoulagym = "MENIŇ AWTOULAGYM";
            tm_asgabat = "AŞGABAT";
            tm_ahal = "AHAL";
            tm_lebap = "LEBAP";
            tm_mary = "MARY";
            tm_balkan = "BALKAN";
            tm_dasoguz = "DAŞOGUZ";
            tm_car_marka = "Marka";
            tm_car_yyly = "Ýyly";
            tm_car_renki = "Reňki";
            tm_car_bahasy = "Bahasy";
            tm_yerlesyan_yeri = "Ýeri";
            tm_kredit_beryanler = "Kredit Beryänler";
            tm_obmen_edyanler = "Obmen Edýänler";
            tm_filter_toolbar = "FILTER";
            tm_bildiris_gos = "BILDIRIŞ GOŞ";
            tm_kuzow = "Kuzow";
            tm_model = "Modeli";
            tm_probeg = "Probeg";
            tm_saylanylmadyk = "Saýlanylmadyk";
            tm_bahasy_manatda = "Bahasy manatda";
            tm_probegi_giriz = "Probegi giriz";
            tm_small_asgabat = "Aşgabat";
            tm_small_ahal = "Ahal";
            tm_small_mary = "Mary";
            tm_small_balkan = "Balkan";
            tm_small_lebap = "Lebap";
            tm_small_dasoguz = "Daşoguz";
            tm_mator = "Motory";
            tm_karopka = "Karopkasy";
            tm_kredit_bolya = "Kredit Bolya";
            tm_obmen_bolya = "Obmen Bolya";
            tm_gosmaca = "Goşmaça";
            tm_surat_gos = "Surat Goş";
            tm_telefon_belgi = "Telefon Belgiňiz";
            tm_kody_giriz = "Kody Giriziň";
            tm_sms_ugrat = "Bildirişi goş";
            tm_tassykla = "TASSYKLAŇ";
            tm_opublikowan="+99365166566 nomera sms ugradyň";
            tm_goyuldy = "Goyuldy";
            tm_garasylyar = "Garaşylýar";
            tm_kredit = "Kredit";
            tm_obmen = "Çalyşmak";
            tm_kabul_edilmedi = "Kabul Edilmedi";
            tm_satyjt_nomeri = "Satyjy nomeri";
            tm_bildirisin_yagdayy = "Bildirişiň ýagdaýy:";
            tm_ginisleyib_maglumat = "Giňişleýin maglumat";
            tm_ayyr = "Aýyryň";
            tm_satyldy = "Satyldy";
            tm_gyzykly_maglumatlar = "GYZYKLY MAGLUMATLAR";
            tm_nomeri = "Nomeri";
            tm_satlyk_elitkalar = "SATLYK ELITKALAR";
            tm_arenda_elitkalar = "ARENDA ELITKALAR";
            tm_satlyk_jaylar = "SATLYK JAÝLAR";
            tm_arenda_jaylar = "ARENDA JAÝLAR";
            tm_satlyk_we_arenda_yerler = "SATLYK WE ARENDA ÝERLER";
            tm_satlyk_ofislar_we_marketlar = "SATLYK OFISLAR WE MARKETLAR";
            arenda_ofislar_we_marketlar = "ARENDA OFISLAR WE MARKETLAR";
            tm_dusundirisi = "Düşündirişi";
            tm_ady = "Ady";
            tm_kategoriya = "Kategoriýa";
            tm_su_wagt = "Şu wagt";
            tm_gozleg = "Gözleg";
            tm_hyzmatlar = "HYZMATLAR";
            tm_oy_tehnikasy = "ÖÝ TEHNIKASY";
            tm_telefonlar = "TELEFONLAR WE ENJAMLARY";
            tm_kampyuterlar = "KOMPÝUTER WE ENJAMLARY";
            tm_is_we_isgar = "IŞ WE IŞGÄR";
            tm_oy_goslary = "ÖÝ GOŞLARY";
            tm_egin_esikler = "EGIN EŞIKLER";
            tm_cagalar_ucin = "ÇAGALAR ÜÇIN ÄHLI ZATLAR";
            tm_awtosaylar = "AWTOŞAÝLAR";
            tm_sport_we_dunc = "SPORT WE DYNÇ ALYŞ";
            tm_haryt_gozleyan = "HARYT GÖZLEÝÄN-MAŇA GEREK";
            tm_yitirilen_tapylan = "ÝITIRILEN WE TAPYLAN";
            tm_halanlarym = "HALANLARYM";
            tm_men_awtoulagym = "MENIŇ AWTOULAGYM";
            tm_nomer_giriz = "Nomeri dogry giriziň";
            tm_yza = "Yza";
            tm_sayla = "Saýla";
            tm_marka_saylanylmady = "Marka saýlanylmady";
            tm_nomeri_kod_ugradyldy = "Siziň belgiňize tassyklama kody ugradyldy";
            tm_kod_yalnys = "Girizilen kod ýalňyş täzeden giriziň";
            tm_in_arzan = "iň arzan";
            tm_in_gymmat = "iň gymmat";
            status_yagdayy_garasylyar = "Bildirişiň ýagdaýy:Garaşylýar";
            status_yagdayy_goyuldy = "Bildirişiň ýagdaýy:Goýuldy";
            status_yagdayy_kabul_edilmedi = "Bildirişiň ýagdaýy:Kabul edilmedi";
            jan_etmek = "Jaň etmek";
            reklama_halanlaryma_gouldy = "Bildiriş halanlaryma goşuldy";
            reklama_halanlarymdan_ayryldy = "Bildiriş halanlarymdan aýryldy";
            tm_otagyn_sany = "Otagyň Sany";
            tm_bildiris_gorkez="Bildiriş Görkez";

        }
    }

}

