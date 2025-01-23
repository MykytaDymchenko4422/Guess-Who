package practice.ex.guess_who;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start_select_name;
    Button result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_select_name = (Button) findViewById(R.id.buttonStart);
        result = (Button) findViewById(R.id.buttonResult);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onClick(View view) {
        Intent intent;
        if (view.getId() == R.id.buttonStart) {
            intent = new Intent(this, SelectNameActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.buttonResult) {
            intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }
}