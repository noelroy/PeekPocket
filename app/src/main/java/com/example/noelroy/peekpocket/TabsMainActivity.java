package com.example.noelroy.peekpocket;

/**
 * Created by Noel Roy on 15-10-2015.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;

public class TabsMainActivity extends AppCompatActivity implements android.support.v7.app.ActionBar.TabListener {

    private ViewPager tabsviewPager;
    private ActionBar mActionBar;
    private Tabsadapter mTabsAdapter;

    String type;

    public String gettype(Context context){
        SharedPreferences settings = context.getSharedPreferences("type", MODE_PRIVATE);
        return settings.getString("gettype","XXX");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);

        Intent intent = getIntent();
        type = intent.getExtras().getString("type");

        SharedPreferences settings = getSharedPreferences("type", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("gettype", type);
        editor.commit();

        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);

        mTabsAdapter = new Tabsadapter(getSupportFragmentManager());

        tabsviewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        final Tab friendstab = getSupportActionBar().newTab().setText("View By Date").setTabListener(this);
        Tab publicprofiletab = getSupportActionBar().newTab().setText("View By Month").setTabListener(this);
        Tab communitytab = getSupportActionBar().newTab().setText("View All").setTabListener(this);

        getSupportActionBar().addTab(friendstab);
        getSupportActionBar().addTab(publicprofiletab);
        getSupportActionBar().addTab(communitytab);

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.drawable.ic_add_white_18dp);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setTheme(R.style.AppTheme)
                .build();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TabsMainActivity.this, AddActivity.class);
                intent.putExtra("type", "Expense");
                startActivity(intent);
            }
        });

        //This helps in providing swiping effect for v7 compat library
        tabsviewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        Toast.makeText(getBaseContext(), type, Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(Tab selectedtab, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        tabsviewPager.setCurrentItem(selectedtab.getPosition()); //update tab position on tap
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}