package com.jahirfiquitiva.paperboard.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jahirfiquitiva.dashboardsample.R;
import com.pkmmte.applylauncher.PkApplyLauncher;

/**
 * Created by Jahir on 28/02/2015.
 */
public class Apply extends Fragment {

    private static final String MARKET_URI = "market://details?id=";
    private static final String MARKET_URL = "https://play.google.com/store/apps/details?id=";
    private Context context;

    public static Fragment newInstance(Context context) {
        Apply f = new Apply();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_apply, null);

        context = getActivity();

        LinearLayout layout;
        LinearLayout layout1;
        LinearLayout layout2;
        LinearLayout layout3;
        LinearLayout layout4;
        LinearLayout layout5;
        LinearLayout layout6;
        LinearLayout layout7;
        LinearLayout layout8;
        LinearLayout layout9;
        LinearLayout layout10;

        layout = (LinearLayout) root.findViewById(R.id.laction);
        layout1 = (LinearLayout) root.findViewById(R.id.ladw);
        layout2 = (LinearLayout) root.findViewById(R.id.lapex);
        layout3 = (LinearLayout) root.findViewById(R.id.latom);
        layout4 = (LinearLayout) root.findViewById(R.id.laviate);
        layout5 = (LinearLayout) root.findViewById(R.id.lgo);
        layout6 = (LinearLayout) root.findViewById(R.id.lholo);
        layout7 = (LinearLayout) root.findViewById(R.id.linspire);
        layout8 = (LinearLayout) root.findViewById(R.id.lnext);
        layout9 = (LinearLayout) root.findViewById(R.id.lnova);
        layout10 = (LinearLayout) root.findViewById(R.id.lsmart);

        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherAction, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getActionLauncher().getName();
                    String lPackage = PkApplyLauncher.getActionLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherAdw, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getAdwLauncher().getName();
                    String lPackage = PkApplyLauncher.getAdwLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherApex, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getApexLauncher().getName();
                    String lPackage = PkApplyLauncher.getApexLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherAtom, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getAtomLauncher().getName();
                    String lPackage = PkApplyLauncher.getAtomLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherAviate, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getAviateLauncher().getName();
                    String lPackage = PkApplyLauncher.getAviateLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherGo, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getGoLauncher().getName();
                    String lPackage = PkApplyLauncher.getGoLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherHolo, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getHoloLauncher().getName();
                    String lPackage = PkApplyLauncher.getHoloLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherInspire, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getInspireLauncher().getName();
                    String lPackage = PkApplyLauncher.getInspireLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherNext, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getNextLauncher().getName();
                    String lPackage = PkApplyLauncher.getNextLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherNova, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getNovaLauncher().getName();
                    String lPackage = PkApplyLauncher.getNovaLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        layout10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean result = PkApplyLauncher.applyLauncher(PkApplyLauncher.launcherSmart, (Activity) context, context.getString(R.string.package_name));

                if (!result) {
                    String launcherName = PkApplyLauncher.getSmartLauncher().getName();
                    String lPackage = PkApplyLauncher.getSmartLauncher().getPackage();
                    LauncherNotInstalledDialog(launcherName, lPackage);
                }
            }
        });

        return root;
    }

    private void LauncherNotInstalledDialog(String launcher, final String launcherPackage) {
        String content = launcher + getResources().getString(R.string.lni_content);
        new MaterialDialog.Builder(context)
                .title(launcher)
                .content(content)
                .positiveText(R.string.lni_yes)
                .negativeText(R.string.lni_no)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(MARKET_URL + launcherPackage));
                        startActivity(intent);
                    }
                })
                .show();
    }

}
