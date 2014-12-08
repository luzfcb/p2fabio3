package info.fabiodev.p2fabio3.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import info.fabiodev.p2fabio3.app.fabio.RestClient;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

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
        RestClient restclient = new RestClient(this.getActivity(), this.imageLoader, this.imageView_principal, this.progressBar);
        restclient.execute();

    }

}
