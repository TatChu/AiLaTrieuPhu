package xyz.dichvuso.ailatrieuphu;


/**
 * Created by Nguyễn Ngọc Liêm on 02/27/2016.
 * Last Edited by The Boss on 18/03/2016
 */
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbHelper {
    Context context;
    SQLiteDatabase db;

    public DbHelper(Context context) {
        this.context = context;
        AssetDatabaseOpenHelper assetDB = new AssetDatabaseOpenHelper(context);
        db = assetDB.StoreDatabase();
    }


    private ArrayList<Score> get_Score(String sql, String... selectionArgs) {
        ArrayList<Score> contents = new ArrayList<Score>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            Score score = new Score(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3));
            contents.add(score);

        }
        c.close();
        return contents;
    }

    // Lấy câu hỏi bất kỳ

    public ArrayList<Score> get_list_Score() {

        String sql = "select * from Score ";

        return get_Score(sql);

    }


    private ArrayList<CauHoi> getCauHoi(String sql, String... selectionArgs) {
        ArrayList<CauHoi> contents = new ArrayList<CauHoi>();

        // đọc từng cột từ database mới copy từ file assets vào như bình thường
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()) {
            CauHoi cauHoi = new CauHoi(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), String.valueOf(c.getInt(7)));
            contents.add(cauHoi);
        }
        c.close();
        return contents;
    }


    // Lấy câu hỏi bất kỳ

    public ArrayList<CauHoi> danhSach(int cau) {
        String sql = "select * from NoiDungCauHoi where MucCauHoi = " + String.valueOf(cau) + " ORDER BY RANDOM() LIMIT 1";
        return getCauHoi(sql);
    }

    public void Insert_Socre(String Name, int Money, String Img)
    {
        db.execSQL("Insert into Score (PlayerName, Money, Anh) values ('" + Name + "', " + Money + ", '" + "Img')");
    }
}
