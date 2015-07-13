package sittikun.r.co.th.compairor;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.util.ArrayList;

/**
 * Created by sittikun on 29/6/2558.
 */
public class CompairAdapter extends ArrayAdapter<CompairItem>{


    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;
    enum Direction {LEFT, RIGHT;}

    ArrayList<CompairItem> compirItems;
    CompairAdapter currAdapter;

    public CompairAdapter(Context context,ArrayList<CompairItem> compirItems){
        super(context, 0, compirItems);
        this.compirItems = compirItems;
        currAdapter = this;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        CompairItem compairItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.compaire_item, parent, false);
        }


        TextView tvCpItemPrice = (TextView) convertView.findViewById(R.id.tvCpItemPrice);
        TextView tvCpItemUnit = (TextView) convertView.findViewById(R.id.tvCpItemUnit);
        TextView tvCpItemPricePerUnit = (TextView) convertView.findViewById(R.id.tvCpItemPricePerUnit);
        //TextView tvSSS = (TextView) convertView.findViewById(R.id.tvSSS);
       /* Button btClose = (Button) convertView.findViewById(R.id.btClose);
        btClose.setTag(""+position);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String index = (String) v.getTag();

                System.out.print("remove : "+index);

                //compirItems.clear();


                //currAdapter.remove(Integer.parseInt(index));
            }
        });*/


/*        tvCpItemPrice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.print("BEFORE");
                SnackbarManager.show(
                        Snackbar.with(getContext())
                                .text("FIRST1112222"));
                System.out.print("AFTER");

                return true;
            }
        });*/

        /*tvCpItemPrice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                System.out.println("======TOUCHHH=======" + event.getAction());
                System.out.println("Index : "+v.getTag());

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        System.out.println("======douwn=======");
                        historicX = event.getX();
                        historicY = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        System.out.println("======UP=======x:"+historicX+",getX"+event.getX());
                        if (event.getX() - historicX < -DELTA) {
                            //FunctionDeleteRowWhenSlidingLeft();
                            System.out.println("<<<<<======SLIDE LEFT=======");
                            return true;
                        } else if (event.getX() - historicX > DELTA) {
                            System.out.println("======SLIDE RIGHT=======>>>>>");
                            //FunctionDeleteRowWhenSlidingRight();
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
*/


        Log.i("Myapp", "PERCENT : " + (float) position / currAdapter.getCount());
        Log.i("Myapp", "ArgbEvaluator : " + new ArgbEvaluator().evaluate((float) position / (float) currAdapter.getCount(), 0xFF00AC00 , Color.RED));
        tvCpItemPricePerUnit.setTextColor((Integer) new ArgbEvaluator().evaluate((float) position / currAdapter.getCount(), 0xFF00AC00 , Color.RED));

        if(position != 0){
            tvCpItemPrice.setTextColor(Color.BLACK);
            tvCpItemUnit.setTextColor(Color.BLACK);
            //tvSSS.setVisibility(View.INVISIBLE);

        }else{
            tvCpItemPrice.setTextColor((Integer) new ArgbEvaluator().evaluate((float) position / currAdapter.getCount(), 0xFF00AC00 , Color.RED));
            tvCpItemUnit.setTextColor((Integer) new ArgbEvaluator().evaluate((float) position / currAdapter.getCount(), 0xFF00AC00, Color.RED));
            //tvSSS.setVisibility(View.VISIBLE);
        }


        tvCpItemPrice.setText(compairItem.price);
        tvCpItemUnit.setText(compairItem.unit);
        tvCpItemPricePerUnit.setText(String.format("%1$,.2f", compairItem.getPricePerUnit()));

        return convertView;
    }

}