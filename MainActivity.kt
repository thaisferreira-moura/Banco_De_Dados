package com.example.projetocrud

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var bancoHelper: BancoHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bancoHelper = BancoHelper(this)

        val editNome = findViewById<TextInputEditText>(R.id.textNome)
        val editIdade = findViewById<TextInputEditText>(R.id.textIdade)
        val textResultado = findViewById<TextView>(R.id.textResultado)

        val btnGravar = findViewById<Button>(R.id.btnGravar)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        val btnAtualizar = findViewById<Button>(R.id.btnAtualizar)
        val btnDeletar = findViewById<Button>(R.id.btnDeletar)

        // 1. CADASTRAR (Gravar)
        btnGravar.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val idadeText = editIdade.text.toString().trim()

            if (nome.isNotEmpty() && idadeText.isNotEmpty()) {
                val idade = idadeText.toInt()
                val sucesso = bancoHelper.inserirUsuario(nome, idade)

                if (sucesso) {
                    textResultado.text = "Usuário '$nome' gravado com sucesso!"
                    limparCampos(editNome, editIdade)
                } else {
                    textResultado.text = "Erro: Nome já cadastrado ou falha no banco."
                }
            } else {
                Toast.makeText(this, "Preencha o Nome e a Idade!", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. CONSULTAR
        btnConsultar.setOnClickListener {
            val nome = editNome.text.toString().trim()

            if (nome.isNotEmpty()) {
                val idade = bancoHelper.buscarUsuario(nome)
                if (idade != null) {
                    editIdade.setText(idade.toString())
                    textResultado.text = "Encontrado: $nome, $idade anos."
                } else {
                    textResultado.text = "Usuário '$nome' não encontrado."
                }
            } else {
                Toast.makeText(this, "Digite o Nome para consultar!", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. ATUALIZAR
        btnAtualizar.setOnClickListener {
            val nome = editNome.text.toString().trim()
            val idadeText = editIdade.text.toString().trim()

            if (nome.isNotEmpty() && idadeText.isNotEmpty()) {
                val novaIdade = idadeText.toInt()
                val sucesso = bancoHelper.atualizarUsuario(nome, novaIdade)

                if (sucesso) {
                    textResultado.text = "Usuário '$nome' atualizado para $novaIdade anos!"
                    limparCampos(editNome, editIdade)
                } else {
                    textResultado.text = "Usuário '$nome' não foi encontrado para atualizar."
                }
            } else {
                Toast.makeText(this, "Preencha o Nome e a nova Idade!", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. DELETAR
        btnDeletar.setOnClickListener {
            val nome = editNome.text.toString().trim()

            if (nome.isNotEmpty()) {
                val sucesso = bancoHelper.deletarUsuario(nome)

                if (sucesso) {
                    textResultado.text = "Usuário '$nome' deletado com sucesso!"
                    limparCampos(editNome, editIdade)
                } else {
                    textResultado.text = "Usuário '$nome' não foi encontrado para remoção."
                }
            } else {
                Toast.makeText(this, "Digite o Nome para deletar!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limparCampos(editNome: TextInputEditText, editIdade: TextInputEditText) {
        editNome.text?.clear()
        editIdade.text?.clear()
    }
}
