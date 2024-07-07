package com.example.tasktide;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tasktide.DAO.DAO;
import com.example.tasktide.Objetos.Identidade;

public class ConfirmarIdentidade extends AppCompatActivity {

    private static final String TAG = "ConfirmarIdentidade";
    private EditText edtxtNome, edtxtCargo, edtxtDepartamento, edtxtContato;
    private long idEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_identidade);

        // Inicializar os EditTexts
        edtxtNome = findViewById(R.id.edtxtNome);
        edtxtCargo = findViewById(R.id.edtxtCargo);
        edtxtDepartamento = findViewById(R.id.edtxtDepartamento);
        edtxtContato = findViewById(R.id.edtxtContato);

        // Recuperar o idEvento do Intent
        idEvento = getIntent().getLongExtra("ID_EVENTO", -1);

        // Configurar OnClickListener para o botão "Proximo"
        findViewById(R.id.imgbtnProximoQ).setOnClickListener(v -> {
            // Obter os dados dos campos de entrada
            String nome = edtxtNome.getText().toString().trim();
            String cargo = edtxtCargo.getText().toString().trim();
            String departamento = edtxtDepartamento.getText().toString().trim();
            String contato = edtxtContato.getText().toString().trim();

            // Criar um objeto Identidade com os dados inseridos
            Identidade identidade = new Identidade(nome, cargo, departamento, contato);

            // Inserir na tabela identidade usando o DAO
            long id = new DAO(ConfirmarIdentidade.this).inserirIdentidade(identidade, idEvento);

            // Verificar se a inserção foi bem-sucedida
            if (id != -1) {
                Log.i(TAG, "Dados de identidade inseridos com sucesso.");
                // Navegar para a próxima tela (QuaseLa)
                startActivity(new Intent(ConfirmarIdentidade.this, QuaseLa.class));
            } else {
                Log.e(TAG, "Erro ao inserir dados de identidade.");
                // Implementar feedback ao usuário se necessário
            }
        });

        // Configurar OnClickListener para o botão "Voltar"
        findViewById(R.id.imgbtnVoltarQ).setOnClickListener(v -> {
            // Navegar de volta para a tela OutrasInformacoes
            startActivity(new Intent(ConfirmarIdentidade.this, OutrasInformacoes.class));
        });
    }
}
