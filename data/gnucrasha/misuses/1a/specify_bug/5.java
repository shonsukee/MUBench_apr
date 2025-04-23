import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class PassLockActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sharedPreferences.getBoolean(UxArgument.ENABLED_PASSCODE, false) && !isSessionActive()) {
            Intent currentIntent = getIntent();
            Intent intent = new Intent(this, PasscodeLockScreenActivity.class);
            if (currentIntent != null) {
                intent.setAction(currentIntent.getAction())
                        .putExtra(UxArgument.PASSCODE_CLASS_CALLER, this.getClass().getName())
                        .putExtra(UxArgument.SELECTED_ACCOUNT_UID, currentIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L));
            }
            startActivity(intent);
        }
    }

    private boolean isSessionActive() {
        // Implement session check logic here.
        return false;
    }
}
