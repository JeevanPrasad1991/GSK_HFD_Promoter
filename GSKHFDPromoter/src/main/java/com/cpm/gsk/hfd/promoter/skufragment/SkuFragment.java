package com.cpm.gsk.hfd.promoter.skufragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cpm.gsk.hfd.promoter.R;
import com.cpm.gsk.hfd.promoter.constant.CommonString;
import com.cpm.gsk.hfd.promoter.database.GSKDatabase;
import com.cpm.gsk.hfd.promoter.fragment.AdviseFragment;
import com.cpm.gsk.hfd.promoter.fragment_6.SubStartFragment;
import com.cpm.gsk.hfd.promoter.gettersetter.Skugettersetter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static android.widget.NumberPicker.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;

public class SkuFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Boolean update = false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2, store_id, sale_count = "";
    FloatingActionButton fab;
    FragmentManager fragmentManager;
    List<Skugettersetter> skugettersetterList;
    boolean validation_flag = true;
    ExpandableListView sku_list;
    GSKDatabase database;
    List<Skugettersetter> brand_list;
    List<Skugettersetter> sku_listData;
    private HashMap<Skugettersetter, List<Skugettersetter>> _listDataChild;
    List<Integer> validate_header = new ArrayList<>();
    HashMap<Skugettersetter, List<Skugettersetter>> listDataChild;
    ExpandableListAdapter listAdapter;
    List<Skugettersetter> category_list;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor = null;

    public SkuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getActivity().getSupportFragmentManager();
        View view = inflater.inflate(R.layout.fragment_sku, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        sku_list = (ExpandableListView) view.findViewById(R.id.sku_list);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = preferences.edit();
        sale_count = preferences.getString(CommonString.KEY_SALES_COUNT, null);
        if (sale_count==null){
            sale_count="0";
        }
        fab.setOnClickListener(this);
        database = new GSKDatabase(getActivity());
        database.open();
        store_id = preferences.getString(CommonString.KEY_STORE_ID, null);
        sku_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                sku_list.clearFocus();
                if (SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                    View currentFocus = getActivity().getCurrentFocus();
                    if (currentFocus != null) {
                        currentFocus.clearFocus();

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        prepareData();
        listAdapter = new ExpandableListAdapter(getActivity(), brand_list, listDataChild);
        // setting list adapter
        sku_list.setAdapter(listAdapter);
      /*  skugettersetterList = database.getSkuMasterData();
        if (skugettersetterList.size() > 0) {
            sku_list.setAdapter(new MyAdapter());
        }*/

        return view;
    }

    public void prepareData() {
        listDataChild = new HashMap<Skugettersetter, List<Skugettersetter>>();
        category_list = database.getCategoryIdData();
        if (category_list.size() > 0) {
            for (int j = 0; j < category_list.size(); j++) {
                brand_list = database.getBrandData(category_list.get(j).getCategory_id().get(0));
            }
        }

        if (brand_list.size() > 0) {
            for (int i = 0; i < brand_list.size(); i++) {
                sku_listData = database.getSkuDataByBrand(brand_list.get(i).getBrand_id().get(0));
                listDataChild.put(brand_list.get(i), sku_listData);
            }
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fab:
                sku_list.clearFocus();
               // if (validateData(listDataChild, brand_list)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Do you want to save sku data ")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    int count = Integer.parseInt(sale_count);
                                    if (count == 0 || count > 0) {
                                        count++;
                                        editor = preferences.edit();
                                        editor.putString(CommonString.KEY_SALES_COUNT, String.valueOf(count));
                                        editor.commit();
                                    }
                                    database.InsertSkulistData(store_id, listDataChild, brand_list);
                                    SubStartFragment fragment1 = new SubStartFragment();
                                  //  AdviseFragment fragment1 = new AdviseFragment();
                                    fragmentManager.beginTransaction().replace(R.id.container, fragment1).commit();
                                    Snackbar.make(fab, "Sku data saved successfully", Snackbar.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
               /* } else {
                    Snackbar.make(fab, "Please fill the data", Snackbar.LENGTH_LONG).show();
                    sku_list.invalidateViews();
                }*/
                break;
        }
    }



  /*  public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return skugettersetterList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyAdapter.ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.sku_item, null);
                holder.sku_name = (TextView) convertView.findViewById(R.id.textsku);
                holder.sku_quantity = (EditText) convertView.findViewById(R.id.qwantity);
                convertView.setTag(holder);
            } else {
                holder = (MyAdapter.ViewHolder) convertView.getTag();
            }

            holder.sku_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        final int position = v.getId();
                        final EditText Caption = (EditText) v;
                        String value1 = Caption.getText().toString().replaceFirst("^0+(?!$)", "");
                        if (value1.equals("")) {
                            skugettersetterList.get(position).setQuantity("");
                        } else {
                            skugettersetterList.get(position).setQuantity(value1);
                        }
                    }
                }
            });

            if (!validation_flag) {
                if (holder.sku_quantity.getText().toString().equalsIgnoreCase("")) {
                    holder.sku_quantity.setBackgroundColor(Color.parseColor("#FF0000"));
                } else {
                    holder.sku_quantity.setBackgroundColor(Color.parseColor("#00BFFF"));
                }
            }
            holder.sku_name.setId(position);
            holder.sku_name.setText(skugettersetterList.get(position).getSku().get(0));
            return convertView;
        }

        private class ViewHolder {
            TextView sku_name;
            EditText sku_quantity;
        }
    }*/


    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;

        public ExpandableListAdapter(Context context, List<Skugettersetter> listDataHeader, HashMap<Skugettersetter, List<Skugettersetter>> listChildData) {
            this._context = context;
            brand_list = listDataHeader;
            _listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return _listDataChild.get(brand_list.get(groupPosition)).get(childPosition);
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final Skugettersetter childText = (Skugettersetter) getChild(groupPosition, childPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.sku_listview, null);
            }
            TextView sku_name = (TextView) convertView.findViewById(R.id.sku_name);
            EditText sku_stock = (EditText) convertView.findViewById(R.id.sku_stock_recieved);

            sku_name.setText(childText.getSku().get(0));
            sku_stock.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        final EditText Caption = (EditText) v;
                        String value1 = Caption.getText().toString().replaceFirst("^0+&(?!$)", "");
                                if (!value1.equals("")) {
                                  //  _listDataChild.get(brand_list.get(groupPosition)).get(childPosition).setQuantity(value1);
                                   childText.setQuantity(value1);
                            } else {
                               // _listDataChild.get(brand_list.get(groupPosition)).get(childPosition).setQuantity("");
                              childText.setQuantity("0");
                        }
                    }
                }
            });
            sku_stock.setText(childText.getQuantity());

           /* if (!validation_flag) {
                if (sku_stock.getText().toString().equalsIgnoreCase("")) {
                    sku_stock.setBackgroundColor(Color.parseColor("#FF0000"));
                } else {
                    sku_stock.setBackgroundColor(Color.parseColor("#00BFFF"));
                }

            }*/
            return convertView;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public int getGroupCount() {
            return brand_list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return _listDataChild.get(brand_list.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return brand_list.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            final Skugettersetter headerTitle = (Skugettersetter) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.brand, null);
            }

            TextView brand_name = (TextView) convertView.findViewById(R.id.brand_name);
            brand_name.setText(headerTitle.getBrand().get(0));
            LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.first);
            if (!validation_flag) {
                if (validate_header.contains(groupPosition)) {
                    layout.setBackgroundColor(Color.RED);
                } else {
                    layout.setBackgroundColor(Color.parseColor("#CCCCCC"));
                }
            }
            return convertView;

        }


    }

    boolean validateData(
            HashMap<Skugettersetter, List<Skugettersetter>> listDataChild2,
            List<Skugettersetter> listDataHeader2) {
        boolean flag = true;
        validate_header.clear();
        for (int i = 0; i < listDataHeader2.size(); i++) {
            for (int j = 0; j < listDataChild2.get(brand_list.get(i)).size(); j++) {
                String qwantity = listDataChild.get(brand_list.get(i)).get(j).getQuantity();
                if (qwantity.equalsIgnoreCase("")) {
                    flag = false;
                    validation_flag = false;
                    validate_header.add(i);
                } else {
                    flag = true;
                    break;
                }

            }
        }
        return flag;
    }

}
