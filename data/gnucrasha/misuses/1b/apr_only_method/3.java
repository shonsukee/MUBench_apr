@Override
public void onPasscodeEntered(String pass) {
    String passcode = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
            .getString(UxArgument.PASSCODE, "");
    Log.d(TAG, "Passcode: " + passcode);

    if (passcode.equals(pass)) {
        GnuCashApplication.PASSCODE_SESSION_INIT_TIME = System.currentTimeMillis();
        startActivity(new Intent()
                .setClassName(this, getIntent().getStringExtra(UxArgument.PASSCODE_CLASS_CALLER))
                .setAction(getIntent().getAction())
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra(UxArgument.SELECTED_ACCOUNT_UID, getIntent().getLongExtra(UxArgument.SELECTED_ACCOUNT_UID, 0L))
        );
    } else {
        Toast.makeText(this, R.string.toast_wrong_passcode, Toast.LENGTH_SHORT).show();
    }
}
