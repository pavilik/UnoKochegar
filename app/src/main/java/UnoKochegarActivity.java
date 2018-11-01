package ru.h1n.unokochegar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class UnoKochegarActivity extends AppCompatActivity {
    private List<Integer> datchiki = new LinkedList<>();
    public static final int MAXVALUE = 170;
    public static final int MINVALUE = 90;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uno_kochegar);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        final ru.h1n.unokochegar.CustomNumberPicker numberPicker0 = (ru.h1n.unokochegar.CustomNumberPicker) findViewById(R.id.numberPicker);
        final ru.h1n.unokochegar.CustomNumberPicker numberPicker1 = (ru.h1n.unokochegar.CustomNumberPicker) findViewById(R.id.numberPicker2);
        final ru.h1n.unokochegar.CustomNumberPicker numberPicker2 = (ru.h1n.unokochegar.CustomNumberPicker) findViewById(R.id.numberPicker2kotel);
        final NumberPicker numberPicker3 = (NumberPicker) findViewById(R.id.numberPicker22kotel);
        final NumberPicker numberPicker5 = (NumberPicker) findViewById(R.id.numberPicker23kotel);
        final NumberPicker numberPicker4 = (NumberPicker) findViewById(R.id.numberPicker33kotel);
        final NumberPicker numberPicker6 = (NumberPicker) findViewById(R.id.numberPicker4kotel);
        final NumberPicker numberPicker7 = (NumberPicker) findViewById(R.id.numberPicker24kotel);
        final TextView dataTimeLastUpdateBase = (TextView) findViewById(R.id.dataTimeLastBase);
        final TextView remarkdataTimeLastUpdateBase = (TextView) findViewById(R.id.remarkDataTimeLastBase);
        final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm:ss.SS");
        Date date = new Date();
         dataTimeLastUpdateBase.setText(dateFormat.format(date));


        //numberPicker0.setShowDividers(0);
      // numberPicker0.getChildAt(1);
          //  EditText edt=(EditText) numberPicker0.getChildAt(1);
            //edt.setTextSize (24);
           // edt.setAutoSizeTextTypeUniformWithConfiguration(24,40,1,1);
            //do customizations here

        //свитчи
        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        final Switch switch2 = (Switch) findViewById(R.id.switch2);
        final Switch switch3 = (Switch) findViewById(R.id.switch3);
        final Switch switch4 = (Switch) findViewById(R.id.switch4);
        switch1.setChecked(true);
        switch2.setChecked(true);
        switch3.setChecked(true);
        switch4.setChecked(true);
        //4й котел отключен по умолчанию
     //   numberPicker6.setEnabled(false);
      //  numberPicker7.setEnabled(false);

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked()){
                    numberPicker0.setEnabled(true);
                    numberPicker1.setEnabled(true);
                }
                else{
                    numberPicker0.setEnabled(false);
                    numberPicker1.setEnabled(false);

                    }
            }
        });

        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch2.isChecked()){
                    numberPicker2.setEnabled(true);
                    numberPicker3.setEnabled(true);
                }
                else{
                    numberPicker2.setEnabled(false);
                    numberPicker3.setEnabled(false);

                }
            }
        });

        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch3.isChecked()){
                    numberPicker4.setEnabled(true);
                    numberPicker5.setEnabled(true);
                }
                else{
                    numberPicker4.setEnabled(false);
                    numberPicker5.setEnabled(false);

                }
            }
        });

        switch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch4.isChecked()){
                    numberPicker6.setEnabled(true);
                    numberPicker7.setEnabled(true);
                }
                else{
                    numberPicker6.setEnabled(false);
                    numberPicker7.setEnabled(false);

                }
            }
        });


        //-------------------------------------------

        Button sendData = (Button) findViewById(R.id.button);

       final Button getData = (Button) findViewById(R.id.button2);




       //getData.setEnabled(false);

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DataFromKotels");
                DatabaseReference myRef2 = database.getReference("SaveDataTime");
                datchiki.clear();
                //получение последних данных с датчиков
                datchiki.add(numberPicker0.getValue());
                datchiki.add(numberPicker1.getValue());
                datchiki.add(numberPicker2.getValue());
                datchiki.add(numberPicker3.getValue());
                datchiki.add(numberPicker4.getValue());
                datchiki.add(numberPicker5.getValue());
                datchiki.add(numberPicker6.getValue());
                datchiki.add(numberPicker7.getValue());
                //*********конец получения данных с датчиков

                myRef.setValue(datchiki);


                Date date2 = new Date();
                myRef2.setValue(dateFormat.format(date2.getTime()));
                remarkdataTimeLastUpdateBase.setText("ОТПРАВИЛ В ТЫРНЕТ: ");

                getData.setEnabled(true);

            }
        });


        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ////чтение из базы********************************
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRefget = database.getReference("DataFromKotels");
                DatabaseReference myRefget2 = database.getReference("SaveDataTime");
                myRefget.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.


                        numberPicker0.setValue(dataSnapshot.child("0").getValue(Integer.class));
                        numberPicker1.setValue(dataSnapshot.child("1").getValue(Integer.class));
                        numberPicker2.setValue(dataSnapshot.child("2").getValue(Integer.class));
                        numberPicker3.setValue(dataSnapshot.child("3").getValue(Integer.class));
                        numberPicker4.setValue(dataSnapshot.child("4").getValue(Integer.class));
                        numberPicker5.setValue(dataSnapshot.child("5").getValue(Integer.class));
                        numberPicker6.setValue(dataSnapshot.child("6").getValue(Integer.class));
                        numberPicker7.setValue(dataSnapshot.child("7").getValue(Integer.class));

                       getData.setEnabled(false);



                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("HEY", "Failed to read value.", error.toException());
                    }


                });

                myRefget2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataTimeLastUpdateBase.setText(dataSnapshot.getValue(String.class));


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //************************

                remarkdataTimeLastUpdateBase.setText("ЗАГР. С ТЫРНЕТА: ");
            }
        });

        TextView textV1KDo = (TextView) findViewById(R.id.textView1kotelDo);
        TextView textV2Kposle = (TextView) findViewById(R.id.textView1kotelPosle);
