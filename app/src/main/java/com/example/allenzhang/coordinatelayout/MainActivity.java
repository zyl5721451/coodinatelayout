package com.example.allenzhang.coordinatelayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewHolder viewHolder = new ViewHolder(findViewById(R.id.content));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    public static class ViewHolder implements View.OnClickListener{
        public View rootView;
        public Toolbar mToolbar;
        public Button mButton;
        public Button mButton2;
        public Button mButton3;
        public Button mButton4;
        public Button mButton5;
        public Button mButton6;
        public Button mButton7;
        public Button mButton8;
        public Button mButton9;
        public Button mButton10;
        public FloatingActionButton mFab;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
            this.mButton = (Button) rootView.findViewById(R.id.button);
            this.mButton2 = (Button) rootView.findViewById(R.id.button2);
            this.mButton3 = (Button) rootView.findViewById(R.id.button3);
            this.mButton4 = (Button) rootView.findViewById(R.id.button4);
            this.mButton5 = (Button) rootView.findViewById(R.id.button5);
            this.mButton6 = (Button) rootView.findViewById(R.id.button6);
            this.mButton7 = (Button) rootView.findViewById(R.id.button7);
            this.mButton8 = (Button) rootView.findViewById(R.id.button8);
            this.mButton9 = (Button) rootView.findViewById(R.id.button9);
            this.mButton10 = (Button) rootView.findViewById(R.id.button10);
            this.mFab = (FloatingActionButton) rootView.findViewById(R.id.fab);

            mButton.setOnClickListener(this);
            mButton2.setOnClickListener(this);
            mButton3.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    Toast.makeText(rootView.getContext(),"button1",Toast.LENGTH_SHORT).show();
                    rootView.getContext().startActivity(new Intent(rootView.getContext(),ActivityToolBar.class));
                    break;
                case R.id.button2:
                    Toast.makeText(rootView.getContext(),"button2",Toast.LENGTH_SHORT).show();
                    rootView.getContext().startActivity(new Intent(rootView.getContext(),ActivityViewPager.class));
                    break;
                case R.id.button3:
                    Toast.makeText(rootView.getContext(),"button3",Toast.LENGTH_SHORT).show();
                    rootView.getContext().startActivity(new Intent(rootView.getContext(),Activity_CollapsingToolbar.class));
                    break;
            }
        }
    }
}
