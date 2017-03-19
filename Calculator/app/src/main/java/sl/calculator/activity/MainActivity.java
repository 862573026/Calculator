package sl.calculator.activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import sl.calculator.R;
import sl.calculator.fragment.ScientificFragment;
import sl.calculator.fragment.StandardFragment;
import sl.calculator.utils.PreferenceUtils;
import sl.calculator.utils.Utils;
import sl.calculator.utils.base.ConstantData;
import sl.calculator.utils.segment.SegmentedGroup;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private SegmentedGroup segSwitch;
    private RadioButton rdoBtnStandard;
    private RadioButton rdoBtnScientific;
    private LinearLayout llMain;

    private FragmentTransaction ft;

    private StandardFragment standardFragment;
    private ScientificFragment scientificFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        segSwitch = (SegmentedGroup) findViewById(R.id.seg_switch);
        rdoBtnStandard = (RadioButton) findViewById(R.id.rdoBtn_standard);
        rdoBtnScientific = (RadioButton) findViewById(R.id.rdoBtn_scientific);
        llMain = (LinearLayout) findViewById(R.id.ll_main);

        addView();

        segSwitch.setTintColor(ContextCompat.getColor(this, Utils.getThemeColor()));
        String nowSelect = PreferenceUtils.getString(ConstantData.FLAG_CALCULATOR_TYPE, ConstantData.FLAG_CALCULATOR_STANDARD);

        if (nowSelect.equals(ConstantData.FLAG_CALCULATOR_STANDARD)) {
            rdoBtnStandard.setChecked(true);
            switchView(0);
        } else {
            rdoBtnScientific.setChecked(true);
            switchView(1);
        }
        addListener();
    }

    private void addListener() {
        rdoBtnStandard.setOnClickListener(this);
        rdoBtnScientific.setOnClickListener(this);
    }

    private void addView() {
        ft = getSupportFragmentManager().beginTransaction();
        standardFragment = new StandardFragment();
        scientificFragment = new ScientificFragment();

        ft.add(R.id.ll_main,standardFragment,standardFragment.getClass().getSimpleName());
        ft.add(R.id.ll_main,scientificFragment,scientificFragment.getClass().getSimpleName());

        ft.commit();
    }

    private void switchView(int index) {
        ft = getSupportFragmentManager().beginTransaction();

        if (index == 0) {
            ft.show(standardFragment);
            ft.hide(scientificFragment);
        } else {
            ft.show(scientificFragment);
            ft.hide(standardFragment);
        }
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(getString(R.string.blue));
        menu.add(getString(R.string.green));
        menu.add(getString(R.string.orange));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String selectedStyle = item.getTitle().toString();
        if (selectedStyle.equals(getString(R.string.blue))) {
            PreferenceUtils.commitInt(ConstantData.FLAG_THEME_STYLE, R.style.AppThemeBlue);
            getApplication().setTheme(R.style.AppThemeBlue);
        } else if (selectedStyle.equals(getString(R.string.green))) {
            PreferenceUtils.commitInt(ConstantData.FLAG_THEME_STYLE, R.style.AppThemeGreen);
            getApplication().setTheme(R.style.AppThemeGreen);
        } else if (selectedStyle.equals(getString(R.string.orange))) {
            PreferenceUtils.commitInt(ConstantData.FLAG_THEME_STYLE, R.style.AppThemeOrange);
            getApplication().setTheme(R.style.AppThemeOrange);
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rdoBtn_standard:
                PreferenceUtils.commitString(ConstantData.FLAG_CALCULATOR_TYPE, ConstantData.FLAG_CALCULATOR_STANDARD);
                switchView(0);
                break;
            case R.id.rdoBtn_scientific:
                PreferenceUtils.commitString(ConstantData.FLAG_CALCULATOR_TYPE, ConstantData.FLAG_CALCULATOR_SCIENTIFIC);
                switchView(1);
                break;
            default:
                PreferenceUtils.commitString(ConstantData.FLAG_CALCULATOR_TYPE, ConstantData.FLAG_CALCULATOR_STANDARD);
                switchView(0);
                break;
        }
    }
}
