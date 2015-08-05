package ua.kharkiv.dorozhan.androiddataservice.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ua.kharkiv.dorozhan.androiddataservice.R;
import ua.kharkiv.dorozhan.androiddataservice.model.User;

public class UserListAdapter extends BaseAdapter {
    private List<User> users;
    private Context context;
    private LayoutInflater inflater;

    public UserListAdapter(Context context, List<User> users) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return users.size();
    }

    public User getItem(int position) {
        return users.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.user_object, parent, false);
        TextView firstName = (TextView) view.findViewById(R.id.firstName);
        TextView email = (TextView) view.findViewById(R.id.email);
        TextView number = (TextView) view.findViewById(R.id.number);
        firstName.setText(getItem(position).getFirstName());
        email.setText(getItem(position).getEmail());
        number.setText(String.valueOf(++position));
        return view;
    }

    public void refill(List<User> users) {
        this.users.clear();
        this.users.addAll(users);
        notifyDataSetChanged();
    }
}
