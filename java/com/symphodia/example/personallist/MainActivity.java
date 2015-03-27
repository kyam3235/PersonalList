package com.symphodia.example.personallist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    PersonFragment mPersonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            mPersonFragment = PersonFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mPersonFragment)
                    .commit();
        }

        final EditText etName = (EditText)findViewById(R.id.et_name);
        final EditText etAge = (EditText)findViewById(R.id.et_age);
        final Button btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int age = -1;
                try{
                    age = Integer.parseInt(etAge.getText().toString());
                }catch (NumberFormatException e) {
                    throw new NumberFormatException(e.getMessage());
                }

                if(!name.equals("") && age >= 0){
                    mPersonFragment.add(name, age);
                }
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
}
