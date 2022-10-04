package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_name, et_email, et_password, et_confirm_password, et_homepage, et_about;
    private Button btn_ok;
    private String s_name, s_email, s_password, s_confirm_password, s_homepage, s_about, s_uriString;
    private ImageView imgProfile, img_ok;
    private String uriString = null;

    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = findViewById(R.id.text_fullname);
        et_email = findViewById(R.id.text_email);
        et_password = findViewById(R.id.text_password);
        et_confirm_password = findViewById(R.id.text_confirm_password);
        et_homepage = findViewById(R.id.text_homepage);
        et_about = findViewById(R.id.text_about);
        btn_ok = findViewById(R.id.button_ok);
        img_ok = findViewById(R.id.imageView);
        imgProfile = findViewById(R.id.image_profile);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_name = et_name.getText().toString();
                s_email = et_email.getText().toString();
                s_password = et_password.getText().toString();
                s_confirm_password = et_confirm_password.getText().toString();
                s_homepage = et_homepage.getText().toString();
                s_about = et_about.getText().toString();

                boolean check = validateinfo(s_password, s_confirm_password);

                if (check==true){
                    Toast.makeText(getApplicationContext(), "Data is Valid", Toast.LENGTH_SHORT).show();

                    Intent register = new Intent(RegisterActivity.this, ProfileActivity.class);

                    register.putExtra("KEY_NAME", s_name);
                    register.putExtra("KEY_EMAIL", s_email);
                    register.putExtra("KEY_PASSWORD", s_password);
                    register.putExtra("KEY_CO_PASSWORD", s_confirm_password);
                    register.putExtra("KEY_HOMEPAGE", s_homepage);
                    register.putExtra("KEY_ABOUT", s_about);
                    register.putExtra("KEY_URI", uriString);

                    startActivity(register);
                } else  {
                    Toast.makeText(getApplicationContext(), "Sorry Check Password Again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST_CODE);
            }
        });

    }

    private Boolean validateinfo(String s_password, String s_confirm_password){
        if (!s_password.equals(s_confirm_password)){
            et_password.requestFocus();
            et_confirm_password.setError("Password Unmatches");
            return false;
        }else  {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Canceled pick images", Toast.LENGTH_SHORT).show();
        } else if (requestCode == GALLERY_REQUEST_CODE){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    uriString = imageUri.toString();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imgProfile.setImageBitmap(bitmap);
                } catch (IOException e){
                    Toast.makeText(this, "Failed load images", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
