package khuongduongnguyen.carracing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        ImageButton btn_startrace = (ImageButton) findViewById(R.id.btn_startrace);
        btn_startrace.getBackground().setAlpha(0);

        btn_startrace.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
				/* Start Game */
                        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                        startActivity(intent);
            }
        });

        Global.context = getApplicationContext();
    }
}
