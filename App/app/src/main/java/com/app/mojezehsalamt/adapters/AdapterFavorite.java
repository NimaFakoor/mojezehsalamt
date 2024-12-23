package com.app.mojezehsalamt.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.mojezehsalamt.Config;
import com.app.mojezehsalamt.R;
import com.app.mojezehsalamt.activities.ActivityRecipesDetail;
import com.app.mojezehsalamt.json.JsonConfig;
import com.app.mojezehsalamt.models.ItemFavorite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.ViewHolder> {

    private Context context;
    private List<ItemFavorite> arrayItemFavorite;
    ItemFavorite itemFavorite;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;
        public RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.news_title);
            image = (ImageView) view.findViewById(R.id.news_image);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);

        }

    }

    public AdapterFavorite(Context mContext, List<ItemFavorite> arrayItemFavorite) {
        this.context = mContext;
        this.arrayItemFavorite = arrayItemFavorite;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsv_item_recipes_list, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        itemFavorite = arrayItemFavorite.get(position);

        Typeface font1 = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.title.setTypeface(font1);

        holder.title.setText(itemFavorite.getNewsHeading());

        Picasso.with(context).load(Config.SERVER_URL + "/upload/thumbs/" +
                itemFavorite.getNewsImage()).placeholder(R.drawable.ic_thumbnail).into(holder.image);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                itemFavorite = arrayItemFavorite.get(position);
                int pos = Integer.parseInt(itemFavorite.getCatId());

                Intent intent = new Intent(context, ActivityRecipesDetail.class);
                intent.putExtra("POSITION", pos);
                JsonConfig.NEWS_ITEMID = itemFavorite.getCatId();

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayItemFavorite.size();
    }

}
