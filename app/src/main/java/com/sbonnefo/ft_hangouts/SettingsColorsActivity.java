package com.sbonnefo.ft_hangouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingsColorsActivity extends AppCompatActivity {

    private Integer _theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _theme = MainActivity.getCurrentTheme();
        setTheme(_theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_colors);

        setTitle(R.string.customApp);
        RadioGroup grpRadioColors = findViewById(R.id.grpRadioColors);

        switch (_theme){
            case R.style.AppThemeAlliance: grpRadioColors.check(R.id.radioBtnAlliance); break;
            case R.style.AppThemeAssembly: grpRadioColors.check(R.id.radioBtnAssembly); break;
            case R.style.AppThemeFederation: grpRadioColors.check(R.id.radioBtnFederation); break;
            case R.style.AppThemeOrder: grpRadioColors.check(R.id.radioBtnOrder); break;
            default: grpRadioColors.check(R.id.radioBtnAndroid); break;
        }

        grpRadioColors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioBtn = group.findViewById(group.getCheckedRadioButtonId());
                switch (checkedRadioBtn.getId()){
                    case R.id.radioBtnAlliance: MainActivity.setCurrentTheme(R.style.AppThemeAlliance); break;
                    case R.id.radioBtnAssembly: MainActivity.setCurrentTheme(R.style.AppThemeAssembly); break;
                    case R.id.radioBtnFederation: MainActivity.setCurrentTheme(R.style.AppThemeFederation); break;
                    case R.id.radioBtnOrder: MainActivity.setCurrentTheme(R.style.AppThemeOrder); break;
                    default: MainActivity.setCurrentTheme(R.style.AppTheme); break;
                }
                SettingsColorsActivity.this.finish();
                SettingsColorsActivity.this.startActivity(
                        new Intent(SettingsColorsActivity.this,
                                SettingsColorsActivity.class));


            }


        });
    }
}
