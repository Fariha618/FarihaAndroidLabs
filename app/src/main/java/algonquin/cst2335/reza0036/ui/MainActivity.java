package algonquin.cst2335.reza0036.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


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

        variableBinding.mybutton.setOnClickListener(click ->
                {
                    model.editString.postValue(variableBinding.myedittext.getText().toString());
                    model.editString.observe(this, s -> {
                        variableBinding.myedittext.setText("Your edit text has: "+ s);
                    });
                }
                );

    }
}