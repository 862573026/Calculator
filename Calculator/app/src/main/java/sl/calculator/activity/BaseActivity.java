package sl.calculator.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import sl.calculator.R;
import sl.calculator.utils.PreferenceUtils;
import sl.calculator.utils.base.ConstantData;

/**
 * Created by Administrator on 2017/3/11.
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceUtils.init(this);

        setTheme(PreferenceUtils.getInt(ConstantData.FLAG_THEME_STYLE, R.style.AppThemeBlue));
    }
}
