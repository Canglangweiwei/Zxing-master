package org.zero.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;

public class MainActivity extends AppCompatActivity {

    private final static int PAGE_REQUEST_CODE = 1001;

    private TextView mTview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTview = (TextView) findViewById(R.id.textView);

        final ImageView image = (ImageView) findViewById(R.id.imageView);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = ZxingUtil.getInstance().encodeAsBitmap(MainActivity.this, "http://www.89892528.com");
                    image.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 扫描条形码和二维码
                 */
                ZxingUtil.getInstance().decode(MainActivity.this, PAGE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            switch (requestCode) {
                case PAGE_REQUEST_CODE:
                    mTview.setText(bundle.getString("scan_result"));
                    break;
            }
        }
    }
}
