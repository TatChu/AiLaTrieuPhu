package xyz.dichvuso.ailatrieuphu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import java.util.Random;


public class Activity_Play extends AppCompatActivity {

    Button btnStop, btnHelpCall, btnHell50_50, btnHelpAudience, btnHelpChangeQuestion, btnA, btnB, btnC, btnD;
    TextView edtQuestion, txt_time, txt_CauSo ;
    Integer  n = 0, t = 0, CauSo = 1, Tien[] = {0, 200000, 400000, 600000, 1000000, 2000000, 3000000, 6000000, 10000000, 14000000
            , 22000000, 30000000, 40000000, 60000000, 85000000, 150000000};
    String DAn = "D";
    CountDownTimer countdown, tichtacDA;
    int[] a = new int[5];
    int lanthu = 1;
    String prefname="data_save";
    String UserName = "";
    Boolean sound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Context context = this;//Phải khai báo final sau này mới sử dụng được
        btnStop = (Button)findViewById(R.id.buttonStop);
        btnHelpCall = (Button)findViewById(R.id.buttonHelpCall);
        btnHell50_50 = (Button)findViewById(R.id.buttonHelp50_50);
        btnHelpAudience = (Button)findViewById(R.id.buttonHelpAudience);
        btnHelpChangeQuestion = (Button)findViewById(R.id.buttonChangeQuestion);
        btnA = (Button)findViewById(R.id.buttonAnsA);
        btnB = (Button)findViewById(R.id.buttonAnsB);
        btnC = (Button)findViewById(R.id.buttonAnsC);
        btnD = (Button)findViewById(R.id.buttonAnsD);
        edtQuestion = (TextView)findViewById(R.id.textViewQuestion);
        txt_time = (TextView)findViewById(R.id.txt_time);
        txt_CauSo = (TextView)findViewById(R.id.txt_CauSo);

        ///Lấy tên người chơi và trạng thái âm thanh
        restoringPreferences();
        //Set data câu hỏi và các phương án
        Set_Data();

        btnHelpCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Trợ giúp: ");
                alertDialogBuilder
                        .setMessage("Bạn muốn sử dụng quyền trợ giúp gọi điện cho người thân?")
                        .setCancelable(false)
                        .setPositiveButton("Gọi",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {

                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                                        // set title
                                        alertDialogBuilder.setTitle("Kết quả trợ giúp: ");

                                        // set dialog message
                                        alertDialogBuilder
                                                .setMessage("Gấu bạn nghĩ là phương án :" + DAn)
                                                .setCancelable(false)

                                                .setNegativeButton("Cảm ơn gấu ^^",
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


                                        btnHelpCall.setEnabled(false);
                                        btnHelpCall.setBackground(getResources().getDrawable(R.drawable.phone_used));
                                    }
                                })
                        .setNegativeButton("Quay lại",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        btnHelpAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnHelpAudience.setEnabled(false);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Trợ giúp");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn muốn nhận sự trợ giúp từ khán giả?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.sound_tro_giup_hoi_y_kien);
                                        if(sound)
                                        sou.start();

                                        //Set phan tram cac phuong an
                                        Trogiup_KhanGia();
                                        String temp = "";
                                        if(DAn.equals("A"))
                                            temp = "A: " + String.valueOf(a[1]) + "%. B: " + String.valueOf(a[2])
                                                    + "%. C: " + String.valueOf(a[3]) + "%. D:" + String.valueOf(a[4]) +"%.";

                                        if(DAn.equals("B"))
                                            temp = "A: " + String.valueOf(a[2]) + "%. B: " + String.valueOf(a[1])
                                                    + "%. C: " + String.valueOf(a[4]) + "%. D:" + String.valueOf(a[3]) + "%.";

                                        if(DAn.equals("C"))
                                            temp = "A: " + String.valueOf(a[3]) + "%. B: " + String.valueOf(a[2])
                                                    + "%. C: " + String.valueOf(a[1]) + "%. D:" + String.valueOf(a[4]) + "%.";

                                        if(DAn.equals("D"))
                                            temp = "A: " + String.valueOf(a[4]) + "%. B: " + String.valueOf(a[2])
                                                    + "%. C: " + String.valueOf(a[3]) + "%. D:" + String.valueOf(a[1]) + "%.";

                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                        alertDialogBuilder.setTitle("Kết quả trợ giúp");

                                        // set dialog message
                                        alertDialogBuilder
                                                .setMessage(temp)
                                                .setCancelable(false)

                                                .setNegativeButton("Cảm ơn khán giả",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog,
                                                                                int id) {
                                                                dialog.cancel();
                                                            }
                                                        });

                                        // create alert dialog
                                        AlertDialog alertDialog = alertDialogBuilder.create();

                                        // show it
                                        alertDialog.show();

                                        btnHelpAudience.setBackground(getResources().getDrawable(R.drawable.audience_used));

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

        ///////////////////////////////////////////////////////////
        btnHell50_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Trợ giúp:");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn muốn sự trợ giúp 50:50?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.sound_chon_50_50);
                                        if(sound)
                                        sou.start();

                                        if(DAn.equals("A"))
                                        {
                                            btnB.setText("");
                                            btnB.setEnabled(false);
                                            btnD.setText("");
                                            btnD.setEnabled(false);
                                        }

                                        if(DAn.equals("B"))
                                        {
                                            btnD.setText("");
                                            btnD.setEnabled(false);
                                            btnC.setText("");
                                            btnC.setEnabled(false);
                                        }

                                        if(DAn.equals("C"))
                                        {
                                            btnB.setText("");
                                            btnB.setEnabled(false);
                                            btnA.setText("");
                                            btnA.setEnabled(false);
                                        }

                                        if(DAn.equals("D"))
                                        {
                                            btnA.setText("");
                                            btnA.setEnabled(false);
                                            btnC.setText("");
                                            btnC.setEnabled(false);
                                        }

                                        btnHell50_50.setEnabled(false);
                                        btnHell50_50.setBackground(getResources().getDrawable(R.drawable.fiftyfifty_used));
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
        /////////////////////////////////////////////////////////////
        btnHelpChangeQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Trợ giúp:");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn muốn sự trợ giúp đổi câu hỏi?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Set_Data();
                                        btnHelpChangeQuestion.setEnabled(false);
                                        btnHelpChangeQuestion.setBackground(getResources().getDrawable(R.drawable.change_used));
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
        /////////////////////////////////////////////////////////////
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.tro_giup_dung_choi);
                if(sound)
                sou.start();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Dừng cuộc chơi");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn chắc chắn dừng cuộc chơi tại đây?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        countdown.cancel();
                                        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.dungcuocchoi);
                                        if(sound)
                                        sou.start();
                                        // if this button is clicked, close
                                        //Go to HomeScreen
                                        ThangCuoc(UserName);
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



        ///btn 50:50


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Phương án cuối cùng");

                // set dialog message
                alertDialogBuilder
                        .setMessage("A là đáp án cuối cùng bạn?")
                        .setCancelable(false)
                        .setPositiveButton("Đúng",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        countdown.cancel();
                                        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.answer_a);
                                        if(sound)
                                        sou.start();
                                        // if this button is clicked, close
                                        // current activity
                                        onWaitingAnswer("A");
                                    }
                                })
                        .setNegativeButton("Không",
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



        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Phương án cuối cùng");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn chọn B là đáp án cuối cùng?")
                        .setCancelable(false)
                        .setPositiveButton("Đúng",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        countdown.cancel();
                                        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.answer_b);
                                        if(sound)
                                        sou.start();
                                        // if this button is clicked, close
                                        // current activity
                                        onWaitingAnswer("B");
                                    }
                                })
                        .setNegativeButton("Không",
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

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Phương án cuối cùng");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn chắc chắn C là phương án cuối cùng?")
                        .setCancelable(false)
                        .setPositiveButton("Đúng",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        countdown.cancel();
                                        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.answer_c);
                                        if(sound)
                                        sou.start();
                                        // if this button is clicked, close
                                        // current activity
                                        onWaitingAnswer("C");
                                    }
                                })
                        .setNegativeButton("Không",
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
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Xác nhận");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Bạn sẽ chọn D là đáp án cuối cùng?")
                        .setCancelable(false)
                        .setPositiveButton("Đúng",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        countdown.cancel();
                                        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.answer_d);
                                        if(sound)
                                        sou.start();
                                        // if this button is clicked, close
                                        // current activity
                                        onWaitingAnswer("D");
                                    }
                                })
                        .setNegativeButton("Không",
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
    //Su kien khi nguoi dung nhan vao nut back
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(Activity_Play.this);
        alertDialogBuilder.setTitle("Xác nhận thoát");

        // set dialog message
        alertDialogBuilder
                .setMessage("Bạn muốn thoát chương trình?")
                .setCancelable(true)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Intent HomeScr = new Intent(Activity_Play.this,MainActivity.class);
                                finish();
                                startActivity(HomeScr);
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
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        return;
    }

    public void ThuaCuoc(int cau, String Ten)
    {
        countdown.cancel();

        MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.sound_ket_thuc);
        if(sound)
        sou.start();
        final Context context = this;
        String msg = "Cảm ơn " + UserName + " đã đến với chương trình!";
        if(CauSo > 5) msg += " Bạn nhận được 2.000.000 đồng.";
        else if(CauSo > 10) msg += " Bạn nhận được 22.000.000 đồng.";
        // Toast.makeText(Activity_Play.this, "Bạn đã thua cuộc!", Toast.LENGTH_LONG).show();


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        alertDialogBuilder.setTitle("Thua cuộc");

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Thoát",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // if this button is clicked, close
                                // current activity
                                Intent HomeScr = new Intent(Activity_Play.this,MainActivity.class);
                                startActivity(HomeScr);
                            }
                        })
                .setNegativeButton("Chơi lại",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                Intent HomeScr = new Intent(Activity_Play.this,Activity_Play.class);
                                finish();
                                startActivity(HomeScr);
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void ThangCuoc(String Ten)
    {
        //Save score's player
        DbHelper dbHelper = new DbHelper(Activity_Play.this);
        dbHelper.Insert_Socre(UserName, Tien[CauSo-1], "female");

        final Context context = this;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Thắng cuộc");
        alertDialogBuilder
                .setMessage(UserName + ". Bạn dành được " + Tien[CauSo-1].toString() + " đồng. Xin chúc mừng")
                .setCancelable(false)
                .setPositiveButton("Đánh giá 5*",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Intent HomeScr = new Intent(Activity_Play.this,MainActivity.class);
                                finish();
                                startActivity(HomeScr);
                            }
                        })
                .setNegativeButton("Chơi lại",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Intent HomeScr = new Intent(Activity_Play.this,Activity_Play.class);
                                finish();
                                startActivity(HomeScr);
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    void Check(String DA) //1-A, 2-B, 3-C, 4-D
    {
        final Context context = this;
        if(DA.equals(this.DAn))
        {
            this.CauSo = CauSo + 1;
            this.n = 0;
            this.t = 30;

            if(DA.equals("A"))
            {
                btnA.setBackground(this.getResources().getDrawable(R.drawable.bg_btn_red));

            }

            if(DA.equals("B")) {

                btnB.setBackground(this.getResources().getDrawable(R.drawable.bg_btn_red));
            }

            if(DA.equals("C")) {

                btnC.setBackground(this.getResources().getDrawable(R.drawable.bg_btn_red));
            }
            if(DA.equals("D"))
            {

                btnD.setBackground(this.getResources().getDrawable(R.drawable.bg_btn_red));
            }

            MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.bgmusic_answer_t);
            if(sound)
            sou.start();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Chính xác");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Bạn đang có " + Tien[CauSo-1] + " đồng")
                    .setCancelable(false)
                    .setPositiveButton("Tiếp tục",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Set_Data();
                                }
                            })
                    .setNegativeButton("Dừng chơi",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                    dialog.cancel();
                                    MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.dungcuocchoi);
                                    if(sound)
                                    sou.start();
                                    ThangCuoc(UserName);
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();


        }

        else
        {
            MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.bgmusic_answer_f);
            if(sound)
            sou.start();
            ThuaCuoc(CauSo, UserName);
        }

        //ThangCuoc("1234");
    }


    void Set_Data()
    {

        btnA.setBackgroundColor(Color.TRANSPARENT);
        btnB.setBackgroundColor(Color.TRANSPARENT);
        btnC.setBackgroundColor(Color.TRANSPARENT);
        btnD.setBackgroundColor(Color.TRANSPARENT);
        btnA.setBackground(this.getResources().getDrawable(R.drawable.bgquestion));
        btnB.setBackground(this.getResources().getDrawable(R.drawable.bgquestion));
        btnC.setBackground(this.getResources().getDrawable(R.drawable.bgquestion));
        btnD.setBackground(this.getResources().getDrawable(R.drawable.bgquestion));

        btnA.setEnabled(true);
        btnB.setEnabled(true);
        btnC.setEnabled(true);
        btnD.setEnabled(true);

        if(CauSo >= 16)
        {
            final Context context = this;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            // set title
            alertDialogBuilder.setTitle("Thắng cuộc");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Bạn dành được " + Tien[CauSo-1].toString() + " đồng. Thật đáng khâm phục!!!")
                    .setCancelable(false)
                    .setPositiveButton("Đánh giá 5*",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    Intent HomeScr = new Intent(Activity_Play.this,MainActivity.class);
                                    finish();
                                    startActivity(HomeScr);
                                }
                            })
                    .setNegativeButton("Chơi lại",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    Intent HomeScr = new Intent(Activity_Play.this,Activity_Play.class);
                                    finish();
                                    startActivity(HomeScr);
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        else{
            DbHelper dbHelpter = new DbHelper(this);
            List<CauHoi> listCauHoi = dbHelpter.danhSach(this.CauSo);

            if(this.CauSo==1)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau1);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==2)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau2);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==3)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau3);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==4)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau4);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==5)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau5);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==6)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau6);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==7)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau7);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==8)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau8);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==9)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau9);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==10)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau10);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==11)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau11);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==12)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau12);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==13)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau13);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==14)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau14);
                if(sound)
                sou.start();
            }
            else

            if(this.CauSo==15)
            {
                MediaPlayer sou = MediaPlayer.create(Activity_Play.this, R.raw.start_cau15);
                if(sound)
                sou.start();
            }

            edtQuestion.setText(listCauHoi.get(0).getCauHoi() + "?");
            btnA.setText(listCauHoi.get(0).getA());
            btnB.setText(listCauHoi.get(0).getB());
            btnC.setText(listCauHoi.get(0).getC());
            btnD.setText(listCauHoi.get(0).getD());

            this.DAn = listCauHoi.get(0).getDapAn();
            txt_CauSo.setText("Câu số: " + this.CauSo.toString());

            ///set time
            if (this.CauSo > 1)
                countdown.cancel();
            countdown = new CountDownTimer(32000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    n = n + 1;
                    t = 31 - n;
                    if (t <= 5)
                        txt_time.setTextColor(Color.RED);
                    else
                        txt_time.setTextColor(Color.BLUE);
                    txt_time.setText("Thời gian: " + t.toString());
                }

                @Override
                public void onFinish() {
                    countdown.cancel();
                    ThuaCuoc(CauSo, UserName);
                }
            }.start();
        }
    }


    //Hàm này sẽ xoát số ramdom cho các phuong an
    // trong do phuong an tra loi dung se co phan tram cao nhat va dat theo thu tu giam gian trong mang a
    void Trogiup_KhanGia()
    {

        Random rd = new Random();
        int t = rd.nextInt(100);
        if(t < 50)
            t = 100 - t;
        a[1] = t - 10;//Giảm độ cao của phương án chính xác
        a[2] = rd.nextInt(100-a[1]);
        a[3] = rd.nextInt(100-a[1] - a[2]);
        a[4]= 100 - (a[1] + a[2] + a[3]);

    }
    void onWaitingAnswer(final String DA)
    {
        int a, b;
        if(CauSo < 3)
        {  a = 4000; b = 200;}
        else
            if(CauSo < 5)
            {a = 5600; b= 200;}
            else
                if(CauSo < 8)
                {a = 6600; b= 200;}
                else
                    if(CauSo < 12)
                    {a = 8600; b= 200;}
                    else
                    {a =15000; b= 200;}
        if(CauSo>1)
        tichtacDA.cancel();

        tichtacDA = new CountDownTimer(a,b) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(DA.equals("A"))
                {
                    lanthu ++;
                    if(lanthu %2 == 0)
                        btnA.setBackground(getResources().getDrawable(R.drawable.bg_btn_green));
                    else
                        btnA.setBackground(getResources().getDrawable(R.drawable.bg_btn_pink));
                }
                if(DA.equals("B"))
                {
                    lanthu ++;
                    if(lanthu%2 == 0)
                        btnB.setBackground(getResources().getDrawable(R.drawable.bg_btn_green));
                    else
                        btnB.setBackground(getResources().getDrawable(R.drawable.bg_btn_pink));
                }

                if(DA.equals("C"))
                {
                    lanthu ++;
                    if(lanthu%2 == 0)
                        btnC.setBackground(getResources().getDrawable(R.drawable.bg_btn_green));
                    else
                        btnC.setBackground(getResources().getDrawable(R.drawable.bg_btn_pink));
                }
                if(DA.equals("D"))
                {
                    lanthu ++;
                    if(lanthu%2 == 0)
                        btnD.setBackground(getResources().getDrawable(R.drawable.bg_btn_green));
                    else
                        btnD.setBackground(getResources().getDrawable(R.drawable.bg_btn_pink));
                }
            }

            @Override
            public void onFinish() {
                Check(DA);
            }
        }.start();

    }

    public void restoringPreferences()
    {
        SharedPreferences pre=getSharedPreferences
                (prefname, MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        //lấy user, pwd, nếu không thấy giá trị mặc định là "Chưa lưu"
        String user= pre.getString("user", "Chưa lưu");
        UserName = user;
        sound = pre.getBoolean("sound", true);
    }
}
