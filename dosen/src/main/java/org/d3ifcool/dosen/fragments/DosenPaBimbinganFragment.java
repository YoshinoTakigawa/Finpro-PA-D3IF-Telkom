package org.d3ifcool.dosen.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenJudulPaSubdosenViewAdapter;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenPaBimbinganViewAdapter;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.lists.JudulListView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.JudulPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_TERSEDIA;

public class DosenPaBimbinganFragment extends Fragment implements JudulListView {

    private static final String PARAMS_1 = "judul.judul_status";
    private static final String PARAMS_2 = "judul.dsn_nip";

    private RecyclerView recyclerView;
    private DosenPaBimbinganViewAdapter adapter;
    private JudulPresenter presenter;
    private ProgressDialog progressDialog;
    private ArrayList<Judul> arrayList = new ArrayList<>();
    private SessionManager sessionManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;

    public DosenPaBimbinganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_pa_bimbingan, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_pa_bimbingan_recyclerview);
        adapter = new DosenPaBimbinganViewAdapter(getContext());
        sessionManager = new SessionManager(getContext());
        swipeRefreshLayout = rootView.findViewById(R.id.frg_dsn_pa_bimbingan_swiperefresh);
        progressDialog = new ProgressDialog(getContext());
        empty_view = rootView.findViewById(R.id.view_emptyview);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        presenter = new JudulPresenter(this);
        presenter.getJudulSearchTwoParameter(PARAMS_1, JUDUL_STATUS_DIGUNAKAN, PARAMS_2, sessionManager.getSessionDosenNip());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getJudulSearchTwoParameter(PARAMS_1, JUDUL_STATUS_DIGUNAKAN, PARAMS_2, sessionManager.getSessionDosenNip());
            }
        });

        return rootView;

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetListJudul(List<Judul> judulpa) {
        arrayList.clear();
        arrayList.addAll(judulpa);
        adapter.addItemPa(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_pa_bimbingan);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailed(String message) {

    }
}
