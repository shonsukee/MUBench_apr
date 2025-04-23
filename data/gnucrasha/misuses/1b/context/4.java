public class PasscodeLockScreenActivity extends SherlockFragmentActivity
        implements KeyboardFragment.OnPasscodeEnteredListener {

    private static final String TAG = "PasscodeLockScreenActivity";

    @Override
    public void onPasscodeEntered(String pass) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String storedPasscode = prefs.getString(UxArgument.PASSCODE, "");
        Log.d(TAG, "Passcode: " + storedPasscode);

        if (storedPasscode.equals(pass)) {
            GnuCashApplication.PASSCODE_SESSION_INIT_TIME = System.currentTimeMillis();
            Intent currentIntent = getIntent();
            String targetClass = currentIntent.getStringExtra(UxArgument.PASSCODE_CLASS_CALLER);
            if (targetClass == null || targetClass.isEmpty()) {
                Log.e(TAG, "Target class for redirection is not provided.");
                Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent newIntent = new Intent();
            newIntent.setClassName(this, targetClass);
            newIntent.setAction(currentIntent.getAction());
            newIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            newIntent.putExtra(UxArgument.SELECTED_ACCOUNT_UID,
                    currentIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L));
            startActivity(newIntent);
        } else {
            Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
        }
    }
}
