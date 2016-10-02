package simulateur.biat.com.simulateurdecredit;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;

public class splash extends AppCompatActivity {

    private static Context context;
    final Activity actForBundle = this ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setExitTransition(new Fade().setDuration(300));
        context = getApplicationContext();
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){

                Intent intent = new Intent(context,MainActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(actForBundle).toBundle() ;
                startActivity(intent,bundle);
            }
        }.start();
    }
}
