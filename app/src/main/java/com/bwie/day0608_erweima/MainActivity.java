package com.bwie.day0608_erweima;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends Activity {
    private TextView textView;

    private ImageView qrImgImageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, CaptureActivity.class);
//        startActivity(intent);

        textView = (TextView) findViewById(R.id.result_button_view);

        qrImgImageView = (ImageView)findViewById(R.id.gen_id);
    }

    //扫描
    public void scannner(View view){
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    public void gener_pic(View view){
        String contentString = "我是一名学生。";
        if (!contentString.equals("")) {
            //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
            Bitmap qrCodeBitmap = EncodingUtils.createQRCode2(contentString, 350, 350);
            qrImgImageView.setImageBitmap(qrCodeBitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            textView.setText(scanResult);
        }
    }


}
