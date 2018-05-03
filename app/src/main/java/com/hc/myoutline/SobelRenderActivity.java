package com.hc.myoutline;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.RenderScript;
import android.widget.ImageView;

import com.hc.renderscript.ScriptC_sobel;

/**
 * Created by desaco on 2018/5/3.
 */

public class SobelRenderActivity extends Activity {

    private ImageView mSrcImageView;
    private ImageView mDstImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobel_render);

        mSrcImageView = (ImageView) findViewById(R.id.src);
        mDstImageView = (ImageView) findViewById(R.id.dst);

        Bitmap mInBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        Bitmap mOutBitmap = Bitmap.createBitmap(mInBitmap.getWidth(),mInBitmap.getHeight(), mInBitmap.getConfig());
        mSrcImageView.setImageBitmap(mInBitmap);

        RenderScript rs = RenderScript.create(this);
        ScriptC_sobel mScript = new ScriptC_sobel(rs);

        Allocation aIn = Allocation.createFromBitmap(rs, mInBitmap);
        Allocation aOut = Allocation.createFromBitmap(rs, mInBitmap);


        mScript.forEach_invert(aIn, aOut);
        aOut.copyTo(mOutBitmap);
        mDstImageView.setImageBitmap(mOutBitmap);
        rs.destroy();
    }
}
