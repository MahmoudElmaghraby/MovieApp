package com.example.android.jsonproject;

public class ExampleItem {

    private String mImageUrl ;
    private String mTitle;

    private String mOverView ;
    private int mVote ;
    private String mReleaseDate ;

    public ExampleItem() {
    }


    public ExampleItem(String mImageUrl, String mTitle, String mOverView, int mVote, String mReleaseDate) {
        this.mImageUrl = mImageUrl;
        this.mTitle = mTitle;
        this.mOverView = mOverView;
        this.mVote = mVote;
        this.mReleaseDate = mReleaseDate;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getmOverView() {
        return mOverView;
    }

    public int getmVote() {
        return mVote;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }
}
