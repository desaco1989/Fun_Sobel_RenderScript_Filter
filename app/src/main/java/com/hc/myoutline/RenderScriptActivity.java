package com.hc.myoutline;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.hc.myoutline.renderscript_utils.BlurUtil;
import com.hc.myoutline.renderscript_utils.MagnifierUtil;
import com.hc.myoutline.renderscript_utils.SketchUtil;


public class RenderScriptActivity extends AppCompatActivity {

    private ImageView mSourceIv;
    private ImageView mMagnifierImageView;
    private ImageView mSketchIv;
    private ImageView mBlurIv;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renderscript);
        mContext = this;

        mSourceIv = (ImageView) findViewById(R.id.source_iv);
        mMagnifierImageView = (ImageView) findViewById(R.id.magnifier_iv);
        mSketchIv = (ImageView) findViewById(R.id.sketch_iv);
        mBlurIv = (ImageView) findViewById(R.id.blur_iv);

//        mSourceIv.setBackgroundResource(R.drawable.image);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);

        magnifier();//放大镜
        sketch();//画轮廓
        blur();//模糊

    }
    private void magnifier(){//Bitmap bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        bitmap = MagnifierUtil.magnifierBitmap(bitmap, 1000, 400, 300, 3, this);
        mMagnifierImageView.setImageBitmap(bitmap);
    }
    private void sketch(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        mSketchIv.setImageBitmap(SketchUtil.sketchBitmap(bitmap, mContext));
    }
    private void blur(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        mBlurIv.setImageBitmap(BlurUtil.blurBitmap(bitmap, 25, mContext));
    }

}
