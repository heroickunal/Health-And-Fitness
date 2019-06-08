package azzukunj.cottonuniversity.healthandfitnesskunjazzu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

public class Main2Activity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "WORKOUT");
        adapter.addFragment(new Tab2Fragment(), "ACTIVITY");
        adapter.addFragment(new Tab3Fragment(), "WATER");
        adapter.addFragment(new DetailsOfUser(), "USER");

        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {

        return;
    }
}