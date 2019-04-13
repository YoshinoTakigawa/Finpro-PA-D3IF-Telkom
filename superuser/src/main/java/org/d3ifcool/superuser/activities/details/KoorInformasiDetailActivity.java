package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.works.InformasiWorkView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.presenters.InformasiPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorInformasiUbahActivity;

public class KoorInformasiDetailActivity extends AppCompatActivity implements InformasiWorkView {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;
    private InformasiPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_informasi_detail);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_informasi_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new InformasiPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        TextView textView_judul = findViewById(R.id.act_koor_info_detail_textview_judul);
        TextView textView_isi = findViewById(R.id.act_koor_info_detail_textview_isi);
        TextView textView_tanggal = findViewById(R.id.act_koor_info_detail_textview_tanggal);
        TextView textView_nama = findViewById(R.id.act_koor_info_nama);
//        CircleImageView imageView_foto = findViewById();

        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extraInfo.getInfo_judul();
        String isi = extraInfo.getInfo_deskripsi();
        String tanggal = extraInfo.getTanggal();
        String nama = extraInfo.getPenerbit();

        textView_judul.setText(judul);
        textView_isi.setText(isi);
        textView_nama.setText(nama);
        textView_tanggal.setText(tanggal);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if (i == android.R.id.home) {
            finish();

        } else if (i == org.d3ifcool.dosen.R.id.toolbar_menu_ubah) {
            Intent intentUbah = new Intent(KoorInformasiDetailActivity.this, KoorInformasiUbahActivity.class);
            intentUbah.putExtra(KoorInformasiUbahActivity.EXTRA_INFORMASI, extraInfo);
            startActivity(intentUbah);
            finish();

        } else if (i == org.d3ifcool.dosen.R.id.toolbar_menu_hapus) {
            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            presenter.deleteInformasi(extraInfo.getId());
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

        return super.onOptionsItemSelected(item);
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
    public void onSucces() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
