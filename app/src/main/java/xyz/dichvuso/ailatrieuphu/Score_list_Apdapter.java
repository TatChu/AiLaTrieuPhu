package xyz.dichvuso.ailatrieuphu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tatchu on 05/04/2016.
 */
public class Score_list_Apdapter extends BaseAdapter{
    private List<Score> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public Score_list_Apdapter(Context aContext, List<Score> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_score, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.imgView_SV);
            holder.txt_TenNguoiChoi = (TextView) convertView.findViewById(R.id.txt_TenNguoiChoi);
            holder.txt_Tien = (TextView) convertView.findViewById(R.id.txt_Tien);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Score score = this.listData.get(position);
        holder.txt_TenNguoiChoi.setText("Người chơi: " + score.getName());
        holder.txt_Tien.setText("Số tiền: " + score.getMoney());

        int imageId = this.getMipmapResIdByName(score.getAnh());

        holder.img.setImageResource(imageId);

        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    static class ViewHolder {
        ImageView img;
        TextView txt_TenNguoiChoi;
        TextView txt_Tien;
    }
}
