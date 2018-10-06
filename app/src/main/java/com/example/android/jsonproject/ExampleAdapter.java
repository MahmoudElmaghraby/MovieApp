package com.example.android.jsonproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext ;
    private ArrayList<ExampleItem> mExampleList ;
    private OnItemClickListener mListener ;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener ;
    }

    public ExampleAdapter(Context mContext, ArrayList<ExampleItem> mExampleList) {
        this.mContext = mContext;
        this.mExampleList = mExampleList;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView ;
        public TextView mTextViewCreator ;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){

                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){

                            mListener.onItemClick(position);

                        }

                    }
                }
            });

        }
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.example_item , parent , false);
        return new ExampleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExampleItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getTitle();

        Picasso.with(mContext).load(imageUrl).fit().into(holder.mImageView);
        holder.mTextViewCreator.setText(creatorName);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
