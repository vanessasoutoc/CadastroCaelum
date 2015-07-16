package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.cadastro.DAO.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastrocaelum.R;

public class ListaAlunosActivity extends Activity {
	private Aluno aluno;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
		ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);	
		
		registerForContextMenu(listaAlunos);
		
		listaAlunos.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				Aluno alunoSelecionadoParaAlteracao = (Aluno) adapter.getItemAtPosition(posicao);
				
				Intent irParaOFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
				irParaOFormulario.putExtra("alunoSelecionado", alunoSelecionadoParaAlteracao);
				startActivity(irParaOFormulario);
			}
		});
		
		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				aluno = (Aluno) adapter.getItemAtPosition(posicao);
				return false;
			}
		});
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}


	private void carregaLista() {
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
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		menu.add("Ligar");
		menu.add("Enviar SMS");
		menu.add("Achar no mapa");
		menu.add("Navegar no site");
		menu.add("Enviar e-mail");
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
				dao.deletar(aluno);
				dao.close();
				carregaLista();
				return false;
			}
		});
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
}
