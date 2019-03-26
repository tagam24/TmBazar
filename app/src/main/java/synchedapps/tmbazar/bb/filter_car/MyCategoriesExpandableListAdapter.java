package synchedapps.tmbazar.bb.filter_car;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import synchedapps.tmbazar.Models.model_cars;
import synchedapps.tmbazar.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zerones on 04-Oct-17.
 */

public class MyCategoriesExpandableListAdapter extends BaseExpandableListAdapter {

    private final ArrayList<ArrayList<HashMap<String, String>>> childItems,originalList1=new ArrayList<>();
    private ArrayList<HashMap<String, String>> parentItems,originalList=new ArrayList<>();

    //    private final ArrayList<HashMap<String, String>> childItems;
    private LayoutInflater inflater;
    private Activity activity;
    private HashMap<String, String> child;
    private int count = 0;
    private boolean isFromMyCategoriesFragment;

    public MyCategoriesExpandableListAdapter(Activity activity, ArrayList<HashMap<String, String>> parentItem,
                                             ArrayList<ArrayList<HashMap<String, String>>> childItems, boolean isFromMyCategoriesFragment) {
Log.d("parent",""+parentItem.size());Log.d("child"," "+childItems.size());

        this.parentItems = parentItem;
        this.childItems = childItems;
        this.activity = activity;
        this.originalList.addAll(parentItem);
        this.originalList1.addAll(childItems);
        Log.d("originalsize",""+this.originalList.size());
        this.isFromMyCategoriesFragment = isFromMyCategoriesFragment;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (childItems.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {

        return " gelyar" ;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean b, View convertView, ViewGroup viewGroup) {
        final ViewHolderParent viewHolderParent;
        if (convertView == null) {

            if(isFromMyCategoriesFragment) {
                convertView = inflater.inflate(R.layout.group_my_category, null);
            }else {
                convertView = inflater.inflate(R.layout.group_list_layout, null);
            }
            viewHolderParent = new ViewHolderParent();

            viewHolderParent.tvMainCategoryName = convertView.findViewById(R.id.tvMainCategoryName);
            viewHolderParent.cbMainCategory = convertView.findViewById(R.id.cbMainCategory);
            viewHolderParent.ivCategory = convertView.findViewById(R.id.ivCategory);
            convertView.setTag(viewHolderParent);
        } else {
            viewHolderParent = (ViewHolderParent) convertView.getTag();
        }

        if (parentItems.get(groupPosition).get(ConstantManager.Parameter.IS_CHECKED).equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
            viewHolderParent.cbMainCategory.setChecked(true);
            notifyDataSetChanged();

        } else {
            viewHolderParent.cbMainCategory.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderParent.cbMainCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderParent.cbMainCategory.isChecked()) {
                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
                    String group=parentItems.get(groupPosition).get("category_name");
                    model_cars model=new model_cars();
                    Log.d("parent",""+parentItems.get(groupPosition));
                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                        childItems.get(groupPosition).get(i).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
                        String child=childItems.get(groupPosition).get(i).get("sub_category_name");
                        model=new model_cars();
                        model.setCategory(group);
                        model.setModel(child);
                        filterActivity.data.add(model);
                        Log.d("child",""+group+" "+child);
                    }
                    Log.d("dataSize",""+filterActivity.data.size());
                    notifyDataSetChanged();

                }
                else {
                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
                    String group=parentItems.get(groupPosition).get("category_name");
                    Log.d("group",group+ " filtersize"+filterActivity.data.size());
                    int size=filterActivity.data.size();
                    model_cars m=new model_cars();
                    m.setCategory(group);
                    boolean b=true;
                    while (b){
                        b=false;
                        for(int i=0; i<filterActivity.data.size();i++){
                            if(filterActivity.data.get(i).getCategory().equals(group)){filterActivity.data.remove(i); b=true;}}
                    }
                    Log.d("dataSize",""+filterActivity.data.size());
                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                        childItems.get(groupPosition).get(i).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
                    }
                    notifyDataSetChanged();
                }
            }
        });

        ConstantManager.childItems = childItems;
        ConstantManager.parentItems = parentItems;
        ConstantManager.Original=originalList;
        viewHolderParent.tvMainCategoryName.setText(parentItems.get(groupPosition).get(ConstantManager.Parameter.CATEGORY_NAME));

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean b, View convertView, ViewGroup viewGroup) {

        final ViewHolderChild viewHolderChild;
        child = childItems.get(groupPosition).get(childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_choose, null);
            viewHolderChild = new ViewHolderChild();

            viewHolderChild.tvSubCategoryName = convertView.findViewById(R.id.tvSubCategoryName);
            viewHolderChild.cbSubCategory = convertView.findViewById(R.id.cbSubCategory);
            viewHolderChild.viewDivider = convertView.findViewById(R.id.viewDivider);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }

        if (childItems.get(groupPosition).get(childPosition).get(ConstantManager.Parameter.IS_CHECKED).equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
            viewHolderChild.cbSubCategory.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderChild.cbSubCategory.setChecked(false);
            notifyDataSetChanged();
        }

        viewHolderChild.tvSubCategoryName.setText(child.get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
        viewHolderChild.cbSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolderChild.cbSubCategory.isChecked()) {
                    count = 0;
                    String group=parentItems.get(groupPosition).get("category_name");
                    model_cars model=new model_cars();
                    childItems.get(groupPosition).get(childPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
                    String child=childItems.get(groupPosition).get(childPosition).get("sub_category_name");
                    if(!filterActivity.data.contains(child)){
                        model.setCategory(group);
                        model.setModel(child);
                        filterActivity.data.add(model);
                    }
                    Log.d("dataSize",""+filterActivity.data.size());
                    notifyDataSetChanged();
                } else {
                    count = 0;
                    String group=parentItems.get(groupPosition).get("category_name");
                    model_cars model=new model_cars();
                    childItems.get(groupPosition).get(childPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
                    String child=childItems.get(groupPosition).get(childPosition).get("sub_category_name");
                    for(int i=0; i<filterActivity.data.size(); i++){
                        if(filterActivity.data.get(i).getCategory().equals(group) &&
                                filterActivity.data.get(i).getModel().equals(child)){
                            filterActivity.data.remove(i);
                        }
                        Log.d("dataSize",""+filterActivity.data.size());
                    }
                    notifyDataSetChanged();
                }

                for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
                    if (childItems.get(groupPosition).get(i).get(ConstantManager.Parameter.IS_CHECKED).equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
                        count++;
                    }
                }
                if (count == childItems.get(groupPosition).size()) {
                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
                    notifyDataSetChanged();
                } else {
                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
                    notifyDataSetChanged();
                }

                ConstantManager.Original=originalList;
                ConstantManager.childItems = childItems;
                ConstantManager.parentItems = parentItems;
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    private class ViewHolderParent {

        TextView tvMainCategoryName;
        CheckBox cbMainCategory;
        ImageView ivCategory;
    }

    private class ViewHolderChild {

        TextView tvSubCategoryName;
        CheckBox cbSubCategory;
        View viewDivider;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        parentItems.clear();
        childItems.clear();
        if (query.isEmpty()) {
           parentItems.addAll(originalList);
            childItems.addAll(originalList1);
        }
        else {
            for(int i=0; i<originalList.size();i++){
                Log.d("query",query);
                Log.d("Constant name",ConstantManager.Parameter.CATEGORY_NAME);
                if(originalList.get(i).get(ConstantManager.Parameter.CATEGORY_NAME).toLowerCase().contains(query)){
                    parentItems.add(originalList.get(i));
                    childItems.add(originalList1.get(i));
                }
            }

        }

        notifyDataSetChanged();
    }


}