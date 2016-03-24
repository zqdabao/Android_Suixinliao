package com.tencent.qcloud.timchat.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMElem;
import com.tencent.qcloud.timchat.MyApplication;
import com.tencent.qcloud.timchat.R;
import com.tencent.qcloud.timchat.utils.TimeUtil;

import java.io.Serializable;

/**
 * 会话数据
 */
public abstract class Conversation implements Comparable {

    //会话对象id
    protected String identify;

    //会话类型
    protected TIMConversationType type;

    //会话对象名称
    protected String name;


    /**
     * 获取最后一条消息的时间
     */
    abstract public long getLastMessageTime();

    /**
     * 获取未读消息数量
     */
    abstract public long getUnreadNum();


    /**
     * 将所有消息标记为已读
     */
    abstract public void readAllMessage();


    /**
     * 获取头像
     */
    abstract public int getAvatar();


    /**
     * 跳转到聊天界面或会话详情
     *
     * @param context 跳转上下文
     */
    abstract public void navToDetail(Context context);

    /**
     * 获取最后一条消息摘要
     */
    abstract public String getLastMessageSummary();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentify(){
        return identify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        if (!identify.equals(that.identify)) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = identify.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }


    /**
     * Compares this object to the specified object to determine their relative
     * order.
     *
     * @param another the object to compare to this instance.
     * @return a negative integer if this instance is less than {@code another};
     * a positive integer if this instance is greater than
     * {@code another}; 0 if this instance has the same order as
     * {@code another}.
     * @throws ClassCastException if {@code another} cannot be converted into something
     *                            comparable to {@code this} instance.
     */
    @Override
    public int compareTo(Object another) {
        if (another instanceof Conversation){
            Conversation anotherConversation = (Conversation) another;
//            if (anotherConversation.lastMessage ==null && lastMessage == null) return 0;
//            if (anotherConversation.lastMessage == null) return -1;
//            if (lastMessage == null) return 1;
//            long timeGap = anotherConversation.lastMessage.getMessage().timestamp() - lastMessage.getMessage().timestamp();
//            if (timeGap > 0) return  1;
//            else if (timeGap < 0) return -1;
//            return 0
            long timeGap = anotherConversation.getLastMessageTime() - getLastMessageTime();
            if (timeGap > 0) return  1;
            else if (timeGap < 0) return -1;
            return 0;
        }else{
            throw new ClassCastException();
        }
    }



}
