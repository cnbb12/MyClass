package com.example.a95797.myclass.module.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class CheckPermissionsActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback
{
    private static final int PERMISSON_REQUESTCODE = 0;
    private boolean isNeedCheck = true;
    protected String[] needPermissions = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE" };

    private void checkPermissions(String[] paramVarArgs)
    {
        List<String> param = findDeniedPermissions(paramVarArgs);
        if ((param != null) && (param.size() > 0)) {
            ActivityCompat.requestPermissions(this, (String[])param.toArray(new String[param.size()]), 0);
        }
    }

    private List<String> findDeniedPermissions(String[] paramArrayOfString)
    {
        ArrayList localArrayList = new ArrayList();

        int j = paramArrayOfString.length;

        for(int i = 0; i < j;i++)
        {
            String str = paramArrayOfString[i];
            if (ContextCompat.checkSelfPermission(this, str) != PackageManager.PERMISSION_GRANTED){

               if (!ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                   continue ;
               }
            }
            localArrayList.add(str);

        }
        return localArrayList;
    }

    private void showMissingPermissionDialog()
{

//    AlertDialog dialog = new AlertDialog.Builder(this,R.style.orderdialog)
//            .setTitle(R.string.notifyTitle )
//            .setMessage(R.string.notifyMsg )
//            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
//            {
//                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//                {
//                    CheckPermissionsActivity.this.finish();
//                }
//            }).setPositiveButton(R.string.setting, new DialogInterface.OnClickListener()
//            {
//                public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//                {
//                    CheckPermissionsActivity.this.startAppSettings();
//                }
//            }).setCancelable(false).show();


//    try {
//        Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
//        mAlert.setAccessible(true);
//        Object mAlertController = mAlert.get(dialog);
//        Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
//        mMessage.setAccessible(true);
//        TextView mMessageView = (TextView) mMessage.get(mAlertController);
//        //设置内容的颜色为绿色
//        mMessageView.setTextColor(0xff4cae50);
//        mMessageView.setTextSize(24);
//    } catch (IllegalAccessException e) {
//        e.printStackTrace();
//    } catch (NoSuchFieldException e) {
//        e.printStackTrace();
//    }

}


    private void startAppSettings()
    {
        Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(localIntent);
    }


    private boolean verifyPermissions(int[] paramArrayOfInt)
    {
        for (int i=0;i<paramArrayOfInt.length;i++){
            if (paramArrayOfInt[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
    {
        if (paramInt == 4)
        {
            finish();
            return true;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    @Override
    public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    {
        if ((paramInt == PERMISSON_REQUESTCODE) && (!verifyPermissions(paramArrayOfInt)))
        {
            showMissingPermissionDialog();
            this.isNeedCheck = false;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (this.isNeedCheck) {
            checkPermissions(this.needPermissions);
        }
    }
}
