package br.com.caelum.cadastro;



import android.widget.EditText;
import android.widget.SeekBar;
import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastrocaelum.R;

public class FormularioHelper  {
	
	private EditText campoNome;
	private EditText campoTelefone;
	private EditText campoEndereco;
	private EditText campoSite;
	private SeekBar campoNota;
	private Aluno aluno;
	
	 public FormularioHelper(FormularioActivity activity) { 
		 aluno = new Aluno(); 
		 campoNome = (EditText) activity.findViewById(R.id.nome);
		 campoTelefone = (EditText) activity.findViewById(R.id.telefone);
		 campoEndereco = (EditText) activity.findViewById(R.id.endereco);
		 campoSite = (EditText) activity.findViewById(R.id.site);
		 campoNota = (SeekBar) activity.findViewById(R.id.nota);
	 }
	 
	 public Aluno pegaAlunoDoFormulario(){
		 	String nome = campoNome.getText().toString();
			String telefone = campoTelefone.getText().toString();
			String endereco = campoEndereco.getText().toString();
			String site = campoSite.getText().toString();
			int nota = campoNota.getProgress();
			
			
			aluno.setNome(nome);
			aluno.setEndereco(endereco);
			aluno.setTelefone(telefone);
			aluno.setSite(site);
			aluno.setNota(Double.valueOf(nota));
			
			return aluno;
	 }

}
