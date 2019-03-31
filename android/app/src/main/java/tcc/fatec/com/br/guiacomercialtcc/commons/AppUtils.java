package tcc.fatec.com.br.guiacomercialtcc.commons;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public abstract class AppUtils {

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, LinearLayout.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String getFacebookPageURL(Context context, String url) {
        String split[] = url.split("https://www.facebook.com/");
        String id = split[1];

        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + url;
            } else {
                return "fb://page/" + id;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return url;
        }
    }

    public static boolean isEmpty(EditText editText) {
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    public static void setError(EditText editText, String errorString) {
        editText.setError(errorString);
    }

    public static void clearError(EditText editText) {
        editText.setError(null);
    }

}
