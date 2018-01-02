package com.example.android.thequizapp;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public  class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>{


    private Context mctx;
    private List<Category> categoryList;


    CategoriesAdapter(Context mctx, List<Category> categoryList) {
        this.mctx = mctx;
        this.categoryList = categoryList;
    }



    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.cardview,null);
        return  new CategoriesViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        Category categories = categoryList.get(position);
        holder.textviewTittle.setText(categories.getTittle());
        holder.textViewsubtittle.setText(categories.getSubtittle());
        holder.imageView.setImageDrawable(mctx.getResources().getDrawable(categories.getImage()));
        if (categories.isImageChanged()){
            holder.lock.setImageResource(R.drawable.unlock);
        }
        else{
            holder.lock.setImageResource(R.drawable.lock);
        }

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }



    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView,lock;
        TextView textviewTittle,textViewsubtittle;

        public CategoriesViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textviewTittle = itemView.findViewById(R.id.textviewTittle);
            textViewsubtittle = itemView.findViewById(R.id.textViewsubtittle);
            lock = itemView.findViewById(R.id.lock);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position =getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
