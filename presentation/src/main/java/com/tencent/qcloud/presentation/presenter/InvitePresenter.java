package com.tencent.qcloud.presentation.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.TIMAddFriendRequest;
import com.tencent.TIMFriendResult;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取自己分组的数据，
 */
public class InvitePresenter extends Presenter {

    private static final String TAG = InvitePresenter.class.getSimpleName();
    private Context mContext;

    public InvitePresenter(Context context) {
        mContext = context;
    }




    public void onInviteFriend(String id,String mark,String other,String source){
        String info = other;
        if(info.equals("")){
            info = "请求添加你为好友";
        }
        List<TIMAddFriendRequest> reqList = new ArrayList<TIMAddFriendRequest>();
        TIMAddFriendRequest friend = new TIMAddFriendRequest();
        friend.setAddWording(info);
        friend.setIdentifier(id);
        friend.setRemark(info);
        friend.setAddrSource("qq");
        reqList.add(friend);
        Log.d(TAG, "add friend here:" + id);
        TIMFriendshipManager.getInstance().addFriend(reqList, new TIMValueCallBack<List<TIMFriendResult>>() {

            @Override
            public void onError(int arg0, String arg1) {
                // TODO Auto-generated method stub
                Log.e(TAG, "add friend error:" + arg0 + ":" + arg1);
            }

            @Override
            public void onSuccess(List<TIMFriendResult> arg0) {
                // TODO Auto-generated method stub
                Log.d(TAG, "add friend response");
                Toast.makeText(mContext, "invite Success!!!!", Toast.LENGTH_SHORT).show();
                for (TIMFriendResult arg : arg0) {
                    Log.d(TAG, "add friend  result:" + arg.getIdentifer() + arg.getStatus());
                }
            }

        });
    }
}
