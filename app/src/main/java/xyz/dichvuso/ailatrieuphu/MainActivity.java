package xyz.dichvuso.ailatrieuphu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.urbanairship.UAirship;

//import com.parse.Parse;

public class MainActivity extends AppCompatActivity {
    RelativeLayout MainLayout;
    Button btnStart, btnAbout, btnSetting, btnExit, btnScore;
    String userName;
    String prefname="data_save";
    public void restoringPreferences()
    {
        SharedPreferences pre = getSharedPreferences
                (prefname, MODE_PRIVATE);
        userName = pre.getString("user", "bạn");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////
        final Context context = this;
        MainLayout = (RelativeLayout)findViewById(R.id.LayoutMain);
        btnStart = (Button)findViewById(R.id.buttonStart);
        btnAbout = (Button)findViewById(R.id.buttonAbout);
        btnScore = (Button)findViewById(R.id.buttonScore);
        btnExit = (Button)findViewById(R.id.buttonExit);
        btnSetting = (Button)findViewById(R.id.buttonSetting);


        //MainLayout.setBackgroundResource(R.drawable.start);

        final MediaPlayer sou = MediaPlayer.create(MainActivity.this, R.raw.bgmusic_1);
        sou.start();
        restoringPreferences();
        Toast.makeText(MainActivity.this, "Xin chào " + userName + ". Chúc bạn một ngày vui vẻ!", Toast.LENGTH_LONG).show();





        ///////////////Events for Button

        ///Start by the first is Next to Screen
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sou.stop();
                Intent Start1Screen = new Intent(MainActivity.this, Activity_Start.class);
                finish();
                startActivity(Start1Screen);

            }
        });

        //Setting Parse
      //  Parse.enableLocalDatastore(context);
      //  Parse.initialize(context);

        /*
        //test parse
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CAUHOI");


        //query.whereEqualTo("playerName", "Dan Stemkoski");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> ch, ParseException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, String.valueOf(ch.size()), Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }


        });
*/


        //  Toast.makeText(MainActivity.this, cauHoi.getCauHoi(), Toast.LENGTH_LONG).show();


        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(MainActivity.this, Activity_Score.class);
                startActivity(i);
            }
        });
        ///Settings Click
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stingScreen  = new Intent(MainActivity.this, Activity_Settings.class);
                startActivity(stingScreen);
            }
        });

        ////About Click
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutScreen  = new Intent(MainActivity.this, Activity_About.class);
                startActivity(aboutScreen);
            }
        });

        ///Exit app
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Xác nhận thoát");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn muốn thoát chương trình?")
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        sou.stop();
                                        // if this button is clicked, close
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);

                                        // Tao su kien ket thuc app
                                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                                        startMain.addCategory(Intent.CATEGORY_HOME);
                                        finish();
                                        startActivity(startMain);

                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu("Chức năng");
        subMenu.add(0, 1, 0, "Vào chơi");
        subMenu.add(0, 2, 0, "Thông tin");
        subMenu.add(0, 3, 0, "Thoát");
        return true;


    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Xác nhận thoát");

        // set dialog message
        alertDialogBuilder
                .setMessage("Bạn muốn thoát chương trình?")
                .setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // if this button is clicked, close
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                                // Tao su kien ket thuc app
                                Intent startMain = new Intent(Intent.ACTION_MAIN);
                                startMain.addCategory(Intent.CATEGORY_HOME);
                                finish();
                                startActivity(startMain);

                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        return;
    }

/*

    CauHoi c;
    int id = 1;
    //Lấy dữ liệu từ server
    public CauHoi getDataFromParse(Integer cau)
    {

        //Setting Parse

        ParseQuery<ParseObject> query = ParseQuery.getQuery("CAUHOI");
       // query.whereEqualTo("CauSo", "1");
        //query.setLimit(2); // limit to at most 10 results
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> ch, com.parse.ParseException e) {
                if (e == null && ch.size() != 0) {

                    id ++;
                    String ND = ch.get(0).getString("CauHoi");
                    String A = ch.get(0).getString("A");
                    String B = ch.get(0).getString("B");
                    String C = ch.get(0).getString("C");
                    String D = ch.get(0).getString("D");
                    String DA = ch.get(0).getString("DapAn");
                    int MucCau = ch.get(0).getInt("CauSo");

                    c = new  CauHoi(id, ND, A, B, C, D, DA,MucCau);

                } else {
                    c = new CauHoi(1, "Nguyên nhân chưa đọc được dữ liệu online!", "Chưa cấp quyền", "Lỗi CSDL",
                            "Code sai", "Lỗi định mệnh", "D",4);

                }
            }

        });
        return c;
    }
*/
}
