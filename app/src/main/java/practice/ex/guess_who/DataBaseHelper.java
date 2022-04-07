package practice.ex.guess_who;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_RESULT = "USER_RESULT";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.bd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_RESULT + " INT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(UserResult userResult){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, userResult.getName());
        cv.put(COLUMN_USER_RESULT, userResult.getResult());

        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }

    public List<UserResult> getAll(){
        List<UserResult> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + USER_TABLE + " ORDER BY " + COLUMN_USER_RESULT + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                int userResult = cursor.getInt(2);
                UserResult newUser = new UserResult(userName, userResult);
                returnList.add(newUser);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
