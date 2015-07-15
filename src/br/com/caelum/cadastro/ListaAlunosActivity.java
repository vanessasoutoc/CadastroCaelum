package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.DAO.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastrocaelum.R;

public class ListaAlunosActivity extends Activity {
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
		ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);	
		
		listaAlunos.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				Toast.makeText(ListaAlunosActivity.this, "A posição é "+ posicao, Toast.LENGTH_SHORT).show();
			}
		});
		
		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
Toast.makeText(ListaAlunosActivity.this, "Aluno clicado é "+ adapter.getItemAtPosition(posicao), Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);	
		
		AlunoDAO dao = new AlunoDAO(this);
		
		List<Aluno> alunos = dao.getLista(); 
		
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
		
		listaAlunos.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.novo:
			Intent irParaFormulario = new Intent(this, FormularioActivity.class);
			startActivity(irParaFormulario);
			
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
