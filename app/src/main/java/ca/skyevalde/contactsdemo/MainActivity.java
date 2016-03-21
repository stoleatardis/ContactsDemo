package ca.skyevalde.contactsdemo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //http://code.tutsplus.com/tutorials/android-essentials-using-the-contact-picker--mobile-2017
    //also
    //http://stackoverflow.com/questions/3044545/get-contact-info-from-android-contact-picker
    //and
    //http://stackoverflow.com/questions/12714701/deprecated-managedquery-issue



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private static final int CONTACT_PICKER_RESULT = 1001;

    public void doLaunchContactPicker(View view) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri contactData = data.getData();

            Cursor c = getContentResolver().query(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                Log.d("SKYEDEMO", "Result: " + name);

            }

        } else {
            // gracefully handle failure
            Log.d("SKYEDEMO", "Warning: activity result not ok");
        }
    }

}
