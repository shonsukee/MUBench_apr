package org.gnucash.android.ui.passcode;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;

import org.gnucash.android.app.GnuCashApplication;
import org.gnucash.android.ui.UxArgument;

/**
 * This activity used as the parent class for enabling passcode lock
 *
 * @author Oleksandr Tyshkovets <olexandr.tyshkovets@gmail.com>
 * @see org.gnucash.android.ui.account.AccountsActivity
 * @see org.gnucash.android.ui.transaction.TransactionsActivity
 */
public class PassLockActivity extends AppCompatActivity {

    private static final String TAG = "PassLockActivity";

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean(UxArgument.ENABLED_PASSCODE, false) && !isSessionActive()) {
            startActivity(new Intent(this, PasscodeLockScreenActivity.class)
                    .setAction(getIntent().getAction())
                    .putExtra(UxArgument.PASSCODE_CLASS_CALLER, this.getClass().getName())
                    .putExtra(UxArgument.SELECTED_ACCOUNT_UID,
                            getIntent().getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L))
            );
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        GnuCashApplication.PASSCODE_SESSION_INIT_TIME = System.currentTimeMillis();
    }

    private boolean isSessionActive() {
        return System.currentTimeMillis() - GnuCashApplication.PASSCODE_SESSION_INIT_TIME
                < GnuCashApplication.SESSION_TIMEOUT;
    }

}
