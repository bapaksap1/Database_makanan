package com.example.master_room;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private Button btn_input, btn_lihat;
    private int harga;
    private String kategori, nama_makanan;
    private EditText et_harga, et_kategori, et_nama_makanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_input = findViewById(R.id.input);
        btn_lihat = findViewById(R.id.lihat);
        et_harga = findViewById(R.id.et_harga);
        et_nama_makanan = findViewById(R.id.et_nama);
        et_kategori = findViewById(R.id.et_kategori);
        appDatabase = AppDatabase.iniDb(getApplicationContext());

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.input:
                        input();
                        break;
                }
            }
        });
        btn_lihat.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            }
        });


    }

    void input() {
        nama_makanan = et_nama_makanan.getText().toString();
        harga = Integer.parseInt(et_harga.getText().toString());
        kategori = et_kategori.getText().toString();
        final Makanan makanan = new Makanan();
        makanan.setNama(nama_makanan);
        makanan.setHarga(harga);
        makanan.setKategori(kategori);
        new InsertData(appDatabase, makanan).execute();
    }

    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private Makanan makanan;



        public InsertData(AppDatabase database, Makanan makanan) {
            this.database = database;
            this.makanan = makanan;
        }
        @Override
        protected Long doInBackground(Void... voids) {
        return database.dao().insertData(makanan);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Toast.makeText(MainActivity.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
        }

        public void readData(AppDatabase database) { List list;
            list = database.dao().getData(); // Untuk read database getData(list); // Untuk menampikan
        }


    }
}
