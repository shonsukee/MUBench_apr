import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PassLockActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (sharedPreferences.getBoolean(UxArgument.ENABLED_PASSCODE, false) && !isSessionActive()) {
            Intent currentIntent = getIntent();
            Intent passcodeIntent = new Intent(this, PasscodeLockScreenActivity.class);
            if (currentIntent != null && currentIntent.getAction() != null) {
                passcodeIntent.setAction(currentIntent.getAction());
            }
            passcodeIntent.putExtra(UxArgument.PASSCODE_CLASS_CALLER, this.getClass().getName());
            long accountUid = (currentIntent != null) ? currentIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L) : 0L;
            passcodeIntent.putExtra(UxArgument.SELECTED_ACCOUNT_UID, accountUid);
            startActivity(passcodeIntent);
        }
    }
    
    private boolean isSessionActive() {
        // Implementation of session active check.
        return false;
    }
}
