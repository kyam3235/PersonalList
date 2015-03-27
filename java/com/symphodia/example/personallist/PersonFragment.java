package com.symphodia.example.personallist;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class PersonFragment extends ListFragment{
    private PersonListViewAdapter mAdapter;

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        return fragment;
    }

    public PersonFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //初回起動時はデータを作成する
        if(savedInstanceState == null) {
            mAdapter = new PersonListViewAdapter(getActivity());
            setListAdapter(mAdapter);
            return;
        }

        mAdapter = new PersonListViewAdapter(getActivity());
        mAdapter.addAll(savedInstanceState.getParcelableArrayList("person_list"));
        setListAdapter(mAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        mAdapter = (PersonListViewAdapter) getListAdapter();
        outState.putParcelableArrayList("person_list", mAdapter.getItemList());
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Log.i("PersonFragment", "Clicked on potion " + String.valueOf(position));
        mAdapter.remove(mAdapter.getItem(position));
    }

    public void add(String name, int age) {
        PersonListItem item = new PersonListItem(name, age);
        mAdapter.add(item);
    }
}
