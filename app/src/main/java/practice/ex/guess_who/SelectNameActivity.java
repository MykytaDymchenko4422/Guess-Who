package practice.ex.guess_who;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SelectNameActivity extends AppCompatActivity {

    public static final String USER_NAME = "user_name";
    String user_name;
    EditText player_name;
    TextView warning_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name);

        player_name = (EditText) findViewById(R.id.player_name);
        warning_view = (TextView) findViewById(R.id.warning);
    }

    public void getName(View view){
        user_name = player_name.getText().toString();
        if(user_name.isEmpty()){
            warning_view.setText(R.string.warning_name);
        }
        else {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra(USER_NAME, user_name);
            startActivity(intent);
        }
    }
}