package com.example.sqlitetest;


import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase =  getBaseContext().openOrCreateDatabase("Sqlite_Test_1.db", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts;");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER, email TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Ashish', 1234, 'ash@gmail.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Shikha', 9911, 'shi@gmail.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Prashant', 6789, 'pra@gmail.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Joginder', 9876, 'jog@gmail.com');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('Kanhiya', 8823, 'kkr@gmail.com');");

        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts;", null);

            if (query.moveToLast()) {
                do {
                    String name = query.getString(0);
                    int phone = query.getInt(1);
                    String email = query.getString(2);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Data");
                    builder.setMessage("Name =" + name + "\n Phone =" + phone + "\n Email =" + email);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                   builder.show();
                }while(query.moveToPrevious());
            }
         query.close();
        sqLiteDatabase.close();
    }
}
