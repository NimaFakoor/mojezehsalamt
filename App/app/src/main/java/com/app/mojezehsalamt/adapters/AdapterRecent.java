package com.app.mojezehsalamt.adapters;

import android.app.Activity;
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
import com.app.mojezehsalamt.models.ItemRecipesList;
import com.app.mojezehsalamt.utilities.GDPR;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecent extends RecyclerView.Adapter<AdapterRecent.ViewHolder> {

    private Context context;
    private List<ItemRecipesList> arrayItemRecipesList;
    private ItemRecipesList itemRecipesList;
    private InterstitialAd interstitialAd;
    private int counter = 1;

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

    public AdapterRecent(Context context, List<ItemRecipesList> arrayItemRecipesList) {
        this.context = context;
        this.arrayItemRecipesList = arrayItemRecipesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsv_item_recipes_list, parent, false);

        loadInterstitialAd();

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        itemRecipesList = arrayItemRecipesList.get(position);

        Typeface font1 = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.title.setTypeface(font1);

        holder.title.setText(itemRecipesList.getNewsHeading());

        Picasso.with(context).load(Config.SERVER_URL + "/upload/thumbs/" +
                itemRecipesList.getNewsImage()).placeholder(R.drawable.ic_thumbnail).into(holder.image);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemRecipesList = arrayItemRecipesList.get(position);

                int pos = Integer.parseInt(itemRecipesList.getCatId());

                Intent intent = new Intent(context, ActivityRecipesDetail.class);
                intent.putExtra("POSITION", pos);
                JsonConfig.NEWS_ITEMID = itemRecipesList.getCatId();

                context.startActivity(intent);

                showInterstitialAd();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayItemRecipesList.size();
    }

    private void loadInterstitialAd() {
        if (Config.ENABLE_ADMOB_INTERSTITIAL_ADS) {
            interstitialAd = new InterstitialAd(context);
            interstitialAd.setAdUnitId(context.getResources().getString(R.string.admob_interstitial_id));
            final AdRequest adRequest = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, GDPR.getBundleAd((Activity) context)).build();
            interstitialAd.loadAd(adRequest);
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    interstitialAd.loadAd(adRequest);
                }
            });
        }
    }

    private void showInterstitialAd() {
        if (Config.ENABLE_ADMOB_INTERSTITIAL_ADS) {
            if (interstitialAd != null && interstitialAd.isLoaded()) {
                if (counter == Config.ADMOB_INTERSTITIAL_ADS_INTERVAL) {
                    interstitialAd.show();
                    counter = 1;
                } else {
                    counter++;
                }
            }
        }
    }

}
