package com.tencent.qcloud.timchat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.TIMFriendGroup;
import com.tencent.TIMUserProfile;
import com.tencent.qcloud.timchat.R;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qcloud.timchat.R.color.colorLabelRed;

/**
 * 分组信息Adapters
 */
public class ExpandGroupListAdapter extends BaseExpandableListAdapter {
    private List<TIMFriendGroup> mGroups;
    private List<List<TIMUserProfile>> mAllGroupMembers;
    private Context mContext;
    private int resourceId = -1;
    private List<TIMUserProfile> mChooseMembers;


    public ExpandGroupListAdapter(Context context, List<TIMFriendGroup> groups, List<List<TIMUserProfile>> allgroupMembers) {
        mContext = context;
        mGroups = groups;
        mAllGroupMembers = allgroupMembers;

    }

    public ExpandGroupListAdapter(Context context, List<TIMFriendGroup> groups, List<List<TIMUserProfile>> allgroupMembers, int resource) {
        mContext = context;
        mGroups = groups;
        mAllGroupMembers = allgroupMembers;
        resourceId = resource;
        mChooseMembers = new ArrayList<TIMUserProfile>();
        mChooseMembers.clear();

    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (mAllGroupMembers.size() == 0) return 0;
        return mAllGroupMembers.get(i).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mAllGroupMembers.size() == 0) return null;
        return mAllGroupMembers.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 群组
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group, null);
            groupHolder = new GroupHolder();
            groupHolder.groupname = (TextView) convertView.findViewById(R.id.group_name);
            // groupHolder.img = (ImageView) convertView
            // .findViewById(R.id.img);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.groupname.setText(mGroups.get(groupPosition).getGroupName());
        return convertView;
    }

    /**
     * 群组成员
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getChildView(final int groupPosition,final int childPosition, boolean isLastChild, View convertView, ViewGroup viewGroup) {
        ChildrenHolder itemHolder = null;
        if (convertView == null) {
            itemHolder = new ChildrenHolder();
            if (resourceId != -1) {
                convertView = LayoutInflater.from(mContext).inflate(resourceId, null);
                itemHolder.tag = (TextView) convertView.findViewById(R.id.choose_tag);
                final ChildrenHolder finalItemHolder = itemHolder;
                itemHolder.tag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finalItemHolder.tag.setBackgroundColor(mContext.getResources().getColor(colorLabelRed));
                        mChooseMembers.add(mAllGroupMembers.get(groupPosition).get(childPosition));
                    }
                });
            } else {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_childmember, null);
            }
            itemHolder.itemname = (TextView) convertView.findViewById(R.id.group_member_name);
//            itemHolder.img = (ImageView) convertView
//             .findViewById(R.id.img);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ChildrenHolder) convertView.getTag();
        }
        itemHolder.itemname.setText(mAllGroupMembers.get(groupPosition).get(childPosition).getIdentifier());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    class GroupHolder {
        public TextView groupname;
        public ImageView img;
    }

    class ChildrenHolder {
        public TextView itemname;
        public ImageView img;
        public TextView tag;
    }

    public List<TIMUserProfile> getChooseList(){
        return mChooseMembers;
    }

}