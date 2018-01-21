package fr.ensicaen.present.present.view.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.dashboard.DashboardPresenter;
import fr.ensicaen.present.present.presenter.dashboard.IDashboardPresenter;
import fr.ensicaen.present.present.session.SessionManager;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.imagetools.RoundImage;
import fr.ensicaen.present.present.view.choosecalltype.ChooseCallTypeActivity;
import fr.ensicaen.present.present.view.choosepreviouscall.ChoosePreviousCallActivity;
import fr.ensicaen.present.present.view.entercode.EnterCodeActivity;

public class DashboardActivity extends Activity implements IDashboardView {

    private Button _launchCallButton;
    private Button _answerCallButton;
    private IDashboardPresenter _presenter;
    private Button _reviewCallButton;
    private ViewGroup _loadingAnimation;

    private static String TAG = "MainActivity";

    private float[] yData = {25.3f, 10.6f};
    private String[] xData = {"Mitch", "Jessica"};
    PieChart pieChart;
    private ImageView _profilePic;
    private TextView _profileName;

    protected HorizontalBarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new DashboardPresenter(this);

        setContentView(R.layout.activity_dashboard);

        _launchCallButton = findViewById(R.id.launchCall);
        setLaunchCallAction();

        _answerCallButton = findViewById(R.id.answerCall);
        setAnswerCallAction();

        _reviewCallButton = findViewById(R.id.review_call_button);
        setReviewCallsAction();


        _loadingAnimation = findViewById(R.id.loading_animation);

        //@TODO load pic with user data
        _profilePic = findViewById(R.id.profile_pic);
        _profilePic.setImageDrawable(
                new RoundImage(
                        BitmapFactory.decodeResource(getResources(), R.drawable.barack_obama_test)
                )
        );
        _profileName = findViewById(R.id.profile_name);

        setPieChart();
        setHistogram();


    }

    private void setPieChart(){
        pieChart = findViewById(R.id.idPieChart);

        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        // entry label styling
        pieChart.setEntryLabelColor(Color.WHITE);

        addDataSet();
    }

    private void setHistogram(){

        mChart = (HorizontalBarChart) findViewById(R.id.chart1);
        // mChart.setHighlightEnabled(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawValueAboveBar(true);


        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        mChart.setDrawGridBackground(false);

        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);

        YAxis yl = mChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);

        YAxis yr = mChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinValue(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        setData(3, 50);
        mChart.setFitBars(true);
        mChart.animateY(2500);


        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }



    private void setLaunchCallAction() {
        _launchCallButton.setOnClickListener(v -> _presenter.onLaunchCallClick());
    }

    private void setAnswerCallAction() {
        _answerCallButton.setOnClickListener(v -> _presenter.onAnswerCallClick());
    }

    private void setReviewCallsAction() {
        _reviewCallButton.setOnClickListener(v -> _presenter.onReviewOldCallsClick());

    }

    public void goToGenerateCode() {
        Intent intent = new Intent(DashboardActivity.this, ChooseCallTypeActivity.class);
        startActivity(intent);
    }

    public void goToEnterCode() {
        Intent intent = new Intent(DashboardActivity.this, EnterCodeActivity.class);
        startActivity(intent);
    }

    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i = 0; i < yData.length; i++) {
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i = 1; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Employee Sales");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        //colors.add(Color.RED);
        //colors.add(Color.GREEN);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        pieDataSet.setColors(colors);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();



    }


    private void setData(int count, float range) {


        float barWidth = 9f;
        float spaceForBar = 10f;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        ArrayList<Integer> colors = new ArrayList<>();
        //colors.add(Color.RED);
        //colors.add(Color.GREEN);

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * range);
            yVals1.add(new BarEntry(i * spaceForBar, val,
                    getResources().getDrawable(R.drawable.star)));
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "DataSet 1");


            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mChart.setData(data);
        }
    }

    @Override
    public void goToReviewCall() {
        Intent intent = new Intent(DashboardActivity.this, ChoosePreviousCallActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoadingAnimation() { _loadingAnimation.setVisibility(View.VISIBLE); }

    @Override
    public void hideLoadingAnimation() {
        _loadingAnimation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showToast(String message, int toastDuration) {
        Toast.makeText(this, message, toastDuration).show();
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }

}

