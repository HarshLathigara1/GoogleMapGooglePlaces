package lathigara.harsh.googlemapgoogleplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity";
    private static final int ERROE_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isServiceOk()){
            init();
        }
    }

    private void init(){
        Button button = findViewById(R.id.btnMap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    public boolean isServiceOk(){
        Log.d(TAG, "isServiceOk: google Play Service version ");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServiceOk: googlePlay Services Is Working");
            return true;


        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServiceOk: we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this,available,ERROE_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this,"we will try again",Toast.LENGTH_LONG).show();
        }
        return false;
    }
}
