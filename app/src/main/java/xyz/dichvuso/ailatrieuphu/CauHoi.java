package xyz.dichvuso.ailatrieuphu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by The Boss on 12/03/2016.
 */
public class CauHoi {

    private int ID;
    private String CauHoi;
    private String A, B, C, D, DapAn;
    private String MucCauHoi;





    public void setID(int id)
    {
        this.ID = id;
    }
    public  int getID ()
    {
        return ID;
    }

    public  void setCauHoi(String ch) {
        this.CauHoi = ch;
    }
    public String getCauHoi()
    {
        return CauHoi;
    }

    public void setA(String A){
        this.A = A;
    }
    public  String getA()
    {
        return A;
    }
    public void setB(String B){
        this.B = B;
    }
    public  String getB()
    {
        return B;
    }

    public void setC(String C){
        this.C = C;
    }
    public  String getC()
    {
        return C;
    }
    public void setD(String D){
        this.D = D;
    }
    public  String getD()
    {
        return D;
    }

    public  String getDapAn()
    {
        return DapAn;
    }
    public  String getMucCauHoi(){
        return MucCauHoi;
    }


    public CauHoi()
    {}
    public CauHoi(int ID, String ND_CauHoi, String A, String B, String C, String D, String DapAn, String MucCau) {
        this.ID = ID;
        this.CauHoi = ND_CauHoi;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.DapAn = DapAn;
        this.MucCauHoi = MucCau;
    }


}