package simulateur.biat.com.simulateurdecredit;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.transition.Fade;
        import android.transition.Slide;
        import android.view.Gravity;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.inputmethod.EditorInfo;
        import android.widget.EditText;
        import android.widget.SeekBar;
        import android.widget.TextView;
        import com.triggertrap.seekarc.SeekArc;
        import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

        import java.text.DecimalFormat;
        import java.util.Calendar;

public class DegressifMontantInputGatherer extends AppCompatActivity {

    private SeekArc montant;
    private EditText montantProgress;

    private SeekArc montantDeRemboursement;
    private EditText montantDeRemboursementProgress;

    private SeekBar taux;
    private EditText tauxProgress;

    private SeekBar periodicitePrincipal;
    private TextView periodicitePrincipalProgress;

    private SeekBar periodiciteInterets;
    private TextView periodiciteInteretsProgress;

    private TextView dateDeblocage;
    private TextView dateDeblocageProgress;

    private TextView premiereEcheanceInterets;
    private TextView premiereEcheanceInteretsProgress;

    private TextView premiereEcheancePrincipal;
    private TextView premiereEcheancePrincipalProgress;

    DatePickerDialog.OnDateSetListener dateDeblocageOnDateSetListener,premiereEcheancePrincipalOnDateSetListener, premiereEcheanceInteretsOnDateSetListener;

    protected int getLayoutFile(){
        return R.layout.activity_degressif_montant_input_gatherer;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutFile());
        setupToolbar(getIntent().getExtras().getString("toolbarTitle"));
        getWindow().setEnterTransition(new Slide(Gravity.LEFT).setDuration(300).setStartDelay(200));
        getWindow().setReenterTransition(new Fade().setDuration(150));
        montant = (SeekArc) findViewById(R.id.montant);
        montantProgress =(EditText) findViewById(R.id.montantProgress) ;

        montantDeRemboursementProgress = (EditText) findViewById(R.id.montantDeRemboursementProgress);
        tauxProgress = (EditText) findViewById(R.id.tauxProgress);
        periodicitePrincipalProgress = (TextView) findViewById(R.id.periodicitePrincipalProgress);
        periodiciteInteretsProgress = (TextView) findViewById(R.id.periodiciteInteretsProgress);
        dateDeblocageProgress = (TextView) findViewById(R.id.dateDeblocageProgress);
        premiereEcheancePrincipalProgress = (TextView) findViewById(R.id.premiereEcheancePrincipalProgress);
        premiereEcheanceInteretsProgress = (TextView) findViewById(R.id.premiereEcheanceInteretsProgress);

        montantDeRemboursement = (SeekArc) findViewById(R.id.montantDeRemboursement);
        taux = (SeekBar) findViewById(R.id.taux);
        periodicitePrincipal  = (SeekBar) findViewById(R.id.periodicitePrincipal);
        periodiciteInterets  = (SeekBar) findViewById(R.id.periodiciteInterets);
        dateDeblocage = (TextView) findViewById(R.id.dateDeblocage);
        premiereEcheancePrincipal = (TextView) findViewById(R.id.premiereEcheancePrincipal);
        premiereEcheanceInterets = (TextView) findViewById(R.id.premiereEcheanceInterets);

        montant.setProgress(2000);
        montantDeRemboursement.setProgress(2000);
        taux.setProgress(125);
        periodicitePrincipal.setProgress(1);
        periodiciteInterets.setProgress(1);

        montant.setRotation(40);
        montant.setStartAngle(-10);

        montantDeRemboursement.setRotation(40);
        montantDeRemboursement.setStartAngle(-10);

        dateDeblocageOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                String date = year+"-"+String.format("%02d", ++monthOfYear)+"-"+String.format("%02d", dayOfMonth) ;
                dateDeblocage.setText(date) ;
                dateDeblocageProgress.setText("cliquez pour changer");

            }
        };

        premiereEcheancePrincipalOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                String date = year+"-"+String.format("%02d", ++monthOfYear)+"-"+String.format("%02d", dayOfMonth) ;
                premiereEcheancePrincipal.setText(date) ;
                premiereEcheancePrincipalProgress.setText("cliquez ici pour changer");
            }
        };

        premiereEcheanceInteretsOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                String date = year+"-"+String.format("%02d", ++monthOfYear)+"-"+String.format("%02d", dayOfMonth) ;
                premiereEcheanceInterets.setText(date) ;
                premiereEcheanceInteretsProgress.setText("cliquez ici pour changer");
            }
        };

        dateDeblocageProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        dateDeblocageOnDateSetListener,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(true);
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                dpd.showYearPickerFirst(true);
                dpd.setTitle("Date de Deblocage");
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }


        });

        premiereEcheancePrincipalProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        premiereEcheancePrincipalOnDateSetListener,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(true);
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                dpd.showYearPickerFirst(true);
                dpd.setTitle("Premiere Echeance Principal");
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }


        });

        premiereEcheanceInteretsProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        premiereEcheanceInteretsOnDateSetListener,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(true);
                dpd.vibrate(true);
                dpd.dismissOnPause(true);
                dpd.showYearPickerFirst(true);
                dpd.setTitle("Premiere Echeance Interets");
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }


        });


        montant.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onProgressChanged(SeekArc seekArc, int progress,
                                          boolean fromUser) {
                montantProgress.setText(String.valueOf(progress));
            }
        });

        montantProgress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Integer i = Integer.parseInt(v.getText().toString());
                    if (i >= 0 && i <= 100000) {
                        montant.setProgress(i);
                    }
                    else
                    {
                        montant.setProgress(2000);
                    }
                    return true;
                }
                return false;
            }
        });

        montantProgress.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    Integer i = Integer.parseInt(editText.getText().toString());
                    if (i >= 0 && i <= 100000) {
                        montant.setProgress(i);
                    }
                    else
                    {
                        montant.setProgress(2000);
                    }
                }
            }
        });


        montantDeRemboursement.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                montantDeRemboursementProgress.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }
        });

        montantDeRemboursementProgress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Integer i = Integer.parseInt(v.getText().toString());
                    if (i >= 0 && i <= 2000) {
                        montantDeRemboursement.setProgress(i);
                    }
                    else
                    {
                        montantDeRemboursement.setProgress(50);
                    }
                    return true;
                }
                return false;
            }
        });

        montantDeRemboursementProgress.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    Integer i = Integer.parseInt(editText.getText().toString());
                    if (i >= 0 && i <= 2000) {
                        montantDeRemboursement.setProgress(i);
                    }
                    else
                    {
                        montantDeRemboursement.setProgress(50);
                    }
                }
            }
        });



        tauxProgress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Float i = Float.parseFloat(v.getText().toString())*100;
                    if (i >= 0 && i <= 6000) {
                        taux.setProgress(Math.round(i));
                    }
                    else
                    {
                        taux.setProgress(125);
                    }
                    return true;
                }
                return false;
            }
        });

        tauxProgress.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    Float i = Float.parseFloat(editText.getText().toString())*100;
                    if (i >= 0 && i <= 6000) {
                        taux.setProgress(Math.round(i));
                    }
                    else
                    {
                        taux.setProgress(Math.round(125));
                    }
                }
            }
        });

        taux.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar view, int progress, boolean fromUser) {
                tauxProgress.setText(new DecimalFormat("00.00").format((float)progress/100));
            }
        });

        periodicitePrincipal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar view, int progress, boolean fromUser) {
                periodicitePrincipalProgress.setText(Integer.toString(progress));
            }
        });



        periodiciteInterets.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar view, int progress, boolean fromUser) {
                periodiciteInteretsProgress.setText(Integer.toString(progress));
            }
        });


    }

    public void connectAndSend(View V)
    {
        Intent intent = new Intent(this,ShowResponse.class) ;
        intent.putExtra("methodName","DegressifMontant") ;
        String[] args = new String[] {montantProgress.getText().toString(), montantDeRemboursementProgress.getText().toString(),
                tauxProgress.getText().toString(), periodicitePrincipalProgress.getText().toString(),periodiciteInteretsProgress.getText().toString(),
                dateDeblocage.getText().toString(), premiereEcheancePrincipal.getText().toString(), premiereEcheanceInterets.getText().toString()  };
        intent.putExtra("Args", args);
        startActivity(intent) ;
    }


    private void setupToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.sample_white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }
}
