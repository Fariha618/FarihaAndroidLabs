package algonquin.cst2335.reza0036.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import algonquin.cst2335.reza0036.data.MainViewModel;
import algonquin.cst2335.reza0036.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.mybutton.setOnClickListener( click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        model.editString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has: " + s);
        });

        model.isSelected.observe(this, selected ->
        {
            variableBinding.checkboxButton.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);
            variableBinding.switchButton.setChecked(selected);
        });

        variableBinding.checkboxButton.setOnCheckedChangeListener((check, isChecked) -> {
            model.isSelected.postValue(isChecked);
            Context context = getApplicationContext();
            CharSequence text = "The value is now: " + isChecked;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        variableBinding.radioButton.setOnCheckedChangeListener((radio, isChecked) -> {
            model.isSelected.postValue(isChecked);
            Context context = getApplicationContext();
            CharSequence text = "The value is now: " + isChecked;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        });

        variableBinding.switchButton.setOnCheckedChangeListener((switchbtn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            Context context = getApplicationContext();
            CharSequence text = "The value is now: " + isChecked;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        variableBinding.myimagebutton.setOnClickListener(imgbtn -> {
            float width = variableBinding.myimagebutton.getWidth();
            float height = variableBinding.myimagebutton.getHeight();
            Context context = getApplicationContext();
            CharSequence text = "The width = " + width + " and height = " + height;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        });


    }
}