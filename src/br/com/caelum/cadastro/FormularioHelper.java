package br.com.caelum.cadastro;

import android.widget.EditText;
import android.widget.RatingBar;
import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastrocaelum.R;

public class FormularioHelper  {
	
	EditText nome, telefone, endereco, site;
	RatingBar nota;
	
	 public FormularioHelper(FormularioActivity activity) { 
		 nome = (EditText) activity.findViewById(R.id.nome);
		 telefone = (EditText) activity.findViewById(R.id.telefone);
		 endereco = (EditText) activity.findViewById(R.id.endereco);
		 site = (EditText) activity.findViewById(R.id.site);
		 nota = (RatingBar) activity.findViewById(R.id.nota);
	 }
	 
	 public Aluno pegaAlunoDoFormulario(){
		 	
			Aluno aluno = new Aluno();
			
			aluno.setNome(nome.getText().toString());
			aluno.setEndereco(endereco.getText().toString());
			aluno.setTelefone(telefone.getText().toString());
			aluno.setSite(site.getText().toString());
			aluno.setNota(Double.valueOf(nota.getProgress()));
			
			return aluno;
	 }

	public void colocaAlunoNoFormulario(Aluno alunoParaSerAlterado) {
		nome.setText(alunoParaSerAlterado.getNome());
		site.setText(alunoParaSerAlterado.getSite());
		endereco.setText(alunoParaSerAlterado.getEndereco());
		telefone.setText(alunoParaSerAlterado.getTelefone());
		nota.setRating((float) alunoParaSerAlterado.getNota());
	}
};
