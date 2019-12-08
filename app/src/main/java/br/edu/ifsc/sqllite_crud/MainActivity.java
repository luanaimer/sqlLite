package br.edu.ifsc.sqllite_crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase bd;

    EditText etCodigo;
    EditText etNomeUC;
    EditText etNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("teste", "1");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("teste", "2");

        bd = openOrCreateDatabase("alunos" , MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS  alunos"+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nome_UC TEXT NOT NULL, " +
                "nota DECIMAL NOT NULL)" );

        etCodigo =findViewById(R.id.editTextcodigo);
        etNomeUC = findViewById(R.id.editTextUC);
        etNota =    findViewById(R.id.editTextNota);

        Log.i("teste", "3");
    }


    public void bntIncluirOnClick (View view ){
        ContentValues registro = new ContentValues();
        registro.put("nome_UC", etNomeUC.getText().toString());
        registro.put("nota",  Double.parseDouble(etNota.getText().toString()) );
        int i = (int) bd.insert("alunos", null, registro);
        Toast.makeText(this, "Sucesso "+ Integer.toString(i), Toast.LENGTH_LONG).show();
    }


    public void bntAlterarOnClick(View view) {
        ContentValues registro = new ContentValues();
        registro.put("nome_UC",etNomeUC.getText().toString() );
        registro.put("nota",  Double.parseDouble(etNota.getText().toString()) );
        int id=Integer.parseInt(etCodigo.getText().toString());
        int i= bd.update("alunos", registro, "id=?" , new String[] {String.valueOf(id)} );
        Toast.makeText(this, " Registros alterados "+ i , Toast.LENGTH_LONG).show();
    }


    public void bntPesquisarOnClick(View view) {
        final EditText etCodPesquisa = new EditText(this);
        etCodPesquisa.setTextColor(Color.BLACK);

        AlertDialog.Builder telaPesquisa = new AlertDialog.Builder(this);
        telaPesquisa.setTitle("Pesquisa");
        telaPesquisa.setMessage("Informe o código");
        telaPesquisa.setView(etCodPesquisa);
        telaPesquisa.setNegativeButton("Cancelar", null);
        DialogInterface.OnClickListener o= new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realizaPesquisa(Integer.parseInt(etCodPesquisa.getText().toString()));
            }
        };
        telaPesquisa.setPositiveButton("Pesquisar" , o);
        telaPesquisa.show();
    }

    private void realizaPesquisa(int i) {
        Cursor registros = bd.query("alunos", null , "id="+i, null, null, null, null );
        if (registros.moveToNext()) {
            String nomeUC = registros.getString(registros.getColumnIndex("nome_UC"));
            double nota = registros.getDouble(registros.getColumnIndex("nota"));

            etNota.setText(Double.toString(nota));
            etNomeUC.setText(nomeUC);
            etCodigo.setText(Integer.toString(i));

        }else{
            Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_LONG).show();
        }

    }


    public void bntExcluirOnClick(View view) {
        int id = Integer.parseInt(etCodigo.getText().toString());
        int i=bd.delete("alunos", "id=?",new String[] {String.valueOf(id)});
        Toast.makeText(this, "Registros excluidos :"+ i , Toast.LENGTH_LONG).show();
    }

    public void bntListarOnClick(View view) {
        Intent i = new Intent(getApplicationContext(), ListaAlunos.class);
        startActivity(i);
    }
}