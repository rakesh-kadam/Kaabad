package com.example.altafshah.goldbin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ScrapAdapter extends RecyclerView.Adapter<ScrapAdapter.MyViewHolder> {

    private List<Scrap> scrapList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title,pricePU, measure, quantity;
        ImageView plus, minus;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.from_name);
            pricePU = (TextView) view.findViewById(R.id.price);
            measure = (TextView) view.findViewById(R.id.measure);
            quantity = (TextView) view.findViewById(R.id.quantity);
            plus = (ImageView) view.findViewById(R.id.cart_plus_img);
            minus = (ImageView) view.findViewById(R.id.cart_minus_img);
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);
        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {

            if (v.getId() == plus.getId()){

                if (Integer.parseInt(quantity.getText().toString())==0){
                    ScrapList.count++;

                }
                int number = Integer.parseInt(quantity.getText().toString()) + 1;
                quantity.setText(String.valueOf(number));
                ScrapList.scrapList.get(getAdapterPosition()).setQuantity(""+number);

            } else if(v.getId() == minus.getId()) {


                int number = Integer.parseInt(quantity.getText().toString()) - 1;
                if(number == 0){
                    ScrapList.count--;
                }
                quantity.setText(String.valueOf(number));
                ScrapList.scrapList.get(getAdapterPosition()).setQuantity(""+number);
            }
        }

    }


    public ScrapAdapter(List<Scrap> moviesList) {
        this.scrapList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Scrap scrap = scrapList.get(position);
        holder.title.setText(scrap.getTitle());
        holder.pricePU.setText(scrap.getPricePU());
        holder.measure.setText(scrap.getMeasure());
        holder.quantity.setText(scrap.getQuantity());
    }

    @Override
    public int getItemCount() {
        return scrapList.size();
    }
}