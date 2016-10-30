package xyz.dichvuso.ailatrieuphu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_About extends AppCompatActivity {

    Button   btnChu, btnLiem, btnDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        AnhXa();
        btnChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent infoChu = new Intent("android.intent.action.VIEW");
                infoChu.setData(Uri.parse("http://www.facebook.com/tchudk.595"));
                startActivity(infoChu);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu("About");
        subMenu.add(0, 1, 0, "Facebook");
        subMenu.add(0, 2, 0, "Adress");
        return true;
    }
    void AnhXa()
    {
        btnChu = (Button)findViewById(R.id.btn_CHu);
        btnLiem = (Button)findViewById(R.id.btn_Liem);
    }
    private boolean MenuChoice(MenuItem item)
    {
        switch (item.getItemId()) {
            case 0:
            {
                Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:0969369499"));
                startActivity(i);
                return true;
            }
            case 1:
            {
                Intent infoChu = new Intent("android.intent.action.VIEW");
                infoChu.setData(Uri.parse("http://www.facebook.com/tchudk.595"));
                startActivity(infoChu);
                return true;
            }
            case 2:
            {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:12.2692343,109.2005116"));
                startActivity(i);
                return true;
            }

        }
        return false;
    }
}
