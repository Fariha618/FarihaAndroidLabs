package algonquin.cst2335.reza0036;

import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    ImageView Imageview;

    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
//                        profileImage.setImageBitmap(thumbnail);

                        FileOutputStream fOut = null;

                        try {
                            fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                            fOut.flush();
                            fOut.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        TextView welcomeMessage = findViewById(R.id.welcomeView);
        welcomeMessage.setText("Welcome " + emailAddress);

        EditText EditText = findViewById(R.id.editTextTextPersonName);
        EditText EditTextPhone = findViewById(R.id.editTextPhone);
        String phoneNumber = prefs.getString("PhoneNumber", "");
        EditTextPhone.setText(phoneNumber);

        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:" + EditTextPhone));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(clk -> {
            String phone = fromPrevious.getStringExtra("PhoneNumber");

            Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
            EditText phoneNumberEdit = findViewById(R.id.editTextPhone);
            call.setData(Uri.parse("tel:" + phoneNumberEdit.getText()));

            startActivity(call);


        });

        if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SecondActivity.this, new String[] {
                    Manifest.permission.CAMERA}, 100
            );
        }
        Button changePicture = findViewById(R.id.button4);
        this.Imageview = findViewById(R.id.avatar);
        changePicture.setOnClickListener(click -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 100);

            File file = new File(getFilesDir(),"Picture.png" );
            if (file.exists()) {
                Bitmap theImage = BitmapFactory.decodeFile(getFilesDir() +"/Picture.png");
                Imageview.setImageBitmap(theImage);
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                Imageview.setImageBitmap(thumbnail);

                FileOutputStream fOut = null;

                try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                    thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                    fOut.flush();

                    fOut.close();

                }

                catch (FileNotFoundException e)

                { e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        EditText EditTextPhone = findViewById(R.id.editTextPhone);
        editor.putString("PhoneNumber", EditTextPhone.getText().toString());
        editor.apply();


    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "In Resume() - Responding to user input");
        File file = new File(getFilesDir(),"Picture.png" );
        if (file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(getFilesDir() +"/Picture.png");
            Imageview.setImageBitmap(theImage);
        }
    }
}