public class PasscodeLockScreenActivity extends AppCompatActivity
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
            String callerClass = callerIntent.getStringExtra(UxArgument.PASSCODE_CLASS_CALLER);
            String action = callerIntent.getAction();
            long selectedAccountUid = callerIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L);

            Intent intent = new Intent();
            intent.setClassName(this, callerClass);
            intent.setAction(action);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(UxArgument.SELECTED_ACCOUNT_UID, selectedAccountUid);

            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
        }
    }
}
