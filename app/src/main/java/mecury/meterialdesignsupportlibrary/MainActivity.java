package mecury.meterialdesignsupportlibrary;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationview_layout);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        switchFragment();

        navigationViewListener();
    }

    //切换Fragment
    private void switchFragment() {
        TabLayout tabLayout;
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();

        Fragment mainFragment = new MainFragment();
        titles.add("首页");
        fragments.add(mainFragment);
        Fragment textFragment = new CoordinationFragment();
        titles.add("text");
        fragments.add(textFragment);
        Fragment aboutFragment = new AboutFragment();
        titles.add("关于");
        fragments.add(aboutFragment);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        FragmentAdapter mAdapter = new FragmentAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    private void navigationViewListener(){
        NavigationView mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_main:
                        Toast.makeText(MainActivity.this, "点击了首页", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        Toast.makeText(MainActivity.this, "点击了关于", Toast.LENGTH_SHORT).show();
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }
}
