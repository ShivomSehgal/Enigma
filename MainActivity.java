package com.example.android.enigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView text1;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        text1=findViewById(R.id.result);
        editText=findViewById(R.id.password);
//         listView=findViewById(R.id.)

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str= editText.getText().toString();
                String result=caesarCypherEncryptor(str,5);
                text1.setText("@#"+runLengthEncoding(result)+"5$%&");
            }
        });
    }

    public static String caesarCypherEncryptor(String string, int key) {
        char[] letters=new char[string.length()];
        int shift=key%26;
        String alphabets="abcdefghijklmnopqrstuvwxyz";

        for(int i=0; i<string.length(); i++){
            letters[i] = shifter(string.charAt(i),alphabets,shift);
        }
        return new String(letters);
    }

    public static char shifter(char letter, String alphabets, int shift){
        int Idx=alphabets.indexOf(letter)+shift;
        return alphabets.charAt(Idx % 26);
    }
    public static String runLengthEncoding(String string) {
        StringBuilder sb=new StringBuilder();
        int counter=1;

        for(int i=1;i<string.length();i++){
            char currentcharacter=string.charAt(i);
            char previouscharacter=string.charAt(i-1);

            if(currentcharacter!=previouscharacter || counter==9){
                sb.append(Integer.toString(counter));
                sb.append(previouscharacter);
                counter=0;
            }
            counter++;
        }
        sb.append(Integer.toString(counter));
        sb.append(string.charAt(string.length()-1));
        return sb.toString();
    }
    public void openActivity(View view){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
}

}
