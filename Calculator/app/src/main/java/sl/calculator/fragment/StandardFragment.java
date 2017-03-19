package sl.calculator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sl.calculator.R;
import sl.calculator.utils.DensityUtil;
import sl.calculator.utils.Utils;
import sl.calculator.utils.UtilsLog;
import sl.calculator.utils.math.Calculator;


/**
 * Created by Administrator on 2017/3/11.
 */
public class StandardFragment extends Fragment {
    private Context ctx;
    private View standardView;

    private TextView tvShow;

    private List<Button> btnList;

    /**
     * 控件声明
     */
    private Button btnClear;
    private Button btnBack;
    private Button btnDivide;
    private Button btnMultiply;
    private Button btnSeven;
    private Button btnEight;
    private Button btnNine;
    private Button btnReduce;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private Button btnAdd;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnDot;
    private Button btnLeft;
    private Button btnZero;
    private Button btnRight;
    private Button btnEqual;

    private StringBuffer showSB = new StringBuffer();
    private StringBuffer calculatorSB = new StringBuffer();
    private String showStr = "";
    private String calculatorStr = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (standardView == null) {
            standardView = inflater.inflate(R.layout.fragment_standard, container, false);
        }
        ctx = standardView.getContext();

        initView();
        return standardView;
    }

    private void initView() {
        btnList = new ArrayList<>();

        tvShow = (TextView) standardView.findViewById(R.id.tvShow);

        btnClear = (Button) standardView.findViewById(R.id.btnClear);
        btnBack = (Button) standardView.findViewById(R.id.btnBack);
        btnDivide = (Button) standardView.findViewById(R.id.btnDivide);
        btnMultiply = (Button) standardView.findViewById(R.id.btnMultiply);
        btnSeven = (Button) standardView.findViewById(R.id.btnSeven);
        btnEight = (Button) standardView.findViewById(R.id.btnEight);
        btnNine = (Button) standardView.findViewById(R.id.btnNine);
        btnReduce = (Button) standardView.findViewById(R.id.btnReduce);
        btnFour = (Button) standardView.findViewById(R.id.btnFour);
        btnFive = (Button) standardView.findViewById(R.id.btnFive);
        btnSix = (Button) standardView.findViewById(R.id.btnSix);
        btnAdd = (Button) standardView.findViewById(R.id.btnAdd);
        btnOne = (Button) standardView.findViewById(R.id.btnOne);
        btnTwo = (Button) standardView.findViewById(R.id.btnTwo);
        btnThree = (Button) standardView.findViewById(R.id.btnThree);
        btnDot = (Button) standardView.findViewById(R.id.btnDot);
        btnLeft = (Button) standardView.findViewById(R.id.btnLeft);
        btnZero = (Button) standardView.findViewById(R.id.btnZero);
        btnRight = (Button) standardView.findViewById(R.id.btnRight);
        btnEqual = (Button) standardView.findViewById(R.id.btnEqual);

        btnList.add(btnClear);
        btnList.add(btnBack);
        btnList.add(btnDivide);
        btnList.add(btnMultiply);
        btnList.add(btnSeven);
        btnList.add(btnEight);
        btnList.add(btnNine);
        btnList.add(btnReduce);
        btnList.add(btnFour);
        btnList.add(btnFive);
        btnList.add(btnSix);
        btnList.add(btnAdd);
        btnList.add(btnOne);
        btnList.add(btnTwo);
        btnList.add(btnThree);
        btnList.add(btnDot);
        btnList.add(btnLeft);
        btnList.add(btnZero);
        btnList.add(btnRight);
        btnList.add(btnEqual);

        for (int i = 0; i < btnList.size(); i++) {
            updateTheme(btnList.get(i));
        }


    }

    private void updateTheme(final Button btn) {
        int textSizePX = DensityUtil.dip2px(ctx, 10);
        btn.setTextSize(textSizePX);
        btn.setTextColor(ContextCompat.getColor(ctx, R.color.white));
        btn.setBackgroundColor(ContextCompat.getColor(ctx, Utils.getThemeColor()));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) btn.getLayoutParams();
        layoutParams.setMargins(5, 5, 5, 5);
        btn.setLayoutParams(layoutParams);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = btn.getText().toString();
//                calculatorSB.append(str);
//                tvShow.setText(calculatorSB.toString());
                specialOperate(ctx, btn, str);
            }
        });
    }

    public void specialOperate(Context ctx, Button btn, String str) {
        if (str.equals(ctx.getString(R.string.sign_equal))) { //等号
            double result = Calculator.conversion(calculatorSB.toString());
            calculatorSB = new StringBuffer();
            calculatorSB.append(result); //计算只存储result
            showSB = showSB.append("\n" + result);

            UtilsLog.logE(UtilsLog.getSte(), showStr);
        } else if (str.equals(ctx.getString(R.string.sign_clear))) {//清除
            calculatorSB = new StringBuffer();
            showSB = new StringBuffer();
        } else if (str.equals(ctx.getString(R.string.sign_back))) {//回退
            if (calculatorSB.length() >= 1) {
                calculatorSB.deleteCharAt(calculatorSB.length() - 1);
                showSB.deleteCharAt(showSB.length() - 1);
            } else {
                calculatorSB = new StringBuffer();
                showSB = new StringBuffer();
            }
        } else if (str.equals(ctx.getString(R.string.sign_add))) {//加
            showSB.append(ctx.getString(R.string.sign_add));
            calculatorSB.append("+");
        } else if (str.equals(ctx.getString(R.string.sign_reduce))) {//减
            showSB.append(ctx.getString(R.string.sign_reduce));
            calculatorSB.append("-");
        } else if (str.equals(ctx.getString(R.string.sign_multiply))) {//乘
            showSB.append(ctx.getString(R.string.sign_multiply));
            calculatorSB.append("*");
        } else if (str.equals(ctx.getString(R.string.sign_divide))) {//除
            showSB.append(ctx.getString(R.string.sign_divide));
            calculatorSB.append("/");
        } else if (str.equals(ctx.getString(R.string.sign_dot))) {//点
            showSB.append(ctx.getString(R.string.sign_dot));
            calculatorSB.append(".");
        } else {
            calculatorSB.append(str);
            showSB.append(str);
        }
        if (showSB.length() >= 1) {
            showStr = showSB.toString();
            tvShow.setText(showStr);
        } else {
            tvShow.setText("0");
        }

    }


}
