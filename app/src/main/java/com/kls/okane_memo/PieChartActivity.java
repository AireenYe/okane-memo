package com.kls.okane_memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.kls.okane_memo.db.Injection;
import com.kls.okane_memo.db.Record;
import com.kls.okane_memo.util.RecordViewModel;
import com.kls.okane_memo.util.ViewModelFactory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends AppCompatActivity implements
        View.OnClickListener,
        DatePickerDialog.OnDateSetListener{

    private int year, month, kind;
    private TextView dateTextView;
    private PieChartView pieChart;
    private PieChartData pieData;

    private List<Record> recordList;    // 存放记录数据
    private RecordViewModel recordViewModel;
    private ViewModelFactory viewModelFactory;
    private final CompositeDisposable disposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        init();
    }

    private void init(){
        // 设置日期显示选择
        dateTextView = findViewById(R.id.pie_chart_datePicker);
        dateTextView.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
//        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String todayDate = String.format("%d年%d月", year, month);
        dateTextView.setText(todayDate);

        //

        // 设置饼图
        kind = -1;
        pieChart=(PieChartView)findViewById(R.id.pie_chart);
        // 饼图是否可旋转
        pieChart.setChartRotationEnabled(true);



        recordList = new ArrayList<>();
        viewModelFactory = Injection.provideViewModelFactory(this);
        recordViewModel = new ViewModelProvider(this, viewModelFactory).get(RecordViewModel.class);
        recordViewModel.getRecordByMonth(year, month)    // 获取某月数据
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Record>>() {
                    @Override
                    public void accept(List<Record> records) throws Exception {
                        recordList = records;
                        setData(-1);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        setData(-1);

        pieData.setHasLabels(true);
        // 是否点击饼模块才显示文本（默认为false,为true时，setHasLabels(true)无效）
        pieData.setHasLabelsOnlyForSelected(false);
        // 文本内容是否显示在饼图外侧(默认为false)
        pieData.setHasLabelsOutside(true);
        // 文本字体大小
        pieData.setValueLabelTextSize(12);
        // 是否有中心圆
        pieData.setHasCenterCircle(true);
    }

    private void setData(int kind){
        HashMap<String, Double> mp;
        mp = new HashMap<>();
        Double sum = 0.0;
        // 格式化数字，显示为保留一位小数
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(1);
        // 计算每种类型的总金额
        for(Record record : recordList)
        {
            String type = record.getTypename();
            Double money = record.getMoney();

            if(record.getKind() == kind)
            {
                sum += money;
                if(mp.get(type) != null) {
                    Double newMoney = mp.get(type) + money;
                    mp.put(type, newMoney);
                }
                else
                    mp.put(type, money);
            }
        }

        List<SliceValue> values = new ArrayList<SliceValue>();
        SliceValue sliceValue = null;

        // 饼图颜色
        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.pie1);
        colorList.add(R.color.pie2);
        colorList.add(R.color.pie3);
        colorList.add(R.color.pie4);
        int cnt = 0;

        // 使用entrySet的迭代器遍历哈希表
        Iterator iter = mp.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String,Double> entry = (Map.Entry<String,Double>)iter.next();
            String type = entry.getKey();
            String money = numberFormat.format(entry.getValue() / sum * 100);
            //创建一个新的值
            sliceValue = new SliceValue();
            //设置每个扇形区域的值，float型
            sliceValue.setValue(Float.parseFloat(money));
            //设置每个扇形区域的颜色
            int color = colorList.get(cnt % colorList.size());
            cnt++;
            if(cnt == mp.size() && (cnt - 1) % colorList.size() == 0)
                color = colorList.get(cnt % colorList.size());  // 避免相同颜色相邻
            sliceValue.setColor(color);
            // 设置每个扇形区域的Lable，不设置的话，默认显示数值
            sliceValue.setLabel(money);

            values.add(sliceValue);
        }

        pieData = new PieChartData(values);
        pieChart.setPieChartData(pieData);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month + 1;
//        this.dayOfMonth = dayOfMonth;
        String date = String.format("%d年%d月", year, this.month);
        dateTextView.setText(date);
        Log.d("图表日期",date);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_pie_chart:
                intent = new Intent(PieChartActivity.this, MainActivity.class);
                startActivity(intent);
//                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;
        }
    }
}