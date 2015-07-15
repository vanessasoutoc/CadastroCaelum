package br.com.caelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import br.com.caelum.cadastro.DAO.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastrocaelum.R;

public class FormularioActivity extends Activity {

	private FormularioHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Button botao = (Button) findViewById(R.id.botao);
		
		helper = new FormularioHelper(this);
		
		botao.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				Toast.makeText(FormularioActivity.this, "Clicou no button", Toast.LENGTH_LONG).show();
				Aluno aluno = helper.pegaAlunoDoFormulario();
				
				AlunoDAO dao = new AlunoDAO(FormularioActivity.this);
				dao.insere(aluno);
				dao.close();
				finish();
				
			}
		});
		
	}
}
