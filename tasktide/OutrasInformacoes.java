package com.example.tasktide;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tasktide.DAO.DAO;
import com.example.tasktide.Objetos.Informacoes;

public class OutrasInformacoes extends AppCompatActivity {

    private EditText edtxtDataPrevis, edtxtTimeInicio, edtxtTimeTermino, edtxtLocal;
    private long idEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outras_informacoes);

        edtxtDataPrevis = findViewById(R.id.edtxtDataPrevis);
        edtxtTimeInicio = findViewById(R.id.etxtxTimeInicio);
        edtxtTimeTermino = findViewById(R.id.edtxtTimeTermino);
        edtxtLocal = findViewById(R.id.edtxtLocal);

        // Recuperar o idEvento do Intent
        idEvento = getIntent().getLongExtra("ID_EVENTO", -1);

        findViewById(R.id.imgbtnProximoT).setOnClickListener(v -> inserirInformacoes());
        findViewById(R.id.imgbtnVoltarT).setOnClickListener(v -> {
            finish(); // Voltar para a atividade anterior
        });

        findViewById(R.id.imgbtnProximoT).setOnClickListener(this::telaIrConfirmarIdentidade);
    }

    private void inserirInformacoes() {
        String dataPrevis = edtxtDataPrevis.getText().toString().trim();
        String horarioInicio = edtxtTimeInicio.getText().toString().trim();
        String horarioTermino = edtxtTimeTermino.getText().toString().trim();
        String local = edtxtLocal.getText().toString().trim();

        DAO dao = new DAO(this);
        Informacoes informacoes = new Informacoes(dataPrevis, horarioInicio, horarioTermino, local);
        dao.inserirInformacoes(informacoes, idEvento);
    }

    public void telaIrConfirmarIdentidade(View view){
        Intent in = new Intent(OutrasInformacoes.this, ConfirmarIdentidade.class);
        startActivity(in);
    }
}
