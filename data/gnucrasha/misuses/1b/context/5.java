import android.content.Intent;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PasscodeLockScreenActivity extends AppCompatActivity
        implements KeyboardFragment.OnPasscodeEnteredListener {

    private static final String TAG = "PasscodeLockScreenActivity";

    @Override
    public void onPasscodeEntered(String pass) {
        String passcode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getString(UxArgument.PASSCODE, "");
        Log.d(TAG, "Passcode: " + passcode);

        if (TextUtils.equals(passcode, pass)) {
            GnuCashApplication.PASSCODE_SESSION_INIT_TIME = System.currentTimeMillis();
            Intent originalIntent = getIntent();
            startActivity(new Intent()
                    .setClassName(this, originalIntent.getStringExtra(UxArgument.PASSCODE_CLASS_CALLER))
                    .setAction(originalIntent.getAction())
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .putExtra(UxArgument.SELECTED_ACCOUNT_UID,
                            originalIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L))
            );
        } else {
            Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
        }
    }
}
