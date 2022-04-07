package practice.ex.guess_who;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    Button return_btn;
    ListView results_view;
    ArrayAdapter usersArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        results_view = (ListView)findViewById(R.id.result_view);
        return_btn = (Button) findViewById(R.id.return_btn);
        DataBaseHelper db = new DataBaseHelper(this);
        usersArrayAdapter = new ArrayAdapter<UserResult>(this, R.layout.row, db.getAll());
        results_view.setAdapter(usersArrayAdapter);
    }

    public void ToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}