package com.Click4Good;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideShow extends Activity{
    private TextView txtStatus;
    private ImageView imageView;
    int i=0;
    int imgid[]={R.drawable.one,R.drawable.two,R.drawable.three};
    RefreshHandler refreshHandler=new RefreshHandler();
    
    class RefreshHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            SlideShow.this.updateUI();
        }
        public void sleep(long delayMillis){
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };
    public void updateUI(){
        int currentInt=Integer.parseInt((String)txtStatus.getText())+10;
        if(currentInt<=100){
            refreshHandler.sleep(2000);
            txtStatus.setText(String.valueOf(currentInt));
            if(i<imgid.length){
                imageView.setImageResource(imgid[i]);
                
                // imageView.setPadding(left, top, right, bottom);
                i++;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slideshow);
        this.txtStatus=(TextView)this.findViewById(R.id.textView1);
        this.imageView=(ImageView)this.findViewById(R.id.imageView);
        updateUI();
    }

}