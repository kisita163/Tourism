package com.kisita.tourism.util;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kisita.tourism.MainActivity;
import com.kisita.tourism.R;


/**
 * Created by Hugues on 08/09/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {
    private int[][] image_resources = {{R.string.kinshasa, R.drawable.kinshasa},
                                       {R.string.sud_kivu,R.drawable.kwilu},
                                       {R.string.sud_kivu,R.drawable.kwango},
                                       {R.string.sud_kivu,R.drawable.nord_ubangi},
                                       {R.string.sud_kivu,R.drawable.sud_ubangi},
                                       {R.string.sud_kivu,R.drawable.equateur},
                                       {R.string.sud_kivu,R.drawable.mongala},
                                       {R.string.sud_kivu,R.drawable.nord_kivu},
                                       {R.string.sud_kivu,R.drawable.sud_kivu},
                                       {R.string.sud_kivu,R.drawable.kasai},
                                       {R.string.sud_kivu,R.drawable.kasai_central},
                                       {R.string.sud_kivu,R.drawable.kasai_oriental},
                                       {R.string.sud_kivu,R.drawable.haut_katanga},
                                       {R.string.sud_kivu,R.drawable.tanganyka},
                                       {R.string.sud_kivu,R.drawable.maniema},
                                       {R.string.sud_kivu,R.drawable.mai_ndombe},
                                       {R.string.sud_kivu,R.drawable.tshopo},
                                       {R.string.sud_kivu,R.drawable.tshuapa},
                                       {R.string.sud_kivu,R.drawable.lomami},
                                       {R.string.sud_kivu,R.drawable.haut_lomami},
                                       {R.string.sud_kivu,R.drawable.sankuru},
                                       {R.string.sud_kivu,R.drawable.bas_uele},
                                       {R.string.sud_kivu,R.drawable.ituri},
                                       {R.string.sud_kivu,R.drawable.lualaba},
                                       {R.string.sud_kivu,R.drawable.sankuru},
                                       {R.string.kongo_central,R.drawable.kongo}};
    private Context context;
    private LayoutInflater layoutInflater;
    View item_view;
    //private ImageView imageView;


    public CustomSwipeAdapter(Context context)
    {
        this.context = context;
    }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        System.out.println("instantiateItem");
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        item_view = (View)layoutInflater.inflate(R.layout.swipe_layout, container, false);
        TextView textView = (TextView)item_view.findViewById(R.id.text_view);
        final ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println("position = " + image_resources[position][0]);

                imageView.setColorFilter(R.color.black_overlay);

                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("ACTIVITY", image_resources[position][0]);
                context.startActivity(intent);
                return true;
            }
        });

        textView.setText(image_resources[position][0]);
        imageView.setImageResource(image_resources[position][1]);
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("destroyItem");
        container.removeView((LinearLayout) object);
    }

}
