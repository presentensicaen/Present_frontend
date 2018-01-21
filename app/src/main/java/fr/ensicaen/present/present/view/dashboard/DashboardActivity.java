package fr.ensicaen.present.present.view.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.dashboard.DashboardPresenter;
import fr.ensicaen.present.present.presenter.dashboard.IDashboardPresenter;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.choosecalltype.ChooseCallTypeActivity;
import fr.ensicaen.present.present.view.choosepreviouscall.ChoosePreviousCallActivity;
import fr.ensicaen.present.present.view.entercode.EnterCodeActivity;
import fr.ensicaen.present.present.view.reviewcall.ReviewCallActivity;

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

        pieChart = (PieChart) findViewById(R.id.idPieChart);

        /*pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleAlpha(0);*/
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
