package info.fabiodev.p2fabio3.app.fabio;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;



import info.fabiodev.p2fabio3.app.PlaceholderFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/hgoebl/DavidWebb
 * Lightweight Java HTTP-Client for calling JSON REST-Services (especially for Android)
 */

import com.goebl.david.Webb;
import com.goebl.david.WebbException;

/**
 * nostra13 Android-Universal-Image-Loader
 * https://github.com/nostra13/Android-Universal-Image-Loader#usage
 */

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * criado por https://github.com/luzfcb on 25/11/14.
 */
public class RestClient extends AsyncTask<String, Integer, String> {
    private final ProgressBar progressBar;
//    private final ImageView imageView;
//    private final ImageLoader imageLoader;
    private final List<Pizza> pizzaList;
    private final PlaceholderFragment placeholderFragment;
    private boolean sem_conexao_rede;


    private JSONArray pizzasJsonArray;
    //private ArrayList<JSONObject> pizzas_jsonObjectArrayList;
    private Activity activity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //this.progressBar.Show
        this.progressBar.setVisibility(View.VISIBLE);
        this.sem_conexao_rede = false;

    }

    public RestClient(PlaceholderFragment placeholderFragment, Activity activity, ImageLoader imageLoader, ImageView imageView, ProgressBar progressBar, List<Pizza> pizzaList) {
        this.placeholderFragment = placeholderFragment;
        this.progressBar = progressBar;
//        this.imageLoader = imageLoader;
//        this.imageView = imageView;
        this.activity = activity;
        this.pizzaList = pizzaList;
    }

    @Override
    protected String doInBackground(String... params) {


        try {
            // obtem dados do webservice
            Webb webb = Webb.create();
            pizzasJsonArray = webb.get("http://fratelli.herokuapp.com/pizzas/?format=json")
                    .ensureSuccess()
                    .asJsonArray()
                    .getBody();
            try {

                //pizzas_jsonObjectArrayList = new ArrayList<JSONObject>();
                for (int i = 1; i < pizzasJsonArray.length(); i++) {

                    //pizzas_jsonObjectArrayList.add(pizzasJsonArray.getJSONObject(i));
                    //pizza = new Pizza(jsonObject.getString("nome"), jsonObject.getString("tamanho"), jsonObject.getString("ingredientes"), jsonObject.getString("valor"), jsonObject.getString("foto"));
                    pizzaList.add(new Pizza(pizzasJsonArray.getJSONObject(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
            this.sem_conexao_rede = true;

        }



        return null;
    }


    @Override
    protected void onPostExecute(String result) {


        if (this.sem_conexao_rede) {
            this.progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this.activity, "Nao ha conexao de rede\nVerifique sua conexão com a internet", Toast.LENGTH_LONG).show();
            Log.v("RestClient", "Nao ha conexao de rede");

        }
        // TODO Auto-generated method stub
        try {
            Log.v("json-pizzasJsonArray original", this.pizzasJsonArray.toString());
            placeholderFragment.adiciona_na_tela();

            this.progressBar.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}