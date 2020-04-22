package com.example.prisprojekat.Helpers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prisprojekat.R;
import com.example.prisprojekat.models.Article;

import java.util.List;

public class MyAdapterSearching extends RecyclerView.Adapter<MyAdapterSearching.MyViewHolderSearching> {
    private List<Article>lista;

    private OnItemBidClickListener onItemClickListener;

    private OnItemOwnerClickListener onItemOwnerClickListener;


    public interface  OnItemBidClickListener{
        void onItemClick(int position);
    }
    public interface  OnItemOwnerClickListener{
        void onItemClick(int position);
    }

    public void setOnItemBidClickListener(OnItemBidClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public void setOnItemOwnerClickListener(OnItemOwnerClickListener onItemOwnerClickListener){
        this.onItemOwnerClickListener=onItemOwnerClickListener;
    }
    public MyAdapterSearching(List<Article>lista){
        this.lista=lista;
    }

    @NonNull
    @Override
    public MyViewHolderSearching onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card_view,parent,false);
        MyViewHolderSearching mvh=new MyViewHolderSearching(v,onItemClickListener,onItemOwnerClickListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSearching holder, int position) {
        Article current=lista.get(position);
        holder.naziv.setText(holder.naziv.getText().toString()+current.getNazivPredmeta());
        holder.opis.setText(holder.opis.getText().toString()+current.getOpis());
        holder.cena.setText(holder.cena.getText().toString()+Integer.toString(current.getPocetnaCena()));
        holder.owner.setText(holder.owner.getText().toString()+"\n"+current.getKorisnik().getName()+" "+current.getKorisnik().getLastName());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class MyViewHolderSearching extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView naziv;
        public TextView opis;
        public TextView cena;
        public TextView owner;
        public Button bid;
        public Button ownerButton;


        public MyViewHolderSearching(@NonNull View itemView,OnItemBidClickListener onItemClickListener,OnItemOwnerClickListener onItemOwnerClickListener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.article_card_image);
            naziv=itemView.findViewById(R.id.article_card_name);
            opis=itemView.findViewById(R.id.article_card_description);
            cena=itemView.findViewById(R.id.article_card_price);
            owner=itemView.findViewById(R.id.article_card_owner);
            bid=itemView.findViewById(R.id.article_bid_button);
            ownerButton=itemView.findViewById(R.id.article_owner_profile_button);
            bid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(onItemClickListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
            ownerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemOwnerClickListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            onItemOwnerClickListener.onItemClick(position);
                        }
                    }

                }
            });

        }
    }
}
