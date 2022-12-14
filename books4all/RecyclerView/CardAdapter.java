package com.example.books4all.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books4all.CardItem;
import com.example.books4all.HomeFragment;
import com.example.books4all.R;
import com.example.books4all.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.LogRecord;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> implements Filterable {

    private List<CardItem> cardItemList = new ArrayList<>();
    private List<CardItem> cardItemListNotFiltered = new ArrayList<>();

    private Activity activity;
    private OnItemListener listener;

    public CardAdapter(OnItemListener listener, Activity activity) {
        this.listener = listener;
        this.activity = activity;
    }

    /*
    public CardAdapter(ArrayList<CardItem> list, Activity activity) {
        this.cardItemList = list;
        this.activity = activity;
    }
     */

    public void setData(List<CardItem> list){

        HomeFragment homeFragment = new HomeFragment();

        if(homeFragment.getLess100()) {
            List<CardItem> cardItemsLess100 = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsLess100, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsLess100 = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            System.out.println("setData less100");
            diffResult.dispatchUpdatesTo(this);

        } else if (homeFragment.isMore100()) {
            List<CardItem> cardItemsMore100 = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsMore100, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsMore100 = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            diffResult.dispatchUpdatesTo(this);
        } else if(homeFragment.isGIALLO()) {
            List<CardItem> cardItemsGiallo = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsGiallo, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsGiallo = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            diffResult.dispatchUpdatesTo(this);
        } else if(homeFragment.isNATURE()) {
            List<CardItem> cardItemsNature = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsNature, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsNature = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            diffResult.dispatchUpdatesTo(this);
        } else if(homeFragment.isFANTASY()) {
            List<CardItem> cardItemsFantasy = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsFantasy, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsFantasy = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            diffResult.dispatchUpdatesTo(this);
        }else if(homeFragment.isITA()) {
            List<CardItem> cardItemsIta = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsIta, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsIta = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            diffResult.dispatchUpdatesTo(this);
        }else if(homeFragment.isUK()) {
            List<CardItem> cardItemsUk = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsUk, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsUk = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            diffResult.dispatchUpdatesTo(this);
        }else if(homeFragment.isUSA()){
            List<CardItem> cardItemsUsa = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(cardItemsUsa, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            cardItemsUsa = new ArrayList<>(list);//-------------------------------------------------------
            this.cardItemListNotFiltered = new ArrayList<>(list);

            diffResult.dispatchUpdatesTo(this);
        } else {
            //this.cardItemList = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            final CardItemDiffCallback diffCallback = new CardItemDiffCallback(this.cardItemList, list);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            this.cardItemList = new ArrayList<>(list);
            this.cardItemListNotFiltered = new ArrayList<>(list);

            System.out.println("setData");
            diffResult.dispatchUpdatesTo(this);

        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new CardViewHolder(layoutView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem currentCardItem = cardItemList.get(position);
        String image = currentCardItem.getImageResource();

        if(image.contains("ic_")){
            Drawable drawable = AppCompatResources.getDrawable(activity,
                    activity.getResources().getIdentifier(image, "drawable", activity.getPackageName()));
            holder.bookImage.setImageDrawable(drawable);
        } else {
            Bitmap bitmap = Utilities.getImageBitmap(activity, Uri.parse(image));
            if(bitmap != null){
                holder.bookImage.setImageBitmap(bitmap);
            }
        }
        holder.bookTitle.setText(currentCardItem.getBookName());
        //holder.bookDesc.setText(currentCardItem.getDescriptionName());
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    public CardItem getItemSelected(int pos) {
        return cardItemList.get(pos);
    }

    @Override
    public Filter getFilter() {
        return cardFilter;
    }

    private final Filter cardFilter = new Filter() {
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CardItem> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(cardItemListNotFiltered);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CardItem item : cardItemListNotFiltered){
                    if (item.getBookName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        protected void publishResults(CharSequence constraint, FilterResults results){
            List<CardItem> filteredList = new ArrayList<>();
            List<?> result = (List<?>) results.values;
            for (Object object : result) {
                if (object instanceof CardItem) {
                    filteredList.add((CardItem) object);
                }
            }
            updateCardListItems(filteredList);
        }
    };

    public void updateCardListItems(List<CardItem> filteredList) {
        final CardItemDiffCallback diffCallback = new CardItemDiffCallback(this.cardItemList, filteredList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.cardItemList.clear();
        this.cardItemList.addAll(filteredList);
        diffResult.dispatchUpdatesTo(this);
    }

    /*public List<CardItem> getCardItemList() {
        return cardItemList;
    }

     */
}
