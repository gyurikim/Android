package com.gura.step08webview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Webview의 참조값 얻어오기
        WebView webView=findViewById(R.id.webView);
        //WebSetting 객체
        WebSettings ws=webView.getSettings();
        ws.setJavaScriptEnabled(true);//js 사용가능 하도록

        //필요한 객체 생성해서 넣어주기
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new MyWebViewClient());
        //url 로딩 시키기
        webView.loadUrl("http://192.168.0.36:8888/spring05/");
    }

    public class MyWebViewClient extends WebChromeClient {
        // For Android Version < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            //System.out.println("WebViewActivity OS Version : " + Build.VERSION.SDK_INT + "\t openFC(VCU), n=1");
            mUploadMessage = uploadMsg;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType(TYPE_IMAGE);
            startActivityForResult(intent, INPUT_FILE_REQUEST_CODE);
        }
        //버전에 따라 파일 여는 방식이 변한다..... 다 알지 못하므로 인터넷을 돌아다니면서 확인해서 작성해야한다
        // For 3.0 <= Android Version < 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

            openFileChooser(uploadMsg, acceptType, "");
        }

        // For Android 4.1+

        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {

            openFileChooser(uploadMsg, acceptType);
        }

        // For Android 5.0+
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadFile, WebChromeClient.FileChooserParams fileChooserParams) {


            if(mFilePathCallback !=null) {
                mFilePathCallback.onReceiveValue(null);
                mFilePathCallback = null;
            }

            mFilePathCallback = uploadFile;
            /*
                Intent잘 사용만 하면 다른 어플에서 존재하는 이미지를 사용할수 잇다.....?
                운영체제한테 Inten 객체를 던져주고 알아서 사용하도록 한다
                선택된 목록이 하나면 알아서 사용하고, 여러 목록이 존재하면 선택하라고 물어본다
             */
            Intent i =new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            startActivityForResult(Intent.createChooser(i, "File Chooser"), INPUT_FILE_REQUEST_CODE);

            return true;

        }


        private void imageChooser() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                    takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Log.e(getClass().getName(), "Unable to create Image File", ex);
                }

                // Continue only if the File was successfully created
                if (photoFile != null) {
                    mCameraPhotoPath = "file:"+photoFile.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                } else {
                    takePictureIntent = null;
                }
            }

            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType(TYPE_IMAGE);

            Intent[] intentArray;
            if(takePictureIntent != null) {
                intentArray = new Intent[]{takePictureIntent};
            } else {
                intentArray = new Intent[0];
            }

            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);//아래 메소드가 호출된다***
        }


    }
    //변수
    private static final String TYPE_IMAGE = "image/*";
    private static final int INPUT_FILE_REQUEST_CODE = 1;

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }


    @Override//작업한 결과값이 아래 메소드로 들어온다 > 항상 성공인 경우는 아니기떄문에 식별값을 확인해보아야한다
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INPUT_FILE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (mFilePathCallback == null) {
                        super.onActivityResult(requestCode, resultCode, data);
                        return;
                    }
                    Uri[] results = new Uri[]{data.getData()};//파일을 선택했을 때 정확히(유일하게) 지칭할수있는것이 Uri이다.

                    mFilePathCallback.onReceiveValue(results);
                    mFilePathCallback = null;
                } else {
                    if (mUploadMessage == null) {
                        super.onActivityResult(requestCode, resultCode, data);
                        return;
                    }
                    Uri result = data.getData();

                    Log.d(getClass().getName(), "openFileChooser : " + result);
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                }
            } else {
                if (mFilePathCallback != null) mFilePathCallback.onReceiveValue(null);
                if (mUploadMessage != null) mUploadMessage.onReceiveValue(null);
                mFilePathCallback = null;
                mUploadMessage = null;
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
