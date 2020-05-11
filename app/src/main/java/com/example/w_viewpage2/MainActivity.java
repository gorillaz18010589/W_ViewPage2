package com.example.w_viewpage2;
//1.加入API:
//ViewPage2
// implementation 'androidx.viewpager2:viewpager2:1.0.0'
//RoundedImageView
// implementation 'com.makeramen:roundedimageview:2.3.0'

//2.xml加入ViewPage2
//3.寫一個item用com.makeramen.roundedimageview.RoundedImageView
//4.圖片放入res
//5.寫一個RecyclerView
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private int i =0;
    boolean isRright = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //7.初始化viewPager2
        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        //8.設定圖片
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.a));
        sliderItems.add(new SliderItem(R.drawable.b));
        sliderItems.add(new SliderItem(R.drawable.c));
        sliderItems.add(new SliderItem(R.drawable.d));



//viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);//設定方向水平或垂直(方向物件)
//viewPager2.setUserInputEnabled(false);//設定是否讓使用者滑動(true/false)

//        setPageTransformer(ViewPager2.PageTransformer transformer)設置頁面滑動時的變換效果(效果設置實作)
//        setAdapter(Adapter adapter)設定調變器(改好的調變器)
//        setOrientation(int orientation)設定方向(方向)
//        setCurrentItem(int item)設定當前itm(item index)
//        beginFakeDrag()開始模擬拖移(回傳boolean)
//        fakeDragBy(float offsetPxFloat)是否拖移中((已向素為單位拖移))(回傳boolean)
//        endFakeDrag()	拖移結束(回傳boolean)
//        setUserInputEnabled(boolean enabled)	設定用戶是否能拖移



        //9.設定Adapter
        viewPager2.setAdapter(new SliderRecyclerViewPager(sliderItems,viewPager2));

        //10.
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);


        //11.準備一個
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));

        //12.當滑動時會呼叫(1.page物件,2.滑動的位置)
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + r *0.15f );
                Log.v("hank","transformPage(View page, float position):" + "postition:" + position);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer); //設定
    }


    //按下按鈕開始滑動,如果滑動到-1000,停止滑動
    public void fakeDragBy(View view) {
        boolean beginFakeDrag =  viewPager2.beginFakeDrag();
        if (viewPager2.fakeDragBy(-1000)){
            viewPager2.endFakeDrag();
            Log.v("hank","是否開始移動了:"  +beginFakeDrag);
        }

        viewPager2.setCurrentItem(2);

    }

    //設定現在的item //照片是0,1,2,3 所以按下按鈕直接到第三張圖片
    public void setCurrentItem(View view) {
        if (i < 3 && !isRright) {
            i++;
            viewPager2.setCurrentItem(i);
            if (i == 3) {
                isRright = true;
            }
        } else if (i >= 3 || isRright) {
            if (isRright) {
                i--;
                viewPager2.setCurrentItem(i);
                if (i == 0) {
                    isRright = false;
                }
            }

        }

    }



}
