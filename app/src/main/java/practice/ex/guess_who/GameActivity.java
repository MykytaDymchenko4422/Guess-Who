package practice.ex.guess_who;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class GameActivity extends AppCompatActivity {

    public static final String USER_NAME = "user_name";
    public static final String USER_RESULT = "user_result";

    Dialog dialog;
    TextView dialog_result;
    Button button1, button2, button3, button4, close_btn;
    ImageView main_img, background;
    ImageButton sound_button;

    MediaPlayer mPlayer;
    AudioManager audioManager;
    Intent intent;
    String user_name;
    String[] answers;
    int right_answer = 4, count_right_answers = 0, counterSoundImg = 0, counterAnswers = 0;
    int[] img = {
            R.drawable.frog_img,
            R.drawable.monkey_img,
            R.drawable.elephant_img,
            R.drawable.squirrel_img,
            R.drawable.tiger_img,
            R.drawable.wolf_img,
            R.drawable.bear_img,
            R.drawable.snake_img,
            R.drawable.horse_img,
            R.drawable.boar_img,

            R.drawable.dove_img,
            R.drawable.crow_img,
            R.drawable.woodpecker_img,
            R.drawable.parrot_img,
            R.drawable.nightingale_img,
            R.drawable.toucan_img,
            R.drawable.duck_img,
            R.drawable.eagle_img,
            R.drawable.cuckoo_img,
            R.drawable.owl_img,

            R.drawable.phone_img,
            R.drawable.vacuum_img,
            R.drawable.guitar_img,
            R.drawable.kettle_img,
            R.drawable.drum_img,
            R.drawable.piano_img,
            R.drawable.alarm_img,
            R.drawable.train_img,
            R.drawable.car_img,
            R.drawable.helicopter_img,
    };

    int[] sound = {
            R.raw.frog_sound,
            R.raw.monkey_sound,
            R.raw.elephant_sound,
            R.raw.squirrel_sound,
            R.raw.tiger_sound,
            R.raw.wolf_sound,
            R.raw.bear_sound,
            R.raw.snake_sound,
            R.raw.horse_sound,
            R.raw.boar_sound,

            R.raw.dove_sound,
            R.raw.crow_sound,
            R.raw.woodpecker_sound,
            R.raw.parrot_sound,
            R.raw.nightingale_sound,
            R.raw.toucan_sound,
            R.raw.duck_sound,
            R.raw.eagle_sound,
            R.raw.cuckoo_spund,
            R.raw.owl_sound,

            R.raw.phone_sound,
            R.raw.vacuum_sound,
            R.raw.guitar_sound,
            R.raw.kettle_sound,
            R.raw.drum_sound,
            R.raw.piano_sound,
            R.raw.alarm_sound,
            R.raw.train_sound,
            R.raw.car_sound,
            R.raw.helicopter_sound,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        intent = getIntent();
        user_name = intent.getStringExtra(USER_NAME);
        answers  = getResources().getStringArray(R.array.answers);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        main_img = (ImageView) findViewById(R.id.imageView);
        background = (ImageView) findViewById(R.id.game_background);
        sound_button = (ImageButton) findViewById(R.id.soundButton);


        Random rnd = new Random();
        for (int i = 0, j = 4; i < answers.length; i++) {
            if(i == j){ j +=5; continue; }
            int t = rnd.nextInt(j-i);
            t += i;
            String temp = answers[i];
            answers[i] = answers[t];
            answers[t] = temp;
        }

        counterAnswers = 0;
        mPlayer = MediaPlayer.create(this, sound[counterSoundImg]);
        main_img.setImageResource(img[counterSoundImg]);
        button1.setText(answers[counterAnswers]);
        button2.setText(answers[counterAnswers+1]);
        button3.setText(answers[counterAnswers+2]);
        button4.setText(answers[counterAnswers+3]);
    }

    public void play_sound(View view){
        if(mPlayer.isPlaying())mPlayer.stop();
        else {
            mPlayer = MediaPlayer.create(this, sound[counterSoundImg]);
            mPlayer.start();
        }
    }


    public void check_answer(View view){
        String answer = ((Button) view).getText().toString();
        if(answer.equals(answers[right_answer])) count_right_answers++;
        else{
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
        }
        counterSoundImg++;
        counterAnswers += 5;
        right_answer +=5;


        if(counterSoundImg == 30){
            dialog = new Dialog(this); //создание далогвого окна
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрыть заголовок
            dialog.setContentView(R.layout.dialog); //путь к макету диалогового окна
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон
            dialog.setCancelable(false);// отключить закрыть окна кнопкой "Назад"

            dialog_result = (TextView) dialog.findViewById(R.id.result_description);
            dialog_result.setText("Молодец "+ user_name + ", ты оветил(ла) правильно на " + count_right_answers + " из 30.\n" +
                    "Ты можешь посмореть результаты нажав на кнопку '" + getResources().getString(R.string.go_to_main)+"'.");

            close_btn = (Button)dialog.findViewById(R.id.go_to_main);
            close_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent_main = new Intent(GameActivity.this, MainActivity.class);
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(GameActivity.this);
                    UserResult result = new UserResult(user_name, count_right_answers);
                    boolean success = dataBaseHelper.addOne(result);
                    //Toast.makeText(GameActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                    startActivity(intent_main);
                }
            });

            dialog.show();
        }
        else{
            if(counterSoundImg == 10){
                background.setImageResource(R.drawable.birds_background);
                button1.setBackgroundResource(R.drawable.stage2_button_style);
                button2.setBackgroundResource(R.drawable.stage2_button_style);
                button3.setBackgroundResource(R.drawable.stage2_button_style);
                button4.setBackgroundResource(R.drawable.stage2_button_style);
                sound_button.setBackgroundResource(R.drawable.stage2_button_style);
            }
            else if (counterSoundImg == 20){
                background.setImageResource(R.drawable.items_background);
                button1.setBackgroundResource(R.drawable.stage3_button_style);
                button2.setBackgroundResource(R.drawable.stage3_button_style);
                button3.setBackgroundResource(R.drawable.stage3_button_style);
                button4.setBackgroundResource(R.drawable.stage3_button_style);
                sound_button.setBackgroundResource(R.drawable.stage3_button_style);
            }

            //mPlayer = MediaPlayer.create(this, sound[counterSoundImg]);
            main_img.setImageResource(img[counterSoundImg]);
            button1.setText(answers[counterAnswers]);
            button2.setText(answers[counterAnswers+1]);
            button3.setText(answers[counterAnswers+2]);
            button4.setText(answers[counterAnswers+3]);
        }
    }
}