package synchedapps.tmbazar.bb.filter_car;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import synchedapps.tmbazar.R;
import synchedapps.tmbazar.fragment.fragment_car1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class axpandableListview_marka extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener  {
    private ExpandableListView lvCategory;

    private ArrayList<DataItem> arCategory;
    private ArrayList<SubCategoryItem> arSubCategory;
    private ArrayList<ArrayList<SubCategoryItem>> arSubCategoryFinal;
    private SearchManager searchManager;
    private SearchView searchView;
    public static  ArrayList<HashMap<String, String>> parentItems,ss;
    private ArrayList<ArrayList<HashMap<String, String>>> childItems;
    private MyCategoriesExpandableListAdapter myCategoriesExpandableListAdapter;
    private MenuItem searchItem;
static int i=0;
    DataItem dataItem;
    Toolbar toolbar;
    ArrayList<String> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axpandable_listview_marka);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_filter_expand_for_car);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FILTER MARKA");

        searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);
        //   searchView=(SearchView)findViewById(R.id.action_search);
//        searchView.s etIconified(true);
setupR();

    }
    public void setupR(){
        lvCategory = (ExpandableListView) findViewById(R.id.lvCategory);
        arCategory = new ArrayList<>();
        arSubCategory = new ArrayList<>();
        parentItems = new ArrayList<>();
        childItems = new ArrayList<>();

        dataItem = new DataItem();
        dataItem.setCategoryId("1");
        dataItem.setCategoryName("ALFA RAMEO");
         list1 = new ArrayList<>();
        list1.addAll(Arrays.asList(getResources().getStringArray(R.array.ALFA_ROMEO_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list1.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("2");
        dataItem.setCategoryName("Audi");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        list2.addAll(Arrays.asList(getResources().getStringArray(R.array.Audi_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list2.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list2.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("3");
        dataItem.setCategoryName("BMW");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();
        list3.addAll(Arrays.asList(getResources().getStringArray(R.array.BMW_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list3.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list3.get(i));
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("4");
        dataItem.setCategoryName("Buick");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list4 = new ArrayList<>();
        list4.addAll(Arrays.asList(getResources().getStringArray(R.array.Buick_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list4.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list4.get(i));
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("5");
        dataItem.setCategoryName("CAMC");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list5 = new ArrayList<>();
        list5.addAll(Arrays.asList(getResources().getStringArray(R.array.CAMC_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list5.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list5.get(i));
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("6");
        dataItem.setCategoryName("Caterpillar");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list6 = new ArrayList<>();
        list6.addAll(Arrays.asList(getResources().getStringArray(R.array.Caterpillar_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list6.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list6.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("7");
        dataItem.setCategoryName("Changan");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list7 = new ArrayList<>();
        list7.addAll(Arrays.asList(getResources().getStringArray(R.array.Changan_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list7.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list7.get(i));
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("8");
        dataItem.setCategoryName("Chevrolet");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list8 = new ArrayList<>();
        list8.addAll(Arrays.asList(getResources().getStringArray(R.array.Chevrolet_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list8.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list8.get(i));
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("9");
        dataItem.setCategoryName("Chrysler");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list9 = new ArrayList<>();
        list9.addAll(Arrays.asList(getResources().getStringArray(R.array.chrysler_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list9.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list9.get(i));
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("10");
        dataItem.setCategoryName("Citroen");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list10 = new ArrayList<>();
        list10.addAll(Arrays.asList(getResources().getStringArray(R.array.Citroen_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list10.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list10.get(i));
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("11");
        dataItem.setCategoryName("CMC");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list11 = new ArrayList<>();
        list11.addAll(Arrays.asList(getResources().getStringArray(R.array.CMC_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list11.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list11.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("12");
        dataItem.setCategoryName("Container");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list12 = new ArrayList<>();
        list12.addAll(Arrays.asList(getResources().getStringArray(R.array.Container_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list12.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list12.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("13");
        dataItem.setCategoryName("Daewoo");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list13 = new ArrayList<>();
        list13.addAll(Arrays.asList(getResources().getStringArray(R.array.Daewoo_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list13.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list13.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("14");
        dataItem.setCategoryName("DAF");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list14 = new ArrayList<>();
        list14.addAll(Arrays.asList(getResources().getStringArray(R.array.DAF_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list14.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list14.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("15");
        dataItem.setCategoryName("Dodge");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list15 = new ArrayList<>();
        list15.addAll(Arrays.asList(getResources().getStringArray(R.array.Dodge_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list15.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list15.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("16");
        dataItem.setCategoryName("Fekon");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list16 = new ArrayList<>();
        list16.addAll(Arrays.asList(getResources().getStringArray(R.array.Fekon_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list16.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list16.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("17");
        dataItem.setCategoryName("Fiat");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list17 = new ArrayList<>();
        list17.addAll(Arrays.asList(getResources().getStringArray(R.array.Fiat_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list17.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list17.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("18");
        dataItem.setCategoryName("Ford");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list18 = new ArrayList<>();
        list18.addAll(Arrays.asList(getResources().getStringArray(R.array.Ford_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list18.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list18.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("19");
        dataItem.setCategoryName("Forland");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list19 = new ArrayList<>();
        list19.addAll(Arrays.asList(getResources().getStringArray(R.array.Forland_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list19.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list19.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("20");
        dataItem.setCategoryName("Gaz");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list20 = new ArrayList<>();
        list20.addAll(Arrays.asList(getResources().getStringArray(R.array.Gaz_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list20.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list20.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("21");
        dataItem.setCategoryName("Golden Dragon");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list21 = new ArrayList<>();
        list21.addAll(Arrays.asList(getResources().getStringArray(R.array.Golden_dragon_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list21.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list21.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("22");
        dataItem.setCategoryName("Howo");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list22 = new ArrayList<>();
        list22.addAll(Arrays.asList(getResources().getStringArray(R.array.Howo_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list22.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list22.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("23");
        dataItem.setCategoryName("Hyundai");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list23 = new ArrayList<>();
        list23.addAll(Arrays.asList(getResources().getStringArray(R.array.Hyundai_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list23.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list23.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("24");
        dataItem.setCategoryName("Infiniti");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list24 = new ArrayList<>();
        list24.addAll(Arrays.asList(getResources().getStringArray(R.array.Infiniti_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list24.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list24.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("25");
        dataItem.setCategoryName("Isuzu");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list25 = new ArrayList<>();
        list25.addAll(Arrays.asList(getResources().getStringArray(R.array.Isuzy_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list25.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list25.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("26");
        dataItem.setCategoryName("Iveco");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list26 = new ArrayList<>();
        list26.addAll(Arrays.asList(getResources().getStringArray(R.array.Iveco_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list26.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list26.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("27");
        dataItem.setCategoryName("JCB");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list27 = new ArrayList<>();
        list27.addAll(Arrays.asList(getResources().getStringArray(R.array.JCB_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list27.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list27.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("28");
        dataItem.setCategoryName("Jeep");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list28 = new ArrayList<>();
        list28.addAll(Arrays.asList(getResources().getStringArray(R.array.jeep_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list28.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list28.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("29");
        dataItem.setCategoryName("Kamaz");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list29 = new ArrayList<>();
        list29.addAll(Arrays.asList(getResources().getStringArray(R.array.kamaz_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list29.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list29.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("30");
        dataItem.setCategoryName("Kia");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list30 = new ArrayList<>();
        list30.addAll(Arrays.asList(getResources().getStringArray(R.array.kia_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list30.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list30.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("31");
        dataItem.setCategoryName("Kogel");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list31 = new ArrayList<>();
        list31.addAll(Arrays.asList(getResources().getStringArray(R.array.kogel_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list31.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list31.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("32");
        dataItem.setCategoryName("Kuba");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list32 = new ArrayList<>();
        list32.addAll(Arrays.asList(getResources().getStringArray(R.array.kuba_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list32.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list32.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("33");
        dataItem.setCategoryName("Lada");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list33 = new ArrayList<>();
        list33.addAll(Arrays.asList(getResources().getStringArray(R.array.lada_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list33.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list33.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("34");
        dataItem.setCategoryName("Land Rover");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list34 = new ArrayList<>();
        list34.addAll(Arrays.asList(getResources().getStringArray(R.array.Landrover_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list34.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list34.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("35");
        dataItem.setCategoryName("Lexus");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list35 = new ArrayList<>();
        list35.addAll(Arrays.asList(getResources().getStringArray(R.array.lexus_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list35.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list35.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("36");
        dataItem.setCategoryName("Lifan");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list36 = new ArrayList<>();
        list36.addAll(Arrays.asList(getResources().getStringArray(R.array.lifan_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list36.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list36.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("37");
        dataItem.setCategoryName("Man");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list37 = new ArrayList<>();
        list37.addAll(Arrays.asList(getResources().getStringArray(R.array.man_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list37.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list37.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("38");
        dataItem.setCategoryName("Maz");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list38 = new ArrayList<>();
        list38.addAll(Arrays.asList(getResources().getStringArray(R.array.maz_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list38.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list38.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("39");
        dataItem.setCategoryName("Mazda");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list39 = new ArrayList<>();
        list39.addAll(Arrays.asList(getResources().getStringArray(R.array.Mazda_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list39.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list39.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("40");
        dataItem.setCategoryName("Mercedec_Benz");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list40 = new ArrayList<>();
        list40.addAll(Arrays.asList(getResources().getStringArray(R.array.mercedes_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list40.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list40.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("41");
        dataItem.setCategoryName("Mitsubishi");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list41 = new ArrayList<>();
        list41.addAll(Arrays.asList(getResources().getStringArray(R.array.Mitsubishi_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list41.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list41.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("42");
        dataItem.setCategoryName("New Holand");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list42 = new ArrayList<>();
        list42.addAll(Arrays.asList(getResources().getStringArray(R.array.New_Holland_model)));
        arSubCategory = new ArrayList<>();
        for (int i =0 ; i < list42.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list42.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("43");
        dataItem.setCategoryName("MTZ");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list43 = new ArrayList<>();
        list43.addAll(Arrays.asList(getResources().getStringArray(R.array.MTZ_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list43.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list43.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("44");
        dataItem.setCategoryName("Nissan");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list44 = new ArrayList<>();
        list44.addAll(Arrays.asList(getResources().getStringArray(R.array.Nissan_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list44.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list44.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("45");
        dataItem.setCategoryName("Opel");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list45 = new ArrayList<>();
        list45.addAll(Arrays.asList(getResources().getStringArray(R.array.opel_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list45.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list45.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("46");
        dataItem.setCategoryName("Peugeot");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list46 = new ArrayList<>();
        list46.addAll(Arrays.asList(getResources().getStringArray(R.array.peuget_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list46.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list46.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);
        dataItem = new DataItem();
        dataItem.setCategoryId("47");
        dataItem.setCategoryName("Poontiac");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list47 = new ArrayList<>();
        list47.addAll(Arrays.asList(getResources().getStringArray(R.array.Pontiac_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list47.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list47.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("48");
        dataItem.setCategoryName("Prisep");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list48 = new ArrayList<>();
        list48.addAll(Arrays.asList(getResources().getStringArray(R.array.prisep_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list48.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list48.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("49");
        dataItem.setCategoryName("Raf");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list49 = new ArrayList<>();
        list49.addAll(Arrays.asList(getResources().getStringArray(R.array.Raf_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list49.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list49.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("50");
        dataItem.setCategoryName("Renault");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list50 = new ArrayList<>();
        list50.addAll(Arrays.asList(getResources().getStringArray(R.array.renault_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list50.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list50.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("51");
        dataItem.setCategoryName("Saipa");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list51 = new ArrayList<>();
        list51.addAll(Arrays.asList(getResources().getStringArray(R.array.saipa_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list51.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list51.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("52");
        dataItem.setCategoryName("Scania");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list52 = new ArrayList<>();
        list52.addAll(Arrays.asList(getResources().getStringArray(R.array.Scania_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list52.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list52.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("53");
        dataItem.setCategoryName("Scmitz");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list53 = new ArrayList<>();
        list53.addAll(Arrays.asList(getResources().getStringArray(R.array.Shimtz_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list53.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list53.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("54");
        dataItem.setCategoryName("Skoda");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list54 = new ArrayList<>();
        list54.addAll(Arrays.asList(getResources().getStringArray(R.array.Skoda_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list54.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list54.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("55");
        dataItem.setCategoryName("Toyota");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list55 = new ArrayList<>();
        list55.addAll(Arrays.asList(getResources().getStringArray(R.array.toyota_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list55.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list55.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("56");
        dataItem.setCategoryName("UAZ");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list56 = new ArrayList<>();
        list56.addAll(Arrays.asList(getResources().getStringArray(R.array.uaz_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list56.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list56.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("57");
        dataItem.setCategoryName("Ural");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list57 = new ArrayList<>();
        list57.addAll(Arrays.asList(getResources().getStringArray(R.array.Ural_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list57.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list57.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("58");
        dataItem.setCategoryName("Wolkswagen");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list58 = new ArrayList<>();
        list58.addAll(Arrays.asList(getResources().getStringArray(R.array.Wolkswagen_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list58.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list58.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("59");
        dataItem.setCategoryName("Volvo");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list59 = new ArrayList<>();
        list59.addAll(Arrays.asList(getResources().getStringArray(R.array.volvo_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list59.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list59.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("60");
        dataItem.setCategoryName("Waz");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list60 = new ArrayList<>();
        list60.addAll(Arrays.asList(getResources().getStringArray(R.array.waz_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list60.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list60.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("61");
        dataItem.setCategoryName("Yamaha");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list61 = new ArrayList<>();
        list61.addAll(Arrays.asList(getResources().getStringArray(R.array.yamaha_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list61.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list61.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("62");
        dataItem.setCategoryName("ZAZ");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list62 = new ArrayList<>();
        list62.addAll(Arrays.asList(getResources().getStringArray(R.array.waz_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list62.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list62.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("63");
        dataItem.setCategoryName("Zil");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list63 = new ArrayList<>();
        list63.addAll(Arrays.asList(getResources().getStringArray(R.array.Zil_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list63.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list63.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("64");
        dataItem.setCategoryName("Zonda");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list64 = new ArrayList<>();
        list64.addAll(Arrays.asList(getResources().getStringArray(R.array.zonda_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list64.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list64.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("65");
        dataItem.setCategoryName("Москвич");
        arSubCategory = new ArrayList<>();
        ArrayList<String> list65 = new ArrayList<>();
        list65.addAll(Arrays.asList(getResources().getStringArray(R.array.moskwic_model)));
        arSubCategory = new ArrayList<>();
        for (int i = 0; i < list65.size(); i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName(list65.get(i));
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        for (DataItem data : arCategory) {
//                        Log.i("Item id",item.id);
            ArrayList<HashMap<String, String>> childArrayList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mapParent = new HashMap<String, String>();

            mapParent.put(ConstantManager.Parameter.CATEGORY_ID, data.getCategoryId());
            mapParent.put(ConstantManager.Parameter.CATEGORY_NAME, data.getCategoryName());

            int countIsChecked = 0;
            for (SubCategoryItem subCategoryItem : data.getSubCategory()) {

                HashMap<String, String> mapChild = new HashMap<String, String>();
                mapChild.put(ConstantManager.Parameter.SUB_ID, subCategoryItem.getSubId());
                mapChild.put(ConstantManager.Parameter.SUB_CATEGORY_NAME, subCategoryItem.getSubCategoryName());
                mapChild.put(ConstantManager.Parameter.CATEGORY_ID, subCategoryItem.getCategoryId());
                mapChild.put(ConstantManager.Parameter.IS_CHECKED, subCategoryItem.getIsChecked());

                if (subCategoryItem.getIsChecked().equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {

                    countIsChecked++;
                }
                childArrayList.add(mapChild);
            }

            if (countIsChecked == data.getSubCategory().size()) {

                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_TRUE);
            } else {
                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            }

            mapParent.put(ConstantManager.Parameter.IS_CHECKED, data.getIsChecked());
            childItems.add(childArrayList);
            parentItems.add(mapParent);

        }
            for(int i=0;i<childItems.size();i++){
           Log.d("childs",childItems.toString());

            }
        for(int i=0;i<childItems.size();i++){
            Log.d("parents",parentItems.toString());

        }
       if (i==0){ss=parentItems;i=1;}
        ConstantManager.parentItems = parentItems;
        ConstantManager.childItems = childItems;

        myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this, parentItems, childItems, false);
        lvCategory.setAdapter(myCategoriesExpandableListAdapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.filter_car_menu, menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
       // searchView.setSearchableInfo
           //     (searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();
      return true;}




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == android.R.id.home) {
            fragment_car1.empty_cars();
            Intent intent = new Intent(getApplicationContext(), filterActivity.class);
            startActivity(intent);
        }
        if(res_id==R.id.filter_tick)
        {ArrayList<String> cat= new ArrayList<>();
            for(int i=0; i<filterActivity.data.size();i++){
                if(!cat.contains(filterActivity.data.get(i).getCategory())){
                    cat.add(filterActivity.data.get(i).getCategory());
                    fragment_car1.category+=filterActivity.data.get(i).getCategory()+",";

                }}
            if(fragment_car1.category.length()>0)fragment_car1.category=fragment_car1.category.substring(0,fragment_car1.category.length()-1);
            filterActivity.marka.setText(fragment_car1.category);
               fragment_car1.category="";
           finish();
        }
        return true;
    }


    @Override
    public boolean onClose() {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
      //  myCategoriesExpandableListAdapter.filterData(query);
    //    myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this, parentItems, childItems, false);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        myCategoriesExpandableListAdapter.filterData(newText);
       // myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this, parentItems, childItems, false);

        return true;
    }

}

