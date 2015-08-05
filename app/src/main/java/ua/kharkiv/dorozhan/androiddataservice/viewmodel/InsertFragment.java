package ua.kharkiv.dorozhan.androiddataservice.viewmodel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.kharkiv.dorozhan.androiddataservice.R;
import ua.kharkiv.dorozhan.androiddataservice.db.DataServiceHelper;
import ua.kharkiv.dorozhan.androiddataservice.model.User;

public class InsertFragment extends Fragment {
    private EditText firstNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View insertView = inflater.inflate(R.layout.fragment_insert, container, false);
        Button insertButton = (Button) insertView.findViewById(R.id.insertButton);
        firstNameEditText = (EditText) insertView.findViewById(R.id.editTextFirstName);
        phoneEditText = (EditText) insertView.findViewById(R.id.editTextPhone);
        emailEditText = (EditText) insertView.findViewById(R.id.editTextEmail);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                if (isValidFirstName(firstName) && isValidPhone(phone) && isValidEmail(email)) {
                    User user = new User(firstName, Integer.valueOf(phone), email);
                    InsertOperationTask insertOperationTask = new InsertOperationTask();
                    insertOperationTask.execute(user);
                    boolean success = false;
                    try {
                        success = insertOperationTask.get();
                    } catch (InterruptedException e) {
                        showToast(getResources().getString(R.string.application_error));
                    } catch (ExecutionException e) {
                        showToast(getResources().getString(R.string.application_error));
                    }
                    if (success) {
                        showToast(getResources().getString(R.string.saved_user_info));
                        firstNameEditText.setText("");
                        phoneEditText.setText("");
                        emailEditText.setText("");
                    } else {
                        showToast(getResources().getString(R.string.unable_to_save_user_info));
                    }
                }
            }
        });
        return insertView;
	}

    private class InsertOperationTask extends AsyncTask<User, Void, Boolean> {
        DataServiceHelper dataServiceHelper = new DataServiceHelper(getActivity());

        @Override
        protected Boolean doInBackground(User... params) {
            User user = null;
            if (params.length > 0) {
                user = params[0];
            }
            return dataServiceHelper.addUser(user);
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
        }
    }

    private boolean isValidFirstName(String firstName) {
        if (firstName.equals("")) {
            showToast(getResources().getString(R.string.enter_first_name));
            return false;
        }
        return true;
    }

    private boolean isValidPhone(String phone) {
        if (phone.equals("")) {
            showToast(getResources().getString(R.string.enter_phone));
            return false;
        } else {
            try {
                int phoneNumber = Integer.valueOf(phone);
                return true;
            } catch (NumberFormatException e) {
                showToast(getResources().getString(R.string.wrong_phone_number));
                return false;
            }
        }
    }

    private boolean isValidEmail(String email) {
        if (email.equals("")) {
            showToast(getResources().getString(R.string.enter_email));
            return false;
        } else {
            String regExp = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
            Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            }
            else {
                showToast(getResources().getString(R.string.wrong_email));
                return false;
            }
        }
    }

    private void showToast(String textToShow) {
        Toast.makeText(getActivity(), textToShow, Toast.LENGTH_SHORT).show();
    }
}
