public class PasscodeLockScreenActivity extends androidx.appcompat.app.AppCompatActivity
        implements KeyboardFragment.OnPasscodeEnteredListener {

    private static final String TAG = "PasscodeLockScreenActivity";

    @Override
    public void onPasscodeEntered(String pass) {
        String passcode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getString(UxArgument.PASSCODE, "");
        Log.d(TAG, "Passcode: " + passcode);

        if (passcode.equals(pass)) {
            GnuCashApplication.PASSCODE_SESSION_INIT_TIME = System.currentTimeMillis();
            Intent callerIntent = getIntent();
            Intent newIntent = new Intent()
                    .setClassName(this, callerIntent.getStringExtra(UxArgument.PASSCODE_CLASS_CALLER))
                    .setAction(callerIntent.getAction())
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .putExtra(UxArgument.SELECTED_ACCOUNT_UID, callerIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L));
            startActivity(newIntent);
        } else {
            Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
        }
    }
}
