package com.zero.myplan;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zero.myplan.core.dao.PlanDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zero on 15-12-18.
 */
public class AddPlanActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private static final String TAG = "AddPlanActivity";

    private Spinner mPlanTypeSpinner;
    private EditText mPlanContentEt;
    private TextView mPlanDoneTimeTv;
    private Button mAddPlanBtn;

    private ArrayAdapter mTypeAdapter;

    private int mPlanType;
    private String mDoneTime = "0"; // 天数
    private String mContentStr;

    private Calendar mCalendr;

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_plan);
        findView();
        initViewData();
        setListener();

    }
    private void findView() {
        mPlanContentEt = (EditText) findViewById(R.id.plan_content_et);
        mPlanDoneTimeTv = (TextView) findViewById(R.id.plan_done_time_tv);
        mPlanTypeSpinner = (Spinner) findViewById(R.id.plan_type_spinner);
        mAddPlanBtn = (Button) findViewById(R.id.add_plan_ok_btn);
    }

    private void initViewData() {
        mTypeAdapter = ArrayAdapter.createFromResource(this, R.array.planTypes, android.R.layout.simple_spinner_item);
        mTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPlanTypeSpinner.setAdapter(mTypeAdapter);

        mCalendr = Calendar.getInstance(Locale.CHINA);
        mPlanDoneTimeTv.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(System.currentTimeMillis()));
    }

    private void setListener() {
        mPlanTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPlanType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAddPlanBtn.setOnClickListener(this);
        mPlanDoneTimeTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.add_plan_ok_btn:
                executeAddPlanTask();
                break;
            case R.id.plan_done_time_tv:
                showTimePickDialog();
                break;
        }

    }

    private void showTimePickDialog(){
        mCalendr.setTime(new Date());
        mYear = mCalendr.get(Calendar.YEAR);
        mMonth = mCalendr.get(Calendar.MONTH);
        mDay = mCalendr.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dlg = new DatePickerDialog(this, this, mYear, mMonth, mDay);
        dlg.show();
    }

    private void executeAddPlanTask() {
        mContentStr =  mPlanContentEt.getText().toString();
        if (mContentStr.equals("")) {
            Toast.makeText(this, "内容不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        new AddPlanTask().execute();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDoneTime = Utils.getDaysByTwoDate(mYear, mMonth+1, mDay, year, monthOfYear+1, dayOfMonth) + "";
        mPlanDoneTimeTv.setText(year+ "-" + (monthOfYear+1) + "-" + dayOfMonth);
    }

    class AddPlanTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String createTime = System.currentTimeMillis() + "";
            String lastUpdateTime = createTime;

            PlanDao dao = new PlanDao(getApplicationContext());
            dao.insertPlan(createTime, lastUpdateTime, mDoneTime, mPlanType + "", mContentStr);
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Toast.makeText(AddPlanActivity.this, "创建完成！", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
    }
}
