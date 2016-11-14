package com.gupengcheng.checksoftinput;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private CheckSoftInputLayout mRootLayout;
    private RelativeLayout mRlInput;
    private EditText mEdtInput;
    private FloatingActionButton mFabBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    private void initViews() {
        mRootLayout = (CheckSoftInputLayout) findViewById(R.id.content_main);
        mRlInput = (RelativeLayout) findViewById(R.id.rl_post_comment);
        mEdtInput = (EditText) findViewById(R.id.edt_comment);
        mFabBtn = (FloatingActionButton) findViewById(R.id.fab);

        mFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInputViewEnabled(true);
            }
        });

        mRootLayout.setOnResizeListener(new CheckSoftInputLayout.OnResizeListener() {
            @Override
            public void onResize(int w, int h, int oldw, int oldh) {
                //如果第一次初始化
                if (oldh == 0) {
                    return;
                }
                //如果用户横竖屏转换
                if (w != oldw) {
                    return;
                }
                if (h < oldh) {
                    //输入法弹出
                } else if (h > oldh) {
                    //输入法关闭
                    setInputViewEnabled(false);
                }
            }
        });
    }

    private void setInputViewEnabled(boolean enabled) {
        if (enabled) {
            mRlInput.setVisibility(View.VISIBLE);
            KeyboardUtil.showSoftInput(MainActivity.this, mEdtInput);
        } else {
            mRlInput.setVisibility(View.GONE);
            KeyboardUtil.hideSoftInput(MainActivity.this);
        }
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
}
