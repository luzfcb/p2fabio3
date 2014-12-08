package info.fabiodev.p2fabio3.app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import info.fabiodev.p2fabio3.app.fabio.Pizza;
import info.fabiodev.p2fabio3.app.fabio.RestClient;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
@EFragment(R.layout.fragment_main)
public class PlaceholderFragment extends Fragment {

    private ImageLoader imageLoader;

    @ViewById
    protected ImageView imageView_principal;

    @ViewById
    protected ProgressBar progressBar;

    @ViewById
    protected TextView preco_pizza;

    @ViewById
    protected TextView ingredientes_pizza;

    @ViewById
    protected TextView tamanho_pizza;

    @ViewById
    protected TextView nome_pizza;

    protected List<Pizza> pizzaList;


    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public PlaceholderFragment() {
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState) {
//        // https://github.com/excilys/androidannotations/wiki/Enhance-Fragments#fragment-layout
//
//        return null;
//    }

    @AfterViews
    void configuracao_inicial() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                //.cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this.getActivity())
                .defaultDisplayImageOptions(defaultOptions)
                .writeDebugLogs()
                .build();
        //ImageLoader.getInstance().init(config);
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        //ImageLoaderConfiguration config = new ImagafterViewseLoaderConfiguration.Builder(getApplicationContext())



        this.progressBar.setVisibility(View.INVISIBLE);
    }


    @Click(R.id.obter_dados)
    protected void obter_dados(){

        pizzaList = new ArrayList<Pizza>();

        RestClient restclient = new RestClient(this, this.getActivity(), this.imageLoader, this.imageView_principal, this.progressBar, this.pizzaList);
        restclient.execute();

    }

    public void adiciona_na_tela(){

        try {
            Random rd = new Random();
            int idx = rd.nextInt(pizzaList.size() -1);
            Pizza pizza = pizzaList.get(idx);
            this.imageLoader.displayImage(pizza.getFoto(), this.imageView_principal);
            this.nome_pizza.setText(pizza.getNome());
            this.preco_pizza.setText("R$ " + pizza.getValor());
            this.tamanho_pizza.setText(pizza.getTamanho());
            this.ingredientes_pizza.setText(pizza.getIngredientes());
        }catch (Exception e){
            e.printStackTrace();
            Log.v("erro Placeholder", "erro Placeholder pizza");
        }


    }

}
