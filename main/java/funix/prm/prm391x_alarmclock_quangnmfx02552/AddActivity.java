package funix.prm.prm391x_alarmclock_quangnmfx02552;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private EditText editText;
    private Button buttonSave, buttonCancel;

    private Alarm alarm;
    private boolean needRefresh;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        timePicker = findViewById(R.id.timePicker);
        editText = findViewById(R.id.name);
        buttonSave = findViewById(R.id.button_save);
        buttonCancel = findViewById(R.id.button_cancel);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String name = editText.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                // tạo alarm với giờ, phút và tên lấy ở TimePicker và EditText và thêm vào database
                alarm = new Alarm(hour, minute, true, name);
                db.addAlarm(alarm);

                needRefresh = true;
                // quay về MainActivity
                onBackPressed();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // quay về MainActivity
                onBackPressed();
            }
        });
    }

    @Override
    public void finish() {
        // chuyển kết quả needRefresh về MainActivity để refresh alarmList nếu cần
        Intent data = new Intent();
        data.putExtra("needRefresh", needRefresh);
        this.setResult(RESULT_OK, data);
        super.finish();
    }
}
