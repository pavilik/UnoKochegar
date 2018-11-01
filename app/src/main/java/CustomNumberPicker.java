package ru.h1n.unokochegar;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Павел on 19.10.2017.
 */
public class CustomNumberPicker extends NumberPicker {



    public CustomNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }
//потуги исправления первого значения пикеров
//
//    private void changeValueByOne(final NumberPicker higherPicker, final boolean increment) {
//
//        Method method;
//        try {
//            // refelction call for
//            // higherPicker.changeValueByOne(true);
//            method = higherPicker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
//            method.setAccessible(true);
//            method.invoke(higherPicker, increment);
//
//        } catch (final NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (final IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (final IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (final InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
///----------

    private void updateView(View view) {
        if (view instanceof EditText) {
            ((EditText) view).setTextSize(20);
            ((EditText) view).setLineSpacing(30,100);
                        ((EditText) view).setTextColor(Color.BLACK);

            Typeface face=Typeface.createFromAsset(getContext().getAssets(),

                    "fonts/10763.otf");
            // "fonts/lcdnova.ttf");
            //  "fonts/impact.ttf");
            // 362_LCDNOVA
            //((EditText) view).setTypeface(Typeface.SANS_SERIF);
            ((EditText) view).setTypeface(face);

        }
    }



}
