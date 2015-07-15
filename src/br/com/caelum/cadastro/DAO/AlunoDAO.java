package br.com.caelum.cadastro.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper{
	private static final String DATABASE = "NomeDoBanco";
	private static final String TABELA = "Alunos";
	private static final int VERSAO = 1;

	public AlunoDAO(Context ctx) {
		super(ctx, DATABASE, null, VERSAO);
	}


	@Override
	public void onCreate(SQLiteDatabase database) {
		String sql = "CREATE TABLE " + TABELA + " (" 
				+ "id INTEGER PRIMARY KEY, " 
				+ "nome TEXT UNIQUE NOT NULL, "
				+ "telefone TEXT,"
				+ "endereco TEXT,"
				+ "site TEXT,"
				+ "nota REAL,"
				+ "caminhoFoto TEXT "
				+ ");";
		database.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		
	}
	
	public void insere(Aluno aluno){
		ContentValues cv = new ContentValues();
		cv.put("id", aluno.getId());
		cv.put("nome", aluno.getNome());
		cv.put("telefone", aluno.getTelefone());
		cv.put("endereco", aluno.getEndereco());
		cv.put("site", aluno.getSite());
		cv.put("nota", aluno.getNota());
		cv.put("caminhoFoto", aluno.getCaminhoImg());
		
		getWritableDatabase().insert(TABELA, null, cv);
	}

	public List<Aluno> getLista() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		String sql = "SELECT * FROM " + TABELA + ";";
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		
		while(c.moveToNext()){
			Aluno aluno = new Aluno();
			aluno.setId(c.getLong(c.getColumnIndex("id")));
			aluno.setNome(c.getString(c.getColumnIndex("nome")));
			aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
			aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
			aluno.setSite(c.getString(c.getColumnIndex("site")));
			aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
			aluno.setCaminhoImg(c.getString(c.getColumnIndex("caminhoFoto")));
			
			alunos.add(aluno);
		
		}
		return alunos;
	
	}
};
