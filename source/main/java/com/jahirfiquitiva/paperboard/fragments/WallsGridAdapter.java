package com.jahirfiquitiva.paperboard.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.graphics.Palette;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jahirfiquitiva.dashboardsample.R;
import com.jahirfiquitiva.paperboard.activities.DetailedWallpaper;
import com.jahirfiquitiva.paperboard.utils.PaletteTransformation;
import static com.jahirfiquitiva.paperboard.utils.PaletteTransformation.PaletteCallback;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class WallsGridAdapter extends BaseAdapter {

    ArrayList<HashMap<String, String>> data;
    public String wallurl;
    private Context context;
    private int numColumns;
    private HashMap<String, String> jsondata = new HashMap<String, String>();

    private WallsHolder holder;

    private boolean usePalete = true;

    public WallsGridAdapter(Context context,
                            ArrayList<HashMap<String, String>> arraylist, int numColumns) {
        super();
        this.context = context;
        this.numColumns = numColumns;
        data = arraylist;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View wallitem = convertView;
        holder = null;
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);

        jsondata = data.get(position);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int imageWidth = (int) (width / numColumns);

        if (wallitem == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            wallitem = inflater.inflate(R.layout.wallpaper_item, parent, false);
            holder = new WallsHolder(wallitem);
            wallitem.setTag(holder);
        } else {
            holder = (WallsHolder) wallitem.getTag();

        }

        holder.name.setText(jsondata.get(Wallpapers.NAME));

        wallurl = jsondata.get(Wallpapers.WALL);

        holder.wall.startAnimation(anim);
        Picasso.with(context)
                .load(wallurl)
                .resize(imageWidth, imageWidth)
                .centerCrop()
                .transform(PaletteTransformation.instance())
                .into(holder.wall,
                new PaletteCallback(holder.wall){
                    @Override
                    public void onSuccess(Palette palette){
                        holder.progressBar.setVisibility(View.GONE);

                        if (usePalete) {
                            if (palette != null) {
                                Palette.Swatch wallSwatch = palette.getVibrantSwatch();
                                if (wallSwatch != null) {
                                    holder.titleBg.setBackgroundColor(wallSwatch.getRgb());
                                    holder.titleBg.setAlpha(1);
                                    holder.name.setTextColor(wallSwatch.getTitleTextColor());
                                    holder.name.setAlpha(1);
                                }
                            }
                        }

                    }

                    @Override
                    public void onError() {

                    }
                });

        return wallitem;
    }

    class WallsHolder {
        ImageView wall;
        TextView name;
        ProgressBar progressBar;
        LinearLayout titleBg;

        WallsHolder(View v) {
            wall = (ImageView) v.findViewById(R.id.wall);
            name = (TextView) v.findViewById(R.id.name);
            progressBar = (ProgressBar) v.findViewById(R.id.progress);
            titleBg = (LinearLayout) v.findViewById(R.id.titlebg);
        }

    }

}
