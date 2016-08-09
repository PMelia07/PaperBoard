package com.jahirfiquitiva.paperboard.fragments;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jahirfiquitiva.dashboardsample.R;

/**
 * Created by Jahir on 28/02/2015.
 */
public class Apply extends Fragment {

    private static final String MARKET_URI = "market://details?id=";
    private static final String MARKET_URL = "https://play.google.com/store/apps/details?id=";
    String adwPackage, adwexPackage;
    private Context context;

    private static final int GO_LAUNCHER = 1;
    private static final int HOLO_LAUNCHER = 2;
    private static final int APEX_LAUNCHER = 3;
    private static final int NOVA_LAUNCHER = 4;
    private static final int SMART_LAUNCHER = 5;
    private static final int INSPIRE_LAUNCHER = 6;
    private static final int ACTION_LAUNCHER = 7;
    private static final int AVIATE_LAUNCHER = 8;
    private static final int NEXT_LAUNCHER = 9;
    private static final int ATOM_LAUNCHER = 10;
    private static final int NINE_LAUNCHER = 11;
    private static final int KK_LAUNCHER = 12;
    private static final int LUCID_LAUNCHER = 13;
    private static final int SOLO_LAUNCHER = 14;
    private static final int TSF_LAUNCHER = 15;

    ListView launcherslist;

    String[] launchers;
    int[] icons = {
            R.drawable.l_action,
            R.drawable.l_adw,
            R.drawable.l_apex,
            R.drawable.l_atom,
            R.drawable.l_aviate,
            R.drawable.l_go,
            R.drawable.l_holo,
            R.drawable.l_inspire,
            R.drawable.l_kk,
            R.drawable.l_lucid,
            R.drawable.l_next,
            R.drawable.l_nine,
            R.drawable.l_nova,
            R.drawable.l_smart,
            R.drawable.l_solo,
            R.drawable.l_tsf
    };

    public static Fragment newInstance(Context context) {
        Apply f = new Apply();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.section_apply, null);

        context = getActivity();

        ActionBar toolbar = ((ActionBarActivity) context).getSupportActionBar();
        toolbar.setTitle(R.string.section_three);

        Resources res = getResources();
        launchers = res.getStringArray(R.array.LaunchersNames);

        launcherslist = (ListView) root.findViewById(R.id.launcherslist);

        LaunchersAdapter adapter = new LaunchersAdapter(context, launchers, icons);

        launcherslist.setAdapter(adapter);

        launcherslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        actionClick();
                        break;
                    case 1:
                        adwClick();
                        break;
                    case 2:
                        apexClick();
                        break;
                    case 3:
                        atomClick();
                        break;
                    case 4:
                        aviateClick();
                        break;
                    case 5:
                        goClick();
                        break;
                    case 6:
                        holoClick();
                        break;
                    case 7:
                        inspireClick();
                        break;
                    case 8:
                        kkClick();
                        break;
                    case 9:
                        lucidClick();
                        break;
                    case 10:
                        nextClick();
                        break;
                    case 11:
                        nineClick();
                        break;
                    case 12:
                        novaClick();
                        break;
                    case 13:
                        smartClick();
                        break;
                    case 14:
                        soloClick();
                        break;
                    case 15:
                        tsfClick();
                        break;

                }
            }
        });


        return root;
    }

    private void LauncherNotInstalledDialog() {
        new MaterialDialog.Builder(context)
                .title("launcher")
                .content("content")
                .positiveText(R.string.lni_yes)
                .negativeText(R.string.lni_no)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(MARKET_URL + "hola"));
                        startActivity(intent);
                    }
                })
                .show();
    }

    class LaunchersAdapter extends ArrayAdapter<String> {

        int[] icons;
        String[] launchers;

        LaunchersAdapter(Context context, String[] launchers, int[] icons) {
            super(context, R.layout.launcher_item, R.id.launchername, launchers);
            this.icons = icons;
            this.launchers = launchers;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item = convertView;
            LauncherHolder holder = null;

            if (item == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                item = inflater.inflate(R.layout.launcher_item, parent, false);
                holder = new LauncherHolder(item);
                item.setTag(holder);
            } else {
                holder = (LauncherHolder) item.getTag();

            }

            holder.icon.setImageResource(icons[position]);
            holder.launchername.setText(launchers[position]);

            switch (position) {
                case 0:
                    if (LauncherIsInstalled("com.actionlauncher.playstore")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 1:
                    if (LauncherIsInstalled("org.adw.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else if (LauncherIsInstalled("org.adwfreak.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 2:
                    if (LauncherIsInstalled("com.anddoes.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 3:
                    if (LauncherIsInstalled("com.dlto.atom.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 4:
                    if (LauncherIsInstalled("com.tul.aviate")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 5:
                    if (LauncherIsInstalled("com.gau.go.launcherex")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 6:
                    if (LauncherIsInstalled("com.mobint.hololauncher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 7:
                    if (LauncherIsInstalled("com.bam.android.inspirelauncher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 8:
                    if (LauncherIsInstalled("com.kk.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 9:
                    if (LauncherIsInstalled("com.powerpoint45.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 10:
                    if (LauncherIsInstalled("com.gtp.nextlauncher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 11:
                    if (LauncherIsInstalled("com.gidappsinc.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 12:
                    if (LauncherIsInstalled("com.teslacoilsw.launcher")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 13:
                    if (LauncherIsInstalled("ginlemon.flowerfree")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 14:
                    if (LauncherIsInstalled("home.solo.launcher.free")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
                case 15:
                    if (LauncherIsInstalled("com.tsf.shell")) {
                        holder.isInstalled.setText(R.string.installed);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.green));
                    } else {
                        holder.isInstalled.setText(R.string.noninstalled);
                        holder.isInstalled.setTextColor(getResources().getColor(R.color.red));
                    }
                    break;
            }

            return item;

        }
    }

    class LauncherHolder {
        ImageView icon;
        TextView launchername;
        TextView isInstalled;

        LauncherHolder(View v) {
            icon = (ImageView) v.findViewById(R.id.launchericon);
            launchername = (TextView) v.findViewById(R.id.launchername);
            isInstalled = (TextView) v.findViewById(R.id.launcherinstalled);
        }

    }

    private boolean LauncherIsInstalled(String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;

        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }

        return installed;
    }

    public void adwClick() {

        Intent intent = new Intent("org.adw.launcher.SET_THEME");
        intent.putExtra("org.adw.launcher.theme.NAME", context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {

            String adwDialogTitle = "Adw Launcher" + getResources().getString(R.string.lni_title);
            String adwDialogContent = "Adw Launcher" + getResources().getString(R.string.lni_content);
            adwPackage = "org.adw.launcher";
            adwexPackage = "org.adwfreak.launcher";

            new MaterialDialog.Builder(context)
                    .title(adwDialogTitle)
                    .content(adwDialogContent)
                    .positiveText("ADW")
                    .neutralText(R.string.lni_no)
                    .negativeText("ADW EX")
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(MARKET_URL + adwPackage));
                            startActivity(intent);
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(MARKET_URL + adwexPackage));
                            startActivity(intent);
                        }
                    })
                    .show();

        }

    }

    public void goClick() {

        Intent intent = context.getPackageManager().getLaunchIntentForPackage(
                "com.gau.go.launcherex");
        if (intent != null) {
            Intent go = new Intent(
                    "com.gau.go.launcherex.MyThemes.mythemeaction");
            go.putExtra("type", 1);
            go.putExtra("pkgname", context.getPackageName());
            context.sendBroadcast(go);
            Toast.makeText(context, "Go Theme Applied!", Toast.LENGTH_SHORT)
                    .show();
            startActivity(intent);
        } else {

            goToPlay(GO_LAUNCHER);

        }

    }


    public void holoClick() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.mobint.hololauncher",
                "com.mobint.hololauncher.SettingsActivity"));

        try {
            startActivity(intent);

        } catch (ActivityNotFoundException e) {

            goToPlay(HOLO_LAUNCHER);

        }

    }

    public void apexClick() {

        Intent intent = new Intent("com.anddoes.launcher.SET_THEME");
        intent.putExtra("com.anddoes.launcher.THEME_PACKAGE_NAME",
                context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);

        } catch (ActivityNotFoundException e) {

            goToPlay(APEX_LAUNCHER);

        }

    }


    public void novaClick() {

        Intent intent = new Intent("com.teslacoilsw.launcher.APPLY_ICON_THEME");
        intent.setPackage("com.teslacoilsw.launcher");
        intent.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_TYPE", "GO");
        intent.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE",
                context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            goToPlay(NOVA_LAUNCHER);
        }

    }

    public void smartClick() {

        Intent smart = this.context.getPackageManager().getLaunchIntentForPackage("ginlemon.flowerfree");
        Intent smartpro = this.context.getPackageManager().getLaunchIntentForPackage("ginlemon.flowerpro");
        if (smart != null) {
            Intent smartlauncherIntent = new Intent("ginlemon.smartlauncher.setGSLTHEME");
            smartlauncherIntent.putExtra("package", this.context.getPackageName());
            startActivity(smartlauncherIntent);
        } else if (smartpro != null) {
            Intent smartlauncherIntent = new Intent("ginlemon.smartlauncher.setGSLTHEME");
            smartlauncherIntent.putExtra("package", this.context.getPackageName());
            startActivity(smartlauncherIntent);
        } else {

            goToPlay(SMART_LAUNCHER);

        }

    }


    public void aviateClick() {

        Intent aviate = new Intent("com.tul.aviate.SET_THEME");
        aviate.setPackage("com.tul.aviate");
        aviate.putExtra("THEME_PACKAGE", context.getPackageName());
        aviate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(aviate);
        } catch (ActivityNotFoundException e) {

            goToPlay(AVIATE_LAUNCHER);

        }

    }

    public void atomClick() {

        Intent atom = new Intent("com.dlto.atom.launcher.intent.action.ACTION_VIEW_THEME_SETTINGS");
        atom.setPackage("com.dlto.atom.launcher");
        atom.putExtra("packageName", context.getPackageName());
        atom.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(atom);
        } catch (ActivityNotFoundException e) {

            goToPlay(ATOM_LAUNCHER);

        }

    }

    public void inspireClick() {

        Intent inspireMain = context.getPackageManager().getLaunchIntentForPackage("com.bam.android.inspirelauncher");

        if (inspireMain != null) {
            Intent inspire = new Intent("com.bam.android.inspirelauncher.action.ACTION_SET_THEME");
            inspire.putExtra("theme_name", context.getPackageName());
            context.sendBroadcast(inspire);
            startActivity(inspireMain);
        } else {

            goToPlay(INSPIRE_LAUNCHER);

        }

    }

    public void actionClick() {

        Intent action = context.getPackageManager().getLaunchIntentForPackage("com.actionlauncher.playstore");

        if (action != null) {
            action.putExtra("apply_icon_pack", context.getPackageName());
            startActivity(action);
        } else {

            goToPlay(ACTION_LAUNCHER);

        }

    }


    public void nextClick() {

        Intent nextApply = context.getPackageManager().getLaunchIntentForPackage("com.gtp.nextlauncher");

        if (nextApply != null) {
            Intent next = new Intent("com.gau.go.launcherex.MyThemes.mythemeaction");
            next.putExtra("type", 1);
            next.putExtra("pkgname", context.getPackageName());
            context.sendBroadcast(next);
            startActivity(nextApply);
        } else {

            goToPlay(NEXT_LAUNCHER);

        }

    }


    public void nineClick() {

        Intent nineApply = context.getPackageManager().getLaunchIntentForPackage("com.gidappsinc.launcher");

        if (nineApply != null) {
            Intent nine = new Intent("com.gridappsinc.launcher.action.THEME");
            nine.putExtra("iconpkg", context.getPackageName());
            nine.putExtra("launch", true);
            context.sendBroadcast(nine);
            //Toast.makeText(context, "Click on OK to apply the Icon Pack", Toast.LENGTH_SHORT).show();
        } else {

            goToPlay(NINE_LAUNCHER);

        }

    }

    public void kkClick() {

        Intent kkApply = context.getPackageManager().getLaunchIntentForPackage("com.kk.launcher");

        if (kkApply != null) {
            Intent kk = new Intent("com.gridappsinc.launcher.action.THEME");
            kk.putExtra("com.kk.launcher.theme.EXTRA_NAME", "theme_name");
            kk.putExtra("com.kk.launcher.theme.EXTRA_PKG", context.getPackageName());
            context.sendBroadcast(kk);
            startActivity(kkApply);
        } else {

            goToPlay(KK_LAUNCHER);
        }
    }

    public void lucidClick() {

        Intent lucidApply = context.getPackageManager().getLaunchIntentForPackage("com.powerpoint45.launcher");

        if (lucidApply != null) {
            Intent lucid = new Intent("com.powerpoint45.action.APPLY_THEME");
            lucid.putExtra("icontheme", context.getPackageName());
            context.sendBroadcast(lucid);
            startActivity(lucidApply);
        } else {

            goToPlay(LUCID_LAUNCHER);
        }
    }

    public void soloClick() {


        Intent soloApply = context.getPackageManager().getLaunchIntentForPackage("home.solo.launcher.free");

        if (soloApply != null) {
            Intent solo = new Intent("home.solo.launcher.free.APPLY_THEME");
            solo.putExtra("EXTRA_PACKAGENAME", context.getPackageName());
            solo.putExtra("EXTRA_THEMENAME", getString(R.string.app_name));
            context.sendBroadcast(solo);
            startActivity(soloApply);
        } else {

            goToPlay(SOLO_LAUNCHER);
        }
    }

    public void tsfClick() {

        Intent tsfApply = context.getPackageManager().getLaunchIntentForPackage("com.tsf.shell");

        if (tsfApply != null) {
            Intent tsf = new Intent("android.intent.action.MAIN");
            tsf.setComponent(new ComponentName("com.tsf.shell", "com.tsf.shell.ShellActivity"));
            context.sendBroadcast(tsf);
            startActivity(tsfApply);
        } else {

            goToPlay(TSF_LAUNCHER);
        }
    }

    @SuppressLint("DefaultLocale")
    public void goToPlay(int launcher) {

        if (launcher == 0 || launcher > 15)
            return;

        String packageName = "";
        String launcherName = "";

        switch (launcher) {

            case GO_LAUNCHER:
                launcherName = "Go Launcher";
                packageName = "com.gau.go.launcherex";
                break;
            case HOLO_LAUNCHER:
                launcherName = "Holo Launcher";
                packageName = "com.mobint.hololauncher";
                break;
            case APEX_LAUNCHER:
                launcherName = "Apex Launcher";
                packageName = "com.anddoes.launcher";
                break;
            case NOVA_LAUNCHER:
                launcherName = "Nova Launcher";
                packageName = "com.teslacoilsw.launcher";
                break;
            case SMART_LAUNCHER:
                launcherName = "Smart Launcher";
                packageName = "ginlemon.flowerfree";
                break;
            case INSPIRE_LAUNCHER:
                launcherName = "Inspire Launcher";
                packageName = "com.bam.android.inspirelauncher";
                break;
            case ACTION_LAUNCHER:
                launcherName = "Action Launcher";
                packageName = "com.actionlauncher.playstore";
                break;
            case AVIATE_LAUNCHER:
                launcherName = "Aviate Launcher";
                packageName = "com.tul.aviate";
                break;
            case NEXT_LAUNCHER:
                launcherName = "Next Launcher";
                packageName = "com.gtp.nextlauncher";
                break;
            case ATOM_LAUNCHER:
                launcherName = "Atom Launcher";
                packageName = "com.dlto.atom.launcher";
                break;
            case NINE_LAUNCHER:
                launcherName = "Nine Launcher";
                packageName = "com.gidappsinc.launcher";
                break;
            case KK_LAUNCHER:
                launcherName = "KK Launcher";
                packageName = "com.kk.launcher";
                break;
            case LUCID_LAUNCHER:
                launcherName = "Lucid Launcher";
                packageName = "com.powerpoint45.launcher";
                break;
            case SOLO_LAUNCHER:
                launcherName = "Solo Launcher";
                packageName = "home.solo.launcher.free";
                break;
            case TSF_LAUNCHER:
                launcherName = "TSF Launcher";
                packageName = "com.tsf.shell";
                break;


        }

        final String launcherPackage = packageName;
        String dialogTitle = launcherName + getResources().getString(R.string.lni_title);
        String dialogContent = launcherName + getResources().getString(R.string.lni_content);

        new MaterialDialog.Builder(context)
                .title(dialogTitle)
                .content(dialogContent)
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