///*******установка кнопки отмены в состояние "отмены"
        numberPicker0.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });

        numberPicker1.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });
        numberPicker2.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });
        numberPicker3.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });
        numberPicker4.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });
        numberPicker5.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });
        numberPicker6.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });
        numberPicker7.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                getData.setEnabled(true);


            }
        });

        ///*****************

        numberPicker0.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }
        });

        numberPicker1.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }
        });

        numberPicker2.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }
        });

        numberPicker3.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }
        });

        numberPicker4.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }

        });

        numberPicker0.invalidate();
        numberPicker0.setMaxValue(MAXVALUE);
        numberPicker0.setMinValue(MINVALUE);
        numberPicker0.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker0.setValue(155);


        //попытка исправить баг с первым значением пикера
//
//        try {
//            numberPicker0.getClass().getSuperclass().getDeclaredMethod("changeValueByOne", boolean.class);
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }

        ///********



        numberPicker1.setMaxValue(MAXVALUE);
        numberPicker1.setMinValue(MINVALUE);
        numberPicker1.setValue(135);

        numberPicker1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker1.invalidate();


        //22222222222222222222222*************2
        TextView textV2KDo = (TextView) findViewById(R.id.textView2kotelDo);
        TextView textV22Kposle = (TextView) findViewById(R.id.textView2kotelPosle);



        numberPicker2.setMaxValue(MAXVALUE);
        numberPicker2.setMinValue(MINVALUE);
        numberPicker2.setValue(155);
        numberPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker2.invalidate();



        numberPicker3.setMaxValue(MAXVALUE);
        numberPicker3.setMinValue(MINVALUE);
        numberPicker3.setValue(135);
        numberPicker3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker3.invalidate();
        //**********

        //33333333333333333333333333333333333
        TextView textV3KDo = (TextView) findViewById(R.id.textView3kotelDo);
        TextView textV23Kposle = (TextView) findViewById(R.id.textView3kotelPosle);



        numberPicker4.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker4.setMaxValue(MAXVALUE);
        numberPicker4.setMinValue(MINVALUE);
        numberPicker4.setValue(135);
        numberPicker4.invalidate();


        numberPicker5.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }
        });
        numberPicker5.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker5.setMaxValue(MAXVALUE);
        numberPicker5.setMinValue(MINVALUE);
        numberPicker5.setValue(155);
        numberPicker5.invalidate();
        //**********

        //44444444444444444444444444444
        TextView textV4KDo = (TextView) findViewById(R.id.textView4kotelDo);
        TextView textV24Kposle = (TextView) findViewById(R.id.textView4kotelPosle);


        numberPicker6.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }
        });
        numberPicker6.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker6.setMaxValue(MAXVALUE);
        numberPicker6.setMinValue(MINVALUE);
        numberPicker6.setValue(135);
        numberPicker6.invalidate();


        numberPicker7.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%d.%d", i / 10, i % 10);
            }
        });
        numberPicker7.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker7.setMaxValue(MAXVALUE);
        numberPicker7.setMinValue(MINVALUE);
        numberPicker7.setValue(155);
        numberPicker7.invalidate();
        //**********

///убираем зеленые цифры

        textV1KDo.setVisibility(View.INVISIBLE);
        textV2Kposle.setVisibility(View.INVISIBLE);

        textV2KDo.setVisibility(View.INVISIBLE);
        textV22Kposle.setVisibility(View.INVISIBLE);

        textV3KDo.setVisibility(View.INVISIBLE);
        textV23Kposle.setVisibility(View.INVISIBLE);

        textV4KDo.setVisibility(View.INVISIBLE);
        textV24Kposle.setVisibility(View.INVISIBLE);
        ///

        TextView textKotel1 = (TextView) findViewById(R.id.textView1Kotel);
        TextView textKotel2 = (TextView) findViewById(R.id.textView1Kotel2);
        TextView textKotel3 = (TextView) findViewById(R.id.textView1Kotel3);
        TextView textKotel4 = (TextView) findViewById(R.id.textView1Kotel4);


//****************


    }

}
