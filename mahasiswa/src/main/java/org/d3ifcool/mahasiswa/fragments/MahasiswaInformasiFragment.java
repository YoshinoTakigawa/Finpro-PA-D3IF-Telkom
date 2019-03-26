package org.d3ifcool.mahasiswa.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.adapters.MahasiswaInformasiViewAdapter;
import org.d3ifcool.service.interfaces.lists.InformasiListView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.presenters.InformasiPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaInformasiFragment extends Fragment implements InformasiListView {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private MahasiswaInformasiViewAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private InformasiPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public MahasiswaInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_informasi, container, false);

        recyclerView = rootView.findViewById(R.id.frg_mhs_info_recyclerview);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_mhs_info_swiperefresh);

        adapter = new MahasiswaInformasiViewAdapter(getContext());
        presenter = new InformasiPresenter(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        presenter.getInformasi();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getInformasi();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getInformasi();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onGetListInformasi(List<Informasi> informasi) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        arrayList.clear();
        arrayList.addAll(informasi);
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_informasi);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
