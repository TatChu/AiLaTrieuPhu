package xyz.dichvuso.ailatrieuphu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tatchu on 05/04/2016.
 */
public class Score {
   private int id, money;
    private String Name, Anh;

    public int getId(){return id;}
    public void setID(int id){this.id = id;}

    public String getName(){return Name;}
    public void setName(String Name){this.Name = Name;}

    public String getAnh(){return Anh;}
    public void setAnh(String anh){this.Anh = anh;}

    public int getMoney(){return  money;}
    public void setMoney(int money){this.money =money;}

    public Score(){}
    public Score(int id, String Name, int money, String Anh){
        this.id = id;
        this.Name = Name;
        this.money = money;
        this.Anh = Anh;
    }
}
