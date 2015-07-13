package sittikun.r.co.th.compairor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;

import java.util.ArrayList;

import sittikun.r.co.th.compairor.fillter.DecimalDigitsInputFilter;


public class MainActivity extends Activity implements View.OnClickListener{

    EditText etPrice;
    EditText etUnit;
    ButtonRectangle btAdd;
    ButtonRectangle btClear;
    ListView lvCompareItem;
    CompairAdapter itemsAdapter;
    ArrayList<CompairItem> compairItemArraylist = new ArrayList<CompairItem>();


    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;
    enum Direction {LEFT, RIGHT;}


    //new ArgbEvaluator().evaluate(0.75, 0x00ff00, 0xff0000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPrice = (EditText)findViewById(R.id.price);
        etUnit = (EditText)findViewById(R.id.unit);
        btAdd = (ButtonRectangle)findViewById(R.id.btAdd);
        btClear = (ButtonRectangle)findViewById(R.id.btClear);

        btAdd.setOnClickListener(this);
        btClear.setOnClickListener(this);


        etPrice.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});


        lvCompareItem = (ListView)findViewById(R.id.lvCompareItem);


        etUnit.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    doAddBt();

                    return true;
                }
                return false;
            }
        });





        //ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.compaire_item, items);
        itemsAdapter = new CompairAdapter(this, compairItemArraylist);

        //CompairItem coItem1 = new CompairItem("50","2");
        //itemsAdapter.add(coItem1);

/*        CompairItem coItem2 = new CompairItem("60","2");
        itemsAdapter.add(coItem2);

        CompairItem coItem3 = new CompairItem("70","2");
        itemsAdapter.add(coItem3);

        CompairItem coItem4 = new CompairItem("80","2");
        itemsAdapter.add(coItem4);*/

        lvCompareItem.setAdapter(itemsAdapter);


       /* lvCompareItem.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                System.out.println("======LV_TOUCHHH=======");


                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        System.out.println("======LV_douwn=======");
                        historicX = event.getX();
                        historicY = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        System.out.println("======LV_UP=======x:"+historicX+",getX"+event.getX());
                        if (event.getX() - historicX < -DELTA) {
                            //FunctionDeleteRowWhenSlidingLeft();
                            System.out.println("<<<<<======LV_SLIDE LEFT=======");
                            return true;
                        } else if (event.getX() - historicX > DELTA) {
                            System.out.println("======LV_SLIDE RIGHT=======>>>>>");
                            //FunctionDeleteRowWhenSlidingRight();
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });*/


        SnackbarManager.show(
                Snackbar.with(this)
                        .text("Wellcome ^-^"));









        lvCompareItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
                SnackbarManager.show(
                        Snackbar.with(MainActivity.this)
                                .text("Item " + (id + 1) + " Selected.")
                                .actionLabel("Remove") // action button label
                                .actionColor(Color.RED) // action button label color
                                        // .actionLabelTypeface(myTypeface) // change the action button font
                                .actionListener(new ActionClickListener() {
                                    @Override
                                    public void onActionClicked(Snackbar snackbar) {
                                        System.out.println("xxxx" + (int) id);
                                        //itemsAdapter.re\
                                        System.out.println("before : " + compairItemArraylist);
                                        compairItemArraylist.remove((int) id);
                                        System.out.println("after : " + compairItemArraylist);
                                        MainActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                System.out.println("before : Notify");
                                                itemsAdapter.notifyDataSetChanged();

                                                System.out.println("After : Notify");
                                            }
                                        });


                                    }
                                }) // action button's ActionClickListener

                );

            }
        });


    }




    @Override
    public void onClick(View v) {

        System.out.print("click");
        Log.i("Myapp","logClick");
        switch (v.getId()) {

            case R.id.btAdd:
                doAddBt();

                break;

            case R.id.btClear:
                itemsAdapter.clear();
                break;
            default:
                break;
        }



        //System.out.println("testtttttttttttttttttttttttt================CLICK");
    }


    public void doAddBt(){
        try{
            Double.parseDouble(etPrice.getText().toString());
            Integer.parseInt(etUnit.getText().toString());
            CompairItem newCompairItem = new CompairItem(etPrice.getText().toString(), etUnit.getText().toString());

            if(itemsAdapter.getPosition(newCompairItem) == -1) {
                itemsAdapter.insert(new CompairItem(etPrice.getText().toString(), etUnit.getText().toString()), 0);
                etPrice.setText("");
                etUnit.setText("");
            }else{
                SnackbarManager.show(
                        Snackbar.with(MainActivity.this)
                                .text("Duplicate Item!!!"));
            }

            itemsAdapter.sort(new CompairItemComparator());
            System.out.print("itemsAdapter.getCount : "+itemsAdapter.getCount());

                    /*for(int i=0 ; i < itemsAdapter.getCount() ; i++){
                        itemsAdapter.getItem(i).
                    }*/

        }catch (Exception e){

        }
    }


}
