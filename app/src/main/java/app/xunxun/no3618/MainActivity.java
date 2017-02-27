package app.xunxun.no3618;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TelephonyManager telephonyManager;
    private WifiManager wifiMng;
    private EditText imeiEt;
    private EditText androidIdEt;
    private EditText macEt;
    private EditText imsiEt;
    private EditText phoneTypeEt;
    private EditText widthEt;
    private EditText heightEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        telephonyManager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        wifiMng = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        if (TextUtils.isEmpty(MyPreference.getOriginImei(this)))
            MyPreference.saveOriginImei(this);

        if (TextUtils.isEmpty(MyPreference.getOriginAndroidId(this)))
            MyPreference.saveOriginAndroidId(this);

        if (TextUtils.isEmpty(MyPreference.getOriginMac(this)))
            MyPreference.savetOriginMac(this);

        if (TextUtils.isEmpty(MyPreference.getOriginImsi(this)))
            MyPreference.savetOriginImsi(this);

        if (TextUtils.isEmpty(MyPreference.getOriginPhoneType(this)))
            MyPreference.saveOriginPhoneType(this);

        if (MyPreference.getOriginWidth(this) == 0)
            MyPreference.saveOriginWidth(this);
        if (MyPreference.getOriginHeight(this) == 0)
            MyPreference.saveOriginHeight(this);

        imeiEt = (EditText) findViewById(R.id.imeiEt);
        imeiEt.setText(MyPreference.getImei(this));

        androidIdEt = (EditText) findViewById(R.id.androidIdEt);
        androidIdEt.setText(MyPreference.getAndroidId(this));

        macEt = (EditText) findViewById(R.id.macEt);
        macEt.setText(MyPreference.getMac(this));

        imsiEt = (EditText) findViewById(R.id.imsiEt);
        imsiEt.setText(MyPreference.getImsi(this));

        phoneTypeEt = (EditText) findViewById(R.id.phoneTypeEt);
        phoneTypeEt.setText(MyPreference.getPhoneType(this));

        widthEt = (EditText) findViewById(R.id.widthEt);
        widthEt.setText(String.valueOf(MyPreference.getWidth(this)));

        heightEt = (EditText) findViewById(R.id.heightEt);
        heightEt.setText(String.valueOf(MyPreference.getHeight(this)));


        Button getBtn = (Button) findViewById(R.id.getIMEIBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGetCLicked(view);
            }
        });
        Button getAndroidIdBtn = (Button) findViewById(R.id.getAndroidIdBtn);
        getAndroidIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                show(android_id);

            }
        });
        Button getMacBtn = (Button) findViewById(R.id.getMacBtn);
        getMacBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiInfo wifiInfor = wifiMng.getConnectionInfo();
                String mac = wifiInfor.getMacAddress();
                show(mac);
            }
        });
        Button getImsiBtn = (Button) findViewById(R.id.getImsiBtn);
        getImsiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imsi = telephonyManager.getSubscriberId();
                show(imsi);

            }
        });
        Button getPhoneTypeBtn = (Button) findViewById(R.id.getPhoneTypeBtn);
        getPhoneTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = android.os.Build.MODEL;
                show(type);
            }
        });

        Button getWidthHeightBtn = (Button) findViewById(R.id.getWidthHeightBtn);
        getWidthHeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int w_screen = dm.widthPixels;
                int h_screen = dm.heightPixels;
                show(w_screen + "*" + h_screen);
            }
        });

        int flag = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (flag != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                show("这个权限对我们很重要");
            }
        }
    }

    public void onGetCLicked(View view) {

        String imei = telephonyManager.getDeviceId();
        show(imei);
    }

    private void show(String msg) {
        new AlertDialog.Builder(this).setMessage(msg).setPositiveButton("ok", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            getInputAndSave();
            Toast.makeText(this, "保存成功,机型需要软重启", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.saveWithRandom) {
            imeiEt.setText(RandomGenerator.getIMEI());
            androidIdEt.setText(RandomGenerator.getAndroidId());
            macEt.setText(RandomGenerator.getMac());
            imsiEt.setText(RandomGenerator.getImsi());
            widthEt.setText(String.valueOf(RandomGenerator.genearateWidthHeight().getWidth()));
            heightEt.setText(String.valueOf(RandomGenerator.genearateWidthHeight().getHeight()));
            getInputAndSave();
            Toast.makeText(this, "保存成功,机型不随机生成", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void getInputAndSave() {
        MyPreference.saveImei(this, imeiEt.getText().toString());
        MyPreference.saveAndroidId(this, androidIdEt.getText().toString());
        MyPreference.saveMac(this, macEt.getText().toString());
        MyPreference.saveImsi(this, imsiEt.getText().toString());
        MyPreference.savePhoneType(this, phoneTypeEt.getText().toString());
        MyPreference.saveWidth(this,Integer.parseInt(widthEt.getText().toString()));
        MyPreference.saveHeight(this,Integer.parseInt(heightEt.getText().toString()));

    }
}
