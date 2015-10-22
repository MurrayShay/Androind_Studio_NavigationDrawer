package demo.wp.environmentapp_1;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private int navItem;
    private static final String NAV_ITEM = "navItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        //註冊 drawer 打開關閉事件
        //ActionBarDrawerToggle 已經實做 DrawerLayout.DrawerListener 介面
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (null != savedInstanceState) {
            navItem = savedInstanceState.getInt(NAV_ITEM, R.id.navigation_item_1);
        } else {
            navItem = R.id.navigation_item_1;
        }
        navigateTo(navItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Toast.makeText(MainActivity.this, menuItem.getTitle() + " pressed", Toast.LENGTH_LONG).show();
        menuItem.setChecked(true);
        navigateTo(menuItem.getItemId());
        return true;
    }

    // onPostCreate方法是指onCreate方法彻底执行完毕的回调，onPostResume类似，
    // 这两个方法官方说法是一般不会重写，现在知道的做法也就只有在使用ActionBarDrawerToggle的使用
    // 在onPostCreate需要在屏幕旋转时候等同步下状态，Google官方提供的一些实例就是
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    // onContentChanged()是Activity中的一个回调方法 当Activity的布局改动时，
    // 即setContentView()或者addContentView()方法执行完毕时就会调用该方法，
    // 例如，Activity中各种View的findViewById()方法都可以放到该方法中。
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void navigateTo(int itemId) {
        navItem = itemId;
        setTitle(navigationView.getMenu().findItem(itemId).getTitle());
        drawerLayout.closeDrawers();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM, navItem);
    }

}
