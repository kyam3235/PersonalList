package com.symphodia.example.personallist;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonListViewAdapter extends ArrayAdapter<PersonListItem>{
    private static final int RESOURCE = R.layout.person_list_item;

    public PersonListViewAdapter(Context context) {
        super(context, 0);
    }

    //意図的に呼び出さない。自動で呼ばれる
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //super.getView()は呼ばない
        View view;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(RESOURCE, parent, false);
        }else{
            view = convertView;
        }

        //自分が持っているデータはgetItem()で取る
        PersonListItem item = getItem(position);

        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(item.getName());
        TextView tvAge = (TextView)view.findViewById(R.id.tv_age);
        tvAge.setText(String.valueOf(item.getAge()));

        return view;
    }

    //以下、必須ではないがあると便利なメソッド
    //ListViewのitemを返す
    //画面の縦横切り替えなどで使う
    public ArrayList<PersonListItem> getItemList(){
        int size = getCount();
        ArrayList<PersonListItem> itemList = new ArrayList<PersonListItem>(size);
        for(int index = 0; index < size; index++){
            itemList.add(getItem(index));
        }
        return itemList;
    }

    //Bundleで復元する時に使う
    public void addAll(ArrayList<Parcelable> parcelableArrayList){
        ArrayList<PersonListItem> itemList = new ArrayList<>();
        for(Parcelable item : parcelableArrayList){
            itemList.add((PersonListItem)item);
        }
        super.addAll(itemList);
    }

    //項目の追加を行う
    public void add(String name, int age) {
        PersonListItem item = new PersonListItem(name, age);
        super.add(item);
    }

    //項目の削除を行う
    public void remove(int index){
        if(index < 0 || index >= getCount()){
            return;
        }
        remove(getItem(index));
    }
}
