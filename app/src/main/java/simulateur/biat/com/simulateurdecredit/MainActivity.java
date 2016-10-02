package simulateur.biat.com.simulateurdecredit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView constantDureeText ;
    TextView constantMontantText ;
    TextView degressifDureeText ;
    TextView degressifMontantText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constantDureeText = (TextView) findViewById(R.id.constantDureeText) ;
        constantMontantText = (TextView) findViewById(R.id.constantMontantText) ;
        degressifDureeText = (TextView) findViewById(R.id.degressifDureeText) ;
        degressifMontantText = (TextView) findViewById(R.id.degressifMontantText) ;
        setupWindowAnimations();
        setupToolbar();


    }

    private void setupWindowAnimations() {
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }


    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void getConstantDureeUserInput(View v)
    {
        Intent intent = new Intent(this,ConstantDureeInputGatherer.class) ;
        intent.putExtra("toolbarTitle", constantDureeText.getText().toString());
        Bundle bundel = ActivityOptions.makeSceneTransitionAnimation(this,constantDureeText,constantDureeText.getTransitionName()).toBundle() ;
        startActivity(intent, bundel);
    }
    public void getConstantMontantUserInput(View v)
    {
        Intent intent = new Intent(this,ConstantMontantInputGatherer.class) ;
        intent.putExtra("toolbarTitle", constantMontantText.getText().toString());
        Bundle bundel = ActivityOptions.makeSceneTransitionAnimation(this,constantMontantText,constantMontantText.getTransitionName()).toBundle() ;
        startActivity(intent, bundel);
    }
    public void getDegressifDureeUserInput(View v)
    {
        Intent intent = new Intent(this,DegressifDureeInputGatherer.class) ;
        intent.putExtra("toolbarTitle", degressifDureeText.getText().toString());
        Bundle bundel = ActivityOptions.makeSceneTransitionAnimation(this,degressifDureeText,degressifDureeText.getTransitionName()).toBundle() ;
        startActivity(intent, bundel);
    }
    public void getDegressifMontantUserInput(View v)
    {
        Intent intent = new Intent(this,DegressifMontantInputGatherer.class) ;
        intent.putExtra("toolbarTitle", degressifMontantText.getText().toString());
        Bundle bundel = ActivityOptions.makeSceneTransitionAnimation(this,degressifMontantText,degressifMontantText.getTransitionName()).toBundle() ;
        startActivity(intent, bundel);
    }


    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Appuiez sur Back encore Pour Sortir",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
