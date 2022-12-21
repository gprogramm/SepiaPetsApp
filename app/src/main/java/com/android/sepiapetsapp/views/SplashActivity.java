package com.android.sepiapetsapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.sepiapetsapp.R;
import com.android.sepiapetsapp.utils.DateUtils;
import com.android.sepiapetsapp.utils.ReadJsonFiles;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class is for splash screen and for some necessary checks, right now not using any splash image or animation.
 * And for the time being, i'm only considering working hours from config not the days like 'M-F'.
 */
public class SplashActivity extends AppCompatActivity implements CustomAlertDialog.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check that user is arriving on application during the working hours, if not then
        // show the alert dialog and force user to exit the application.
        try {
            String[] workingHours = getToAndFromWorkingHours();
            if (workingHours != null && !DateUtils.checkCurrentTimeInBetweenDates(workingHours[0], workingHours[1])) {
                CustomAlertDialog.getInstance().show(this, this);
                return;
            }
        } catch (StringIndexOutOfBoundsException e) {e.printStackTrace();}

        // Navigate to main activity
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     * Split Hours and Minutes from working hours string getting from config file.
     * @return
     */
    private String[] getToAndFromWorkingHours() throws StringIndexOutOfBoundsException {
        String workingHours = ReadJsonFiles.getInstance().fetchSettings(this).getSettings().getWorkHours();
        if(!TextUtils.isEmpty(workingHours)) {
            return workingHours.substring(4).split("-");
        } else {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // To avoid the memory leaks, dismiss dialog(if still showing) while activity is destroying
        if (CustomAlertDialog.getInstance() != null) {
            CustomAlertDialog.getInstance().dismiss();
        }
    }

    @Override
    public void onDismiss() {
        finish();
    }
}