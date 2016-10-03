package tw.com.pcschool.t100301;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);
        File f1 = getFilesDir();
        File f2 = getCacheDir();

        Log.d("FILE", f1.toString());
        Log.d("FILE", f2.toString());


    }

    public void clickWrite(View v)
    {
        String fName = "test.txt";
        FileOutputStream fOut = null;
        try {
            fOut = openFileOutput(fName, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            EditText ed = (EditText) findViewById(R.id.editText);
            String str = ed.getText().toString();
            osw.write(str);
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void clickRead(View v)
    {
        char[] buffer = new char[1];
        FileReader fr = null;
        StringBuilder sb = new StringBuilder();
        File file = new File(getFilesDir().getAbsolutePath(),
                "test.txt");
        try {
            fr = new FileReader(file);
            while (fr.read(buffer)!= -1) {
                sb.append(new String(buffer));
            }
            tv.setText(file.getAbsolutePath() + "\n\n" +
                    sb.toString());
        }
        catch (IOException e) { }
        finally {
            try {
                fr.close(); // 關閉檔案
            }
            catch (IOException e) { }
        }
    }
}
