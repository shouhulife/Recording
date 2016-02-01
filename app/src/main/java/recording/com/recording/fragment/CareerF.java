package recording.com.recording.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import recording.com.recording.Model.Career;
import recording.com.recording.Model.SharedpreKeyMap;
import recording.com.recording.R;
import recording.com.recording.adapter.CareerA;
import recording.com.recording.utils.SPUtil;
import recording.com.recording.xlv.XListView;

/**
 * 职业生涯
 * Created by hong on 2016/1/13.
 */
public class CareerF extends Fragment{
    XListView xlv;
    private int pageSize = 10, pageIndex = 1;
    CareerA adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_career,container,false);
        init(view);
        return view;
    }

    private void init(View v){
        xlv = (XListView) v.findViewById(R.id.fmtcareer_xlv);
        getData();
    }

    private void getData(){
        BmobQuery<Career> query = new BmobQuery<Career>();
        query.addWhereEqualTo("userid", new SPUtil(getActivity()).getValue(SharedpreKeyMap.LoginName));
        query.setLimit(pageSize);//每页多少条
        query.setSkip(pageSize * pageIndex);
        query.findObjects(getActivity(), new FindListener<Career>() {
            @Override
            public void onSuccess(List<Career> list) {
                if(list.size()>0){
                    adapter = new CareerA(CareerF.this.getActivity(),list);
                    xlv.setAdapter(adapter);
                }
            }

            @Override
            public void onError(int i, String s) {
                Snackbar.make(xlv, s, Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
