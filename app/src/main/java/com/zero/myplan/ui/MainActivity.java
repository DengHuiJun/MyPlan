package com.zero.myplan.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.zero.myplan.R;
import com.zero.myplan.core.dao.PlanDao;
import com.zero.myplan.core.dao.model.PlanM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private FloatingActionButton mAddFab;
    private RecyclerView mPlanListRv;
    private PlanListAdapter mAdapter;
    private List<PlanM> mPlanList;

    private static final int REQ_CODE_ADD_PLAN = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findView();
        initViewData();
        setListener();
        executeLoadDateTask();
    }

    private void findView() {
        mPlanListRv = (RecyclerView) findViewById(R.id.plan_list_rv);
        mAddFab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initViewData() {
        mPlanList = new ArrayList<>();
        mPlanListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PlanListAdapter(this, mPlanList);
        mPlanListRv.setAdapter(mAdapter);
    }

    private void setListener() {

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPlanActivity.class);
                startActivityForResult(intent, REQ_CODE_ADD_PLAN);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_ADD_PLAN && resultCode == RESULT_OK) {
            executeLoadDateTask();
        }
    }

    private void executeLoadDateTask() {
        new LoadDataTask().execute();
    }

    class LoadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            PlanDao dao = new PlanDao(getApplicationContext());
            mPlanList = dao.getAllPlans();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.setList(mPlanList);
        }
    }
}
