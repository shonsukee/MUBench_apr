import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class PasscodeLockScreenActivity extends AppCompatActivity
        implements KeyboardFragment.OnPasscodeEnteredListener {

    private static final String TAG = "PasscodeLockScreenActivity";

    @Override
    public void onPasscodeEntered(String pass) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String passcode = prefs.getString(UxArgument.PASSCODE, "");
        Log.d(TAG, "Passcode: " + passcode);

        if (passcode.equals(pass)) {
            GnuCashApplication.PASSCODE_SESSION_INIT_TIME = System.currentTimeMillis();
            Intent currentIntent = getIntent();
            String callerClass = currentIntent.getStringExtra(UxArgument.PASSCODE_CLASS_CALLER);
            String action = currentIntent.getAction();
            long selectedAccountUid = currentIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L);

            Intent newIntent = new Intent()
                    .setClassName(this, callerClass)
                    .setAction(action)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .putExtra(UxArgument.SELECTED_ACCOUNT_UID, selectedAccountUid);
            startActivity(newIntent);
        } else {
            Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
        }
    }
}
