package sl.calculator.utils;

import android.content.Context;
import android.widget.Button;

import sl.calculator.R;
import sl.calculator.utils.base.ConstantData;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Utils {
    /**
     * 获得系统颜色
     *
     * @return
     */
    public static int getThemeColor() {
        int style = PreferenceUtils.getInt(ConstantData.FLAG_THEME_STYLE, R.style.AppThemeBlue);
        if (style == R.style.AppThemeBlue) {
            return R.color.DodgerBlue;
        } else if (style == R.style.AppThemeGreen) {
            return R.color.SpringGreen;
        } else if (style == R.style.AppThemeOrange) {
            return R.color.DarkOrange;
        }
        return R.color.DodgerBlue; //默认蓝色
    }


}
