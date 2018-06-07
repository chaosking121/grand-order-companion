package world.arshad.grandordercompanion.view_servant;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import world.arshad.grandordercompanion.R;

public class AscensionsFragment extends Fragment {

    @BindView(R.id.ascension_list)
    RecyclerView ascensionList;

    private AscensionAdapter adapter;
    private ServantViewModel viewModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(ServantViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ascensions, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        ascensionList.setHasFixedSize(true);
        ascensionList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AscensionAdapter(getActivity(), viewModel.getServant().getAscensions());
        ascensionList.setAdapter(adapter);
    }
}
