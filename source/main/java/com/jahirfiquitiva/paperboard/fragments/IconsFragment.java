package com.jahirfiquitiva.paperboard.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jahirfiquitiva.dashboardsample.R;

import java.util.ArrayList;

public class IconsFragment extends Fragment {

    private Context context;
    private String[] themedApps;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.icons_grid, container, false);
        themedApps = getResources().getStringArray(getArguments().getInt("themedAppsId", 0));
        context = getActivity();
        GridView gridview = (GridView) view.findViewById(R.id.icons_grid);
        final IconAdapter icAdapter = new IconAdapter();
        gridview.setAdapter(icAdapter);
        return view;

    }

    public static IconsFragment newInstance(int iconsArray, int themedApps) {
        IconsFragment fragment = new IconsFragment();
        Bundle args = new Bundle();
        args.putInt("iconsArrayId", iconsArray);
        args.putInt("themedAppsId", themedApps);
        fragment.setArguments(args);
        return fragment;
    }

    private class IconAdapter extends BaseAdapter {
        private ArrayList<Integer> mThumbs;

        public IconAdapter() {
            loadIcon();
        }

        @Override
        public int getCount() {
            return mThumbs.size();
        }

        @Override
        public Object getItem(int position) {
            return mThumbs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View icono = convertView;
            IconsHolder holder;
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);

            if (icono == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                icono = inflater.inflate(R.layout.icon, parent, false);
                icono.setLayoutParams(new GridView.LayoutParams(120, 120));
                holder = new IconsHolder(icono);
                icono.setTag(holder);
            } else {
                holder = (IconsHolder) icono.getTag();

            }

            holder.icon.startAnimation(anim);
            holder.icon.setImageResource(mThumbs.get(position));
            holder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogIconView = View.inflate(getActivity(), R.layout.dialog_icon, null);
                    ImageView dialogIcon = (ImageView) dialogIconView.findViewById(R.id.dialogicon);
                    dialogIcon.setImageResource(mThumbs.get(position));
                    new MaterialDialog.Builder(getActivity())
                        .customView(dialogIconView, false)
                        .title(themedApps[position])
                        .positiveText(R.string.close)
                        .show();
                }
            });

            return icono;
        }

        class IconsHolder {
            ImageView icon;
            IconsHolder(View v) {
                icon = (ImageView) v.findViewById(R.id.icon_img);
            }
        }

        private void loadIcon() {
            mThumbs = new ArrayList<>();

            final Resources resources = getResources();
            final String packageName = getActivity().getApplication().getPackageName();
            addIcon(resources, packageName, getArguments().getInt("iconsArrayId", 0));

        }

        private void addIcon(Resources resources, String packageName, int list) {
            final String[] extras = resources.getStringArray(list);
            for (String extra : extras) {
                int res = resources.getIdentifier(extra, "drawable", packageName);
                if (res != 0) {
                    final int thumbRes = resources.getIdentifier(extra, "drawable", packageName);
                    if (thumbRes != 0) {
                        mThumbs.add(thumbRes);
                    }
                }
            }
        }

    }


}