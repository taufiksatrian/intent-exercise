package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
	private Button btn_register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void handleRegister(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);

		btn_register = findViewById(R.id.btn_register);

		btn_register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent move = new Intent(MainActivity.this, RegisterActivity.class);

				startActivity(move);
			}
		});
	}
}
