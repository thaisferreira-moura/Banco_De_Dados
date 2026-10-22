package com.example.projetocrud

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(context: Context) : SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_BANCO) {

    companion object {
        private const val NOME_BANCO = "usuarios.db"
        private const val VERSAO_BANCO = 1
        private const val TABELA = "usuario"
        private const val COLUNA_ID = "id"
        private const val COLUNA_NOME = "nome"
        private const val COLUNA_IDADE = "idade"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABELA + " ("
                + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_NOME + " TEXT UNIQUE, "
                + COLUNA_IDADE + " INTEGER)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABELA")
        onCreate(db)
    }

    // CREATE - Inserir novo usuário
    fun inserirUsuario(nome: String, idade: Int): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUNA_NOME, nome)
        values.put(COLUNA_IDADE, idade)

        val resultado = db.insert(TABELA, null, values)
        db.close()
        return resultado != -1L
    }

    // READ - Buscar idade pelo nome do usuário
    fun buscarUsuario(nome: String): Int? {
        val db = this.readableDatabase
        val cursor = db.query(
            TABELA,
            arrayOf(COLUNA_IDADE),
            "$COLUNA_NOME = ?",
            arrayOf(nome),
            null, null, null
        )

        var idade: Int? = null
        if (cursor.moveToFirst()) {
            idade = cursor.getInt(cursor.getColumnIndexOrThrow(COLUNA_IDADE))
        }
        cursor.close()
        db.close()
        return idade
    }

    // UPDATE - Atualizar idade pelo nome
    fun atualizarUsuario(nome: String, novaIdade: Int): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUNA_IDADE, novaIdade)

        val linhasAfetadas = db.update(TABELA, values, "$COLUNA_NOME = ?", arrayOf(nome))
        db.close()
        return linhasAfetadas > 0
    }

    // DELETE - Remover usuário pelo nome
    fun deletarUsuario(nome: String): Boolean {
        val db = this.writableDatabase
        val linhasAfetadas = db.delete(TABELA, "$COLUNA_NOME = ?", arrayOf(nome))
        db.close()
        return linhasAfetadas > 0
    }
}
