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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    private TextView hasil_name, hasil_email, hasil_homepage, hasil_about;
    private String s_name, s_email, s_about, s_homepage, s_url, imageUri;
    private Button visit;
    private ImageView profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        hasil_name = findViewById(R.id.label_fullname);
        hasil_email = findViewById(R.id.label_email);
        hasil_about = findViewById(R.id.label_about);
        hasil_homepage = findViewById(R.id.label_homepage);
        visit = findViewById(R.id.button_homepage);
        profile = findViewById(R.id.image_profile);

        s_name = getIntent().getExtras().getString("KEY_NAME");
        s_email = getIntent().getExtras().getString("KEY_EMAIL");
        s_about = getIntent().getExtras().getString("KEY_ABOUT");
        s_homepage = getIntent().getExtras().getString("KEY_HOMEPAGE");

        Uri imageUri = Uri.parse(getIntent().getExtras().getString("KEY_URI"));
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            profile.setImageBitmap(bitmap);
        } catch (IOException e) {
            Toast.makeText(this, "Failed load images", Toast.LENGTH_SHORT).show();
        }

        hasil_name.setText(s_name);
        hasil_email.setText(s_email);
        hasil_about.setText(s_about);
        hasil_homepage.setText(s_homepage);

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_url = "https://www.instagram.com/taufik_satrian/";

                Intent visit = new Intent(Intent.ACTION_VIEW);
                visit.setData(Uri.parse(s_url));
                startActivity(visit);
            }
        });
    }
}
