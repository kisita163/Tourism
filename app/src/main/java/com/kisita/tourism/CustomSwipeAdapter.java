package com.kisita.tourism;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Hugues on 08/09/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {
    private int[][] image_resources = {{R.string.kinshasa,R.drawable.pic_kin},{R.string.goma,R.drawable.pic_kin1},{R.string.likasi,R.drawable.pic_kin2}};
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
