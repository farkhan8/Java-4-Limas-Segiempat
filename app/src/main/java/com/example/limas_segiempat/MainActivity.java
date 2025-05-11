package com.example.limas_segiempat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity - Aplikasi Kalkulator Limas Segiempat
 * 
 * Aplikasi ini menghitung volume dan luas permukaan limas segiempat
 * berdasarkan input panjang sisi alas dan tinggi dari pengguna.
 */
public class MainActivity extends AppCompatActivity {
    // Input fields
    private EditText sideInput;
    private EditText heightInput;
    
    // TextView untuk menampilkan hasil perhitungan
    private TextView volumeResult;
    private TextView areaResult;
    
    // Container untuk hasil perhitungan (awalnya tersembunyi)
    private LinearLayout resultsContainer;

    /**
     * Inisialisasi aplikasi
     * Mengatur layout dan menghubungkan event listeners
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sideInput = findViewById(R.id.sideInput);
        heightInput = findViewById(R.id.heightInput);
        volumeResult = findViewById(R.id.volumeResult);
        areaResult = findViewById(R.id.areaResult);
        resultsContainer = findViewById(R.id.resultsContainer);
        Button calculateButton = findViewById(R.id.calculateButton);
        Button resetButton = findViewById(R.id.resetButton);

        /**
         * Event listener untuk tombol "Hitung"
         * Menghitung volume dan luas permukaan limas segiempat
         */
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sideStr = sideInput.getText().toString();
                String heightStr = heightInput.getText().toString();
                
                if (!sideStr.isEmpty() && !heightStr.isEmpty()) {
                    try {
                        // Konversi input string ke double
                        double side = Double.parseDouble(sideStr);
                        double height = Double.parseDouble(heightStr);
                        
                        // Hitung volume: (1/3) × s² × t
                        double volume = (1.0/3.0) * Math.pow(side, 2) * height;
                        
                        // Hitung tinggi sisi tegak (t')
                        double sideHeight = Math.sqrt(Math.pow(height, 2) + Math.pow(side/2, 2));
                        
                        // Hitung luas permukaan: s² + 4 × (½ × s × t')
                        double surfaceArea = Math.pow(side, 2) + 4 * (0.5 * side * sideHeight);
                        
                        // Format hasil perhitungan volume dengan langkah-langkah
                        String volumeSteps = String.format(
                            "Volume Limas Segiempat:\n" +
                            "V = (1/3) × s² × t\n" +
                            "= (1/3) × %.2f² × %.2f\n" +
                            "= (1/3) × %.2f × %.2f\n" +
                            "= %.2f satuan kubik",
                            side, height,
                            Math.pow(side, 2), height,
                            volume
                        );

                        // Format hasil perhitungan luas permukaan dengan langkah-langkah
                        String areaSteps = String.format(
                            "\nLuas Permukaan Limas Segiempat:\n" +
                            "1. Hitung tinggi sisi tegak (t'):\n" +
                            "t' = √(t² + (s/2)²)\n" +
                            "= √(%.2f² + (%.2f/2)²)\n" +
                            "= √(%.2f + %.2f)\n" +
                            "= %.2f\n\n" +
                            "2. Hitung luas permukaan:\n" +
                            "LP = s² + 4 × (½ × s × t')\n" +
                            "= %.2f² + 4 × (½ × %.2f × %.2f)\n" +
                            "= %.2f + %.2f\n" +
                            "= %.2f satuan persegi",
                            height, side,
                            Math.pow(height, 2), Math.pow(side/2, 2),
                            sideHeight,
                            side, side, sideHeight,
                            Math.pow(side, 2), 2 * side * sideHeight,
                            surfaceArea
                        );

                        volumeResult.setText(volumeSteps);
                        areaResult.setText(areaSteps);
                        resultsContainer.setVisibility(View.VISIBLE);
                    } catch (NumberFormatException e) {
                        volumeResult.setText("Masukkan angka yang valid");
                        areaResult.setText("");
                        resultsContainer.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumeResult.setText("");
                areaResult.setText("");
                sideInput.setText("");
                heightInput.setText("");
                resultsContainer.setVisibility(View.GONE);
            }
        });
    }
}
