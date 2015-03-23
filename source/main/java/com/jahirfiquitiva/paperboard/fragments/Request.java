package com.jahirfiquitiva.paperboard.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jahirfiquitiva.dashboardsample.R;
import com.melnykov.fab.FloatingActionButton;
import com.pkmmte.requestmanager.AppInfo;
import com.pkmmte.requestmanager.PkRequestManager;
import com.pkmmte.requestmanager.RequestSettings;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jahir on 28/02/2015.
 */
public class Request extends Fragment {

    // Request Manager
    private PkRequestManager mRequestManager;

    // App List
    private List<AppInfo> mApps = new LinkedList<AppInfo>();

    // List & Adapter
    private ListView mList;
    private ListAdapter mAdapter;
    private ProgressBar mProgress;
    private View mButton;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_icon_request, null);

        context = getActivity();
        ActionBar toolbar = ((ActionBarActivity)context).getSupportActionBar();
        toolbar.setTitle(R.string.section_five);

        showNewAdviceDialog();

        mList = (ListView) root.findViewById(R.id.appList);
        mList.setVisibility(View.GONE);
        mAdapter = new ListAdapter(getActivity(), mApps);
        mList.setAdapter(mAdapter);

        mProgress = (ProgressBar) root.findViewById(R.id.progress);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo mApp = mApps.get(position);
                mApp.setSelected(!mApp.isSelected());
                mApps.set(position, mApp);
                mAdapter.notifyDataSetChanged();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.send_btn);
        fab.attachToListView(mList);

        mButton = root.findViewById(R.id.send_btn);
        mButton.setVisibility(View.GONE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRequestManager.setActivity(getActivity());

                if (mRequestManager.getNumSelected() < 1) {
                    mRequestManager.sendRequest(true, false);
                } else {
                    mRequestManager.sendRequestAsync();
                }

                Toast.makeText(getActivity(), getString(R.string.building_request), Toast.LENGTH_LONG).show();
            }
        });

        new GrabApplicationsTask().execute();

        return root;
    }

    private void showNewAdviceDialog() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (!prefs.getBoolean("dontshowagain", false)) {
            new MaterialDialog.Builder(getActivity())
                    .title(R.string.advice)
                    .content(R.string.request_advice)
                    .positiveText(R.string.close)
                    .neutralText(R.string.dontshow)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            PreferenceManager.getDefaultSharedPreferences(getActivity())
                                    .edit().putBoolean("dontshowagain", false).commit();
                        }

                        @Override
                        public void onNeutral(MaterialDialog dialog) {
                            PreferenceManager.getDefaultSharedPreferences(getActivity())
                                    .edit().putBoolean("dontshowagain", true).commit();
                        }
                    }).show();
        }

    }

    private class GrabApplicationsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                mRequestManager = PkRequestManager.getInstance(getActivity());

                mRequestManager.setDebugging(true);

                mRequestManager.setSettings(new RequestSettings.Builder()
                        .addEmailAddress(getResources().getString(R.string.email_id))
                        .emailSubject(getResources().getString(R.string.email_request_subject))
                        .emailPrecontent(getResources().getString(R.string.request_precontent))
                        .saveLocation(Environment.getExternalStorageDirectory().getAbsolutePath() + getString(R.string.request_save_location))
                        .build());

                mRequestManager.loadAppsIfEmpty();

                mApps.addAll(mRequestManager.getApps());
            } catch (Exception ex) {
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
            if (mList != null) {
                mList.setVisibility(View.VISIBLE);
            }
            if (mButton != null) {
                mButton.setVisibility(View.VISIBLE);
            }
            if (mProgress != null) {
                mProgress.setVisibility(View.GONE);
            }
        }
    }

    private class ListAdapter extends BaseAdapter {
        private Context mContext;
        private List<AppInfo> mApps;

        public ListAdapter(Context context, List<AppInfo> apps) {
            this.mContext = context;
            this.mApps = apps;
        }

        @Override
        public int getCount() {
            return mApps.size();
        }

        @Override
        public AppInfo getItem(int position) {
            return mApps.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            AppInfo mApp = mApps.get(position);

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.request_item, null);

                holder = new ViewHolder();
                holder.imgIcon = (ImageView) convertView.findViewById(R.id.imgIcon);
                holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
                holder.chkSelected = (CheckBox) convertView.findViewById(R.id.chkSelected);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.txtName.setText(mApp.getName());
            holder.imgIcon.setImageDrawable(mApp.getImage());
            holder.chkSelected.setChecked(mApp.isSelected());

            return convertView;
        }

        private class ViewHolder {
            public ImageView imgIcon;
            public TextView txtName;
            public CheckBox chkSelected;
        }
    }

}