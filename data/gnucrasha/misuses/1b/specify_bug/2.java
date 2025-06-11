public class PasscodeLockScreenActivity extends SherlockFragmentActivity
        implements KeyboardFragment.OnPasscodeEnteredListener {

    private static final String TAG = "PasscodeLockScreenActivity";

    @Override
    public void onPasscodeEntered(String pass) {
        String passcode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getString(UxArgument.PASSCODE, "");
        Log.d(TAG, "Passcode: " + passcode);

        if (passcode.equals(pass)) {
            GnuCashApplication.PASSCODE_SESSION_INIT_TIME = System.currentTimeMillis();
            Intent sourceIntent = getIntent();
            String callerClass = sourceIntent.getStringExtra(UxArgument.PASSCODE_CLASS_CALLER);
            if (callerClass != null) {
                Intent intent = new Intent();
                intent.setClassName(this, callerClass);
                intent.setAction(sourceIntent.getAction());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(UxArgument.SELECTED_ACCOUNT_UID,
                        sourceIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L));
                startActivity(intent);
            } else {
                Log.e(TAG, "Caller class name not provided in Intent extras.");
                Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
        }
    }
}
