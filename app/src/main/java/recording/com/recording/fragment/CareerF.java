package recording.com.recording.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private int pageSize = 10, pageIndex = 0;
    CareerA adapter;
    List<Career> listC;

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
        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                pageIndex = 0;
                getData();
            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                getData();
            }
        });
    }

    private void getData(){
        BmobQuery<Career> query = new BmobQuery<Career>();
        query.addWhereEqualTo("userid", new SPUtil(getActivity()).getValue(SharedpreKeyMap.LoginName));
        query.setLimit(pageSize);//每页多少条
        int sizep = pageSize * pageIndex;
        query.setSkip(sizep);
        query.findObjects(getActivity(), new FindListener<Career>() {
            @Override
            public void onSuccess(List<Career> list) {
                if (list.size() > 0) {
                    if(pageIndex == 0){
                        if(listC!=null){
                            listC.clear();
                        }else{
                            listC = new ArrayList<Career>();
                        }
                        listC.addAll(list);
                        adapter = new CareerA(CareerF.this.getActivity(), listC);
                        xlv.setAdapter(adapter);
                    }else{
                        listC.addAll(list);
                        adapter.setData(listC);
                        xlv.stopLoadMore();
                        xlv.endLoadMore(pageSize, list.size());
                    }
                    xlv.stopRefresh();
                    xlv.setRefreshTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }else{
                    xlv.endLoadMore();
                }
//                Snackbar.make(xlv, list.size()+"==", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(int i, String s) {
                Snackbar.make(xlv, s, Snackbar.LENGTH_LONG).show();
            }
        });

    }
}
