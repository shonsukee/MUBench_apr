## Instruction
You are a software engineer specializing in REST API.
Use the guidelines below to make any necessary modifications.

### Modification Procedure
0. First, familiarise yourself with the following steps and ### Notes.
1. Check the technical specifications of the Java API that you have studied and the official documentation of ## Context. If you don't know, output the ### Input Code as it is.
2. Based on the technical specifications of the Java API you have reviewed in step 1, identify the code according to the deprecated specifications contained in the ### Input Code. In this case, the deprecated specifications are the Java API calls that have been deprecated. If no code according to the deprecated specification is found, identify code with maintainability issues. If you are not sure, output the ### Input Code as it is.
3. If you find code according to the deprecated specification or maintainability issues in step 2, check the technical specifications in the Java API that you have studied and the official documentation of ## Context. If you are not sure, output the ### Input Code as it is.
4. With attention to the points listed in ### Notes below, modify the code identified in step 2 to follow the recommended specification analysed in step 3.
5. Verify again that the modified code works correctly.
6. If you determine that it works correctly, output the modified code.
7. If it is judged to fail, output the ### Input Code as it is.
8. If you are not sure, output the ### Input Code as it is.

### Notes.
- You must follow the ## Context.
- `### Java API documentation` is official documentation and must follow.
- Be sure to fix any maintainability issues that exist.
- If Java API documentation include throws Exception, it must be handled.
- Bug exists in line 14 of the `## Input code`.

## Input Code
```java
    private void viewImageInStandardApp(final BitmapDrawable image) {
        final File file = LocalStorage.getStorageFile(null, "temp.jpg", false, true);
        try {
            final FileOutputStream fos = new FileOutputStream(file);
            image.getBitmap().compress(CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (Exception e) {
            Log.e(Settings.tag, "cgeoimages.handleMessage.onClick: " + e.toString());
            return;
        }

        final Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "image/jpg");
        startActivity(intent);

        if (file.exists()) {
            file.deleteOnExit();
        }
    }
```

## Context
```
Here's a bit of code which works fine on phones:

Intent intent = new Intent();
intent.setAction(Intent.ACTION_VIEW);
intent.setDataAndType(IMAGEURI, "image/jpg");
startActivity(intent);
which on Honeycomb (3.0 and 3.1) throws this:

E/AndroidRuntime(25629): FATAL EXCEPTION: main
E/AndroidRuntime(25629): android.content.ActivityNotFoundException: No Activity found to handle Intent { act=android.intent.action.VIEW dat=content://media/external/images/media/282 typ=image/jpg }
Really? There's no application to view jpg on honeycomb? Really? Has anyone been able to do this properly on a tablet?

The MIME type for JPEG is image/jpeg. You have image/jpg.
```

## Output Indicator
Update the ### Input  Code as per the latest API specification, making necessary modifications.
Ensure the structure and format remain as close as possible to the original, but deprecated code must be updated. Output the all revised code without additional explanations or comments.
