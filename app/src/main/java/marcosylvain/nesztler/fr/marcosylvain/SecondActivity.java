package marcosylvain.nesztler.fr.marcosylvain;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ArrayList<Base> listFromActivity1=new ArrayList<Base>();
    ArrayAdapter adapter;
    int nombreDeValeurs=0;
    Toast toast;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if(getIntent() != null && getIntent().hasExtra("ArrLst")) {
            listFromActivity1 = getIntent().getParcelableArrayListExtra("ArrLst");
        }

        adapter = new ArrayAdapter<Base>(this, android.R.layout.simple_list_item_1, listFromActivity1);
        nombreDeValeurs=listFromActivity1.size();
        ListView resultrechercheList = (ListView) findViewById(R.id.mobile_list);
        resultrechercheList.setAdapter(adapter);
        text=getString(R.string.toast_nbval, nombreDeValeurs);
        toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
        toast.show();
        Button button = (Button) findViewById(R.id.btnRetour);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(SecondActivity.this,MainActivity.class);
               startActivity(i);
           }
        });
    }
}
