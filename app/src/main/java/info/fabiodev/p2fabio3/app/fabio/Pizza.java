package info.fabiodev.p2fabio3.app.fabio;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luzfcb on 25/11/14.
 */
public class Pizza{
    public String getNome() {
        return nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getValor() {
        return valor;
    }

    public String getFoto() {
        return foto;
    }

    String nome;
    String tamanho;
    String ingredientes;
    String valor;
    String foto;

    public Pizza(String nome, String tamanho, String ingredientes, String valor, String foto) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.ingredientes = ingredientes;
        this.valor = valor;
        this.foto = foto;
    }

    public Pizza(JSONObject jsonObject) throws JSONException {
        this(jsonObject.getString("nome"), jsonObject.getString("tamanho"), jsonObject.getString("ingredientes"), jsonObject.getString("valor"), jsonObject.getString("foto"));

    }
    //
//        public Pizza(String nome, String tamanho, String ingredientes, String valor) {
//            this(nome, tamanho, ingredientes, valor, null);
//        }

    @Override
    public String toString() {
        return "Pizza{" +
                "nome='" + nome + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", valor='" + valor + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
