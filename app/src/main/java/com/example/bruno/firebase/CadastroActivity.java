package com.example.bruno.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {
    private EditText email,senha;
    private Button btnLog,btnFpass,btnSign;
    private ProgressBar pb;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        auth = FirebaseAuth.getInstance();
        btnLog = (Button) findViewById(R.id.btnLog);
        btnSign = (Button) findViewById(R.id.btnSign);
        btnFpass = (Button) findViewById(R.id.btnFpass);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.password);
        pb = (ProgressBar) findViewById(R.id.progressBar);

        btnFpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intecao = new Intent (CadastroActivity.this,AtualizarSenhaActivity.class);
             startActivity(intecao);

            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail= email.getText().toString().trim();
                String txtSenha= senha.getText().toString().trim();
            if (TextUtils.isEmpty(txtEmail)){
                Toast.makeText(getApplicationContext(),"E-mail space is empty",Toast.LENGTH_SHORT).show();

            }
                if (TextUtils.isEmpty(txtSenha)){
                    Toast.makeText(getApplicationContext(),"Password space is empty",Toast.LENGTH_SHORT).show();

                }
                if (txtSenha.length()<6){
                    Toast.makeText(getApplicationContext(),"Password too short,password must contain at least 6 characteres ",
                            Toast.LENGTH_SHORT).show();

                }
                pb.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(txtEmail,txtSenha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(CadastroActivity.this,"createUserWithEmail:onComplete:"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.GONE);
                    }
                });


            }
        });


    }
}
