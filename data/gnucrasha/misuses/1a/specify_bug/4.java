public class PassLockActivity extends SherlockFragmentActivity {
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent intent = getIntent();
        if (sharedPreferences.getBoolean(UxArgument.ENABLED_PASSCODE, false) && !isSessionActive()) {
            startActivity(new Intent(this, PasscodeLockScreenActivity.class)
                    .setAction(intent.getAction())
                    .putExtra(UxArgument.PASSCODE_CLASS_CALLER, this.getClass().getName())
                    .putExtra(UxArgument.SELECTED_ACCOUNT_UID,
                            intent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L))
            );
        }
    }
}
