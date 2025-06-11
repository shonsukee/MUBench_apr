protected void onResume() {
    super.onResume();

    SharedPreferences sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    if (sharedPreferences.getBoolean(UxArgument.ENABLED_PASSCODE, false) && !isSessionActive()) {
        startActivity(new Intent(this, PasscodeLockScreenActivity.class)
                .setAction(getIntent().getAction())
                .putExtra(UxArgument.PASSCODE_CLASS_CALLER, this.getClass().getName())
                .putExtra(UxArgument.SELECTED_ACCOUNT_UID,
                        getIntent().getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L))
        );
    }
}
