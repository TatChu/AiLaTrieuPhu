package xyz.dichvuso.ailatrieuphu;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

public class Activity_Settings extends AppCompatActivity {

     EditText edtTen;
    EditText edt_YKien;
    Button btnSave, btn_Send;
    CheckBox ck_Sound ;
    Spinner spn_BoCauHoi;
    Integer LanMoApp;
    String arr[] = {"Bộ mặc định", "Bộ dễ 01", "Bộ khó 01", "Chọn file ..."};
    String bocauhoi;///dung để lưu bộ câu hỏi tạm khi ngừoi dùng chọn
    //đặt tên cho tập tin lưu trạng thái
    String prefname="data_save";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        AnhXa();
        setDatabase();
        restoringPreferences();
        LanMoApp++;

         btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText( Activity_Settings.this, "Đã lưu thành công!", Toast .LENGTH_LONG).show();

            }

        });
        btn_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LanMoApp == 1)
                    InstallParse();
                LanMoApp ++;
                savingPreferences();
                if(checkInternetConnection()) {
                    ParseObject gameScore = new ParseObject("PHANHOI");
                    gameScore.put("NoiDung", String.valueOf(edt_YKien.getText()));
                    gameScore.put("NguoiGopY", String.valueOf(edtTen.getText()));
                    gameScore.saveInBackground();

                    Toast.makeText(Activity_Settings.this, "Cảm ơn ý kiến đóng góp của bạn!", Toast.LENGTH_LONG).show();
                }

                    else
                    Toast.makeText(Activity_Settings.this, "Kết nối mạng không có sẵn!", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //gọi hàm lưu trạng thái ở đây
        savingPreferences();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //gọi hàm đọc trạng thái ở đây
        restoringPreferences();
    }
    private boolean checkInternetConnection() {

        ConnectivityManager connManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo == null) {
            return false;
        }

        if (!networkInfo.isConnected()) {
            return false;
        }

        if (!networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }
    void AnhXa()
    {
        edtTen = (EditText)findViewById(R.id.edtTen);
        edt_YKien = (EditText)findViewById(R.id.edt_GopY);
        btnSave = (Button)findViewById(R.id.btnSave);
        btn_Send = (Button)findViewById(R.id.btn_Send);
        ck_Sound = (CheckBox)findViewById(R.id.Sound);
        spn_BoCauHoi = (Spinner)findViewById(R.id.spinner);
    }

    void setDatabase()
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spn_BoCauHoi.setAdapter(adapter);

        //thiết lập sự kiện chọn phần tử cho Spinner
        spn_BoCauHoi.setOnItemSelectedListener(new MyProcessEvent());
    }
    //Class tạo sự kiện
    private class MyProcessEvent implements
            AdapterView.OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            //selection.setText(arr[arg2]);
            //Nếu chọn môit item nào đó
            if(arg2 == 0) bocauhoi = "ALTP.sqlite";
                else if(arg2 == 1) bocauhoi = "ALTP1.sqlite";
                    else if(arg2 == 2) bocauhoi = "ALTP1.sqlite";
                        else
                            if(arg2 == 3) bocauhoi = "ALTP1.sqlite";

        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            //neeus khong chon gi ca
        }
    }
    void InstallParse()
    {
        Context context = this;
        //Setting Parse
        Parse.enableLocalDatastore(context);
        Parse.initialize(context);
    }

    /**
     * hàm lưu trạng thái
     */
    public void savingPreferences()
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre=getSharedPreferences
                (prefname, MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();

        boolean sound = ck_Sound.isChecked();
        if(false)//khoong xoas
        {
            //xóa mọi lưu trữ trước đó
            editor.clear();
        }
        else
        {
            //lưu vào editor
            editor.putString("user", edtTen.getText().toString());
            editor.putBoolean("sound", sound);
            editor.putInt("LanMoApp", LanMoApp);
            editor.putString("database", bocauhoi);
        }
        //chấp nhận lưu xuống file
        editor.commit();
    }
    public void restoringPreferences()
    {
        SharedPreferences pre=getSharedPreferences
                (prefname, MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
            //lấy user, pwd, nếu không thấy giá trị mặc định là "Chưa lưu"
        String user= pre.getString("user", "Chưa lưu");
        edtTen.setText(user);
        boolean sound = pre.getBoolean("sound", false);;
        ck_Sound.setChecked(sound);
        LanMoApp = pre.getInt("LanMoApp", 0);
        bocauhoi = pre.getString("database", "ALTP.sqlite");
    }
}