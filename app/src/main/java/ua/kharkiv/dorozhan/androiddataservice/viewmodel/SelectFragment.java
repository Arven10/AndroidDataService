package ua.kharkiv.dorozhan.androiddataservice.viewmodel;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ua.kharkiv.dorozhan.androiddataservice.R;
import ua.kharkiv.dorozhan.androiddataservice.db.DataServiceHelper;
import ua.kharkiv.dorozhan.androiddataservice.model.User;

public class SelectFragment extends Fragment {
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private List<User> users;
    private ListView userView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View selectView = inflater.inflate(R.layout.fragment_select, container, false);
        Button buttonRefresh = (Button)selectView.findViewById(R.id.buttonRefresh);
        SelectOperationTask selectOperationTask = new SelectOperationTask();
        selectOperationTask.execute();
        try {
            users = selectOperationTask.get();
        } catch (InterruptedException e) {
            showToast(getResources().getString(R.string.application_error));
        } catch (ExecutionException e) {
            showToast(getResources().getString(R.string.application_error));
        }

        userView = (ListView)selectView.findViewById(R.id.users);
        UserListAdapter userAdapter = new UserListAdapter(getActivity(), users);
        userView.setAdapter(userAdapter);
        userView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                User user = users.get(position);
                intent.putExtra(ID, user.getId());
                intent.putExtra(FIRST_NAME, user.getFirstName());
                intent.putExtra(PHONE, user.getPhone());
                intent.putExtra(EMAIL, user.getEmail());
                startActivity(intent);
            }
        });
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshList();
            }
        });
		return selectView;
	}

    private void refreshList() {
        if (userView.getAdapter() == null) {
            UserListAdapter userAdapter = new UserListAdapter(getActivity(), users);
            userView.setAdapter(userAdapter);
        } else {
            ((UserListAdapter)userView.getAdapter()).refill(users);
        }
        SelectOperationTask selectOperationTask = new SelectOperationTask();
        selectOperationTask.execute();
        try {
            users = selectOperationTask.get();
        } catch (InterruptedException e) {
            showToast(getResources().getString(R.string.application_error));
        } catch (ExecutionException e) {
            showToast(getResources().getString(R.string.application_error));
        }
        userView.setAdapter(new UserListAdapter(getActivity(), users));
    }

    private class SelectOperationTask extends AsyncTask<Void, Void, List<User>> {
        DataServiceHelper dataServiceHelper = new DataServiceHelper(getActivity());

        @Override
        protected List<User> doInBackground(Void... params) {
            return dataServiceHelper.getAllUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
        }
    }

    private void showToast(String textToShow) {
        Toast.makeText(getActivity(), textToShow, Toast.LENGTH_SHORT).show();
    }
}
