package recording.com.recording.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import recording.com.recording.Model.Career;
import recording.com.recording.R;

/**
 * Created by zhangh on 2016/2/1.
 */
public class CareerA extends BaseAdapter {
    List<Career> list;
    Context context;
    public CareerA(Context context, List<Career> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(list == null){
            return 0;
        }else {
            return list.size();
        }
    }

    public void setData(List<Career> list){
        if(this.list!=null){
            this.list.clear();
            this.list.addAll(list);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_career,null);
        }
        TextView tv_con = (TextView) view.findViewById(R.id.itemcareer_tv_content);
        TextView tv_date = (TextView) view.findViewById(R.id.itemcareer_tv_date);
        tv_con.setText(list.get(position).getContent());
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        tv_date.setText(format.format(new Date(list.get(position).getDate())));
        return view;
    }
}
