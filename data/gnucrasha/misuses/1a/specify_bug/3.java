public class PassLockActivity extends SherlockFragmentActivity {
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Intent originalIntent = getIntent();
        if (sharedPreferences.getBoolean(UxArgument.ENABLED_PASSCODE, false) && !isSessionActive()) {
            Intent lockIntent = new Intent(this, PasscodeLockScreenActivity.class)
                    .setAction(originalIntent.getAction())
                    .putExtra(UxArgument.PASSCODE_CLASS_CALLER, this.getClass().getName())
                    .putExtra(UxArgument.SELECTED_ACCOUNT_UID, originalIntent.getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L));
            startActivity(lockIntent);
        }
    }
}
