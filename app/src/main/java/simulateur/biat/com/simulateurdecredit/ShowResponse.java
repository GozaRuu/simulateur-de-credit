package simulateur.biat.com.simulateurdecredit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Visibility;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class ShowResponse extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String methodName ;
    ArrayList<String> dataGathererArguments ;
    TextView sum  ;
    private ArrayList<ResponseType> data = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_response);
        setupWindowAnimations();
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        sum = (TextView)findViewById(R.id.sum) ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        methodName = extras.getString("methodName") ;
        dataGathererArguments = new ArrayList<String>() ;
        DataGatherer dataGatherer = new DataGatherer(methodName, extras.getStringArray("Args")) ;
        //String data_salah = null;
        //DataGatherer dataGatherer = new DataGatherer(methodName, dataGathererArguments.toArray(new String[0])) ;
        try {
            if(dataGatherer.gather())
            {
                data = dataGatherer.data.get(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(data!=null)
        {
            prepareListData();
        }
        else
        {
            prepareListFakeData();
        }
        //prepareListFakeData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

    }

    private void prepareListFakeData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding parent data
        listDataHeader.add("Tranche 1");
        listDataHeader.add("Tranche 2");
        listDataHeader.add("Tranche 3");

        // Adding child data
        List<String> retour1 = new ArrayList<String>();
        retour1.add("Echeance: 100");
        retour1.add("NombreDeJours: 150");
        retour1.add("Encours: 2000");
        retour1.add("Amortissement: 400");
        retour1.add("Interets: 2");

        List<String> retour2 = new ArrayList<String>();
        retour2.add("Echeance: 200");
        retour2.add("NombreDeJours: 200");
        retour2.add("Encours: 200");
        retour2.add("Amortissement: 10");
        retour2.add("Interets: 2");

        List<String> retour3 = new ArrayList<String>();
        retour3.add("Echeance: 300");
        retour3.add("NombreDeJours: 10");
        retour3.add("Encours: 200");
        retour3.add("Amortissement: 20");
        retour3.add("Interets: 2");

        listDataChild.put(listDataHeader.get(0), retour1);
        listDataChild.put(listDataHeader.get(1), retour2);
        listDataChild.put(listDataHeader.get(2), retour3);

        sum.setText(String.valueOf(2400));
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        double sum_tot =0 ;
        int i=0 ;
        for(ResponseType response : data)
        {

            listDataHeader.add("Tranche "+String.valueOf(i+1));
            List<String> retour = new ArrayList<String>();
            double d = 0;
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            try {
                Number number = format.parse(response.Encours) ;
                d = number.doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sum_tot = sum_tot + d ;
            retour.add("Echeance: " + response.Echeance);
            retour.add("NombreDeJours: " + response.NombreDeJours);
            retour.add("Encours: " + response.Encours);
            retour.add("Amortissement: " + response.Amortissement);
            retour.add("Interets: " + response.Interets);
            listDataChild.put(listDataHeader.get(i), retour);
            i++;
        }

        sum.setText(String.valueOf(sum_tot));
    }


    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    private Visibility buildEnterTransition() {
        Fade enterTransition = new Fade();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }
}