package tcc.fatec.com.br.guiacomercialtcc.commons;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerArrayAdapter<T> extends ArrayAdapter<T> {

    private boolean haveBlank = false;

    public SpinnerArrayAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    public SpinnerArrayAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public final View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if (isHaveBlank()) {
            if (position == 0) {
                tv.setTextColor(Color.GRAY);
            } else {
                tv.setTextColor(Color.BLACK);
            }
        } else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        view.setPadding(0, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        return view;
    }

    public boolean isHaveBlank() {
        return haveBlank;
    }

}
