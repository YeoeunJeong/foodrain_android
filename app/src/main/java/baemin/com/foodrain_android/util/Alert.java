package baemin.com.foodrain_android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Alert {


    public Alert() {
    }

    public AlertDialog.Builder alertDialog(final Context mContext) {
        return new AlertDialog.Builder(mContext)
                .setMessage("잠시후 다시 시도해주세요")
                .setPositiveButton("나가기", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }
                )
                .setNegativeButton("머물기", null);
    }

//    public static final String TAG = MyClass.class.getSimpleName();
}
