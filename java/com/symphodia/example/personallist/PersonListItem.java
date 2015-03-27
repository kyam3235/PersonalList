package com.symphodia.example.personallist;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonListItem implements Parcelable{
    private String mName;
    public String getName(){
        return mName;
    }
    public void setName(String name){
        mName = name;
    }

    private int mAge;
    public int getAge(){
        return mAge;
    }
    public void setAge(int age){
        mAge = age;
    }

    public PersonListItem(String name, int age){
        mName = name;
        mAge = age;
    }

    //以下、Parcelable用
    public PersonListItem(Parcel in){
        mName = in.readString();
        mAge = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mAge);
    }

    public static final Creator<PersonListItem> CREATOR = new Creator<PersonListItem>() {
        @Override
        public PersonListItem createFromParcel(Parcel in) {
            return new PersonListItem(in);
        }

        @Override
        public PersonListItem[] newArray(int size) {
            return new PersonListItem[size];
        }
    };
}
