package com.example.tlm_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    List<UserDao> users;

    public UserAdapter(List<UserDao> users){
        this.users = users;
    }

    @Override
    public int getCount() {
        if (users == null)
            return 0;
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        UserListItem item;
        if (view != null)
            item = (UserListItem) view;
        else
            item = new UserListItem(viewGroup.getContext());

        UserDao dao = users.get(position);

        item.setTvName(dao.getName());
        item.setTvGroup(dao.getGroup());

        return item;
    }
}
