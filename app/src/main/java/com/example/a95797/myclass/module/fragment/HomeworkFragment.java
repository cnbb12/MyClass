package com.example.a95797.myclass.module.fragment;

import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;

import com.example.a95797.myclass.module.fragment.base.BaseFragment;
import com.example.a95797.myclass.utils.LoadMoreRecyclerView;
import com.example.a95797.myclass.utils.Onrefresh;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 95797 on 2017/12/28.
 */

public class HomeworkFragment extends BaseFragment {
    //private List<ErrandEntity.ListEntity> mDatas = new ArrayList();
    private int max_id = -1;
    private int min_id = -1;
    LinearLayout noData;
    private Onrefresh reciever;
    LoadMoreRecyclerView recyclerView;
    Handler handler = new Handler(new Handler.Callback()
    {
        public boolean handleMessage(Message paramAnonymousMessage)
        {
            if (paramAnonymousMessage.what == 1) {
                HomeworkFragment.this.getList("refresh", "1", "-1");
            }
            return true;
        }
    });

    private void getList(String refresh, String s, String s1) {
    }

    TimerTask task = new TimerTask()
    {
        public void run()
        {
            Message localMessage = new Message();
            localMessage.what = 1;
            HomeworkFragment.this.handler.sendMessage(localMessage);
        }
    };
    Timer timer;
}
