package world.arshad.grandordercompanion.all_servants;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;
import world.arshad.grandordercompanion.SidebarActivity;
import world.arshad.grandordercompanion.data.Servant;

public class AllServantsActivity extends SidebarActivity {

    @BindView(R.id.servant_list)
    RecyclerView servantInfoList;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.servant_info_sort_button)
    ImageButton sortButton;

    @BindView(R.id.servant_info_reverse_button)
    ImageButton reverseButton;

    @BindView(R.id.servants_progressbar)
    ProgressBar progressBar;

    private AllServantsViewModel viewModel;
    private ServantAdapter adapter;

    String[] sortOptions = {"ID", "Name", "Class", "Rarity", "Attack", "HP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_servants);
        super.setupSidebar(R.id.toolbar);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(AllServantsViewModel.class);

        adapter = new ServantAdapter(this);
        new LoadDataTask().execute();
        servantInfoList.setAdapter(adapter);
        servantInfoList.setHasFixedSize(true);
        servantInfoList.setLayoutManager(new LinearLayoutManager(this));

        sortButton.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setItems(sortOptions, (dialogInterface, j) -> {
                viewModel.sortItems(Servant.Comps.valueOf(sortOptions[j].toUpperCase()));
                new LoadDataTask().execute();
            });
            builder.create().show();
        });

        reverseButton.setOnClickListener(view -> {
            viewModel.reverseItems();
            new LoadDataTask().execute();
            reverseButton.setImageDrawable(viewModel.getReverseButtonDrawable());
        });

        reverseButton.setImageDrawable(viewModel.getReverseButtonDrawable());
    }

    private class LoadDataTask extends AsyncTask<Void, Void, List<Servant>> {
        protected void onPreExecute() {
            servantInfoList.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        protected List<Servant> doInBackground(Void ... params) {
            return viewModel.getServants();
        }


        protected void onPostExecute(List<Servant> servants) {
            adapter.setData(servants);
            servantInfoList.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
