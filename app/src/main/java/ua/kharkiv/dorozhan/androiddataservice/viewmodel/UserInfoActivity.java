package ua.kharkiv.dorozhan.androiddataservice.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ua.kharkiv.dorozhan.androiddataservice.R;

public class UserInfoActivity extends AppCompatActivity {
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        TextView idTextView = (TextView) findViewById(R.id.textViewIdUser);
        TextView firstNameTextView = (TextView) findViewById(R.id.textViewFirstName);
        TextView phoneTextView = (TextView) findViewById(R.id.textViewPhone);
        TextView emailTexView = (TextView) findViewById(R.id.textViewEmail);
        Intent intent = getIntent();
        String id = String.valueOf(intent.getIntExtra(ID, 0));
        String firstName = intent.getStringExtra(FIRST_NAME);
        String email = intent.getStringExtra(EMAIL);
        String phone = String.valueOf(intent.getIntExtra(PHONE, 0));
        idTextView.setText(id);
        firstNameTextView.setText(firstName);
        phoneTextView.setText(phone);
        emailTexView.setText(email);
    }
}

