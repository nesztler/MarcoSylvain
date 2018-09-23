package marcosylvain.nesztler.fr.marcosylvain;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    InputStream inputStream;
    ArrayList<Base> base = new ArrayList<>();
    private ArrayList<Base> resultatRech = new ArrayList<Base>();
    ArrayAdapter adapter;
    private String textQAD;
    private String textBDDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRechArticleMain = (Button) findViewById(R.id.btnRecherche);
        Button btnEffaceMain = (Button) findViewById(R.id.btnEfface);

        inputStream = getResources().openRawResource(R.raw.myfile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            String[] row;
            String libelle;
            String amdtIrdd;
            while ((csvLine=reader.readLine()) != null) {
                row = csvLine.split(",");
                try {
                    base.add(new Base(row[0], row[1], row[2],row[3]));
                }catch (Exception e) {
                    Log.e("Unknown fuck :", e.toString());
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file " + ex);
        }

       btnRechArticleMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nombreCaracteres=0;
                //int nombreDeValeurs=0;
                String valeurCherchee="";
                String valeurTrouvee="";
                Toast toast;
                resultatRech.clear();
                String text=getString(R.string.toast_message);
                EditText codeQADMain = (EditText) findViewById(R.id.edtCodeQAD);
                EditText codeBDDPMain = (EditText) findViewById(R.id.edtCodeBDDP);
                // Put the two strings in uppercase
                codeQADMain.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                codeBDDPMain.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                if ((!codeQADMain.getText().toString().equals("")) && (codeBDDPMain.getText().toString().equals(""))) {
                    textQAD = codeQADMain.getText().toString();
                    nombreCaracteres=textQAD.length();
                    if (nombreCaracteres>8) {
                        toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
                        toast.show();
                        codeQADMain.setText("");
                    }
                    for (int i = 0; i < base.size(); i++) {
                        try {
                            valeurTrouvee = base.get(i).getCodeTLCD();
                            valeurTrouvee = valeurTrouvee.substring(0, nombreCaracteres);
                           // valeurCherchee = textQAD.substring(0, nombreCaracteres);
                            if (valeurTrouvee.equals(textQAD)) {
                                // resultrechercheMain.setText(base.get(i).toString());
                               // nombreDeValeurs++;
                                resultatRech.add(base.get(i));
                            }
                         } catch (IndexOutOfBoundsException e){
                            // Do Something
                         }
                    }
                } else {
                    if ((codeQADMain.getText().toString().equals("")) && (!codeBDDPMain.getText().toString().equals(""))) {
                        textBDDP = codeBDDPMain.getText().toString();
                        nombreCaracteres=textBDDP.length();
                        for (int i = 0; i < base.size(); i++) {
                            try {
                                valeurTrouvee = base.get(i).getLibelle().trim();
                                valeurTrouvee = valeurTrouvee.substring(0, nombreCaracteres);
                                if (valeurTrouvee.equals(textBDDP)) {
                                    //resultrechercheMain.setText(base.get(i).toString());
                                    // nombreDeValeurs++;
                                    resultatRech.add(base.get(i));
                                }
                            } catch (IndexOutOfBoundsException e){
                                // Do Something
                            }
                        }
                    }
                }
                //populateListView();
                goingToSecondActivity();
            }
        });

        btnEffaceMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText codeQADMain = (EditText) findViewById(R.id.edtCodeQAD);
                EditText codeBDDPMain = (EditText) findViewById(R.id.edtCodeBDDP);
                codeBDDPMain.setText("");
                codeQADMain.setText("");
                resultatRech.clear();
                //populateListView();
            }
        });
    }


/*    public void  populateListView() {
        adapter = new ArrayAdapter<Base>(this, android.R.layout.simple_list_item_1, resultatRech);
        ListView resultrechercheList = (ListView) findViewById(R.id.mobile_list);
        resultrechercheList.setAdapter(adapter);
    }*/

    public void goingToSecondActivity() {
        Intent intent=new Intent(this,SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ArrLst", resultatRech);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
