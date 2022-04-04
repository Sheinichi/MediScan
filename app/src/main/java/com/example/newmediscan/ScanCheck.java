package com.example.newmediscan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.newmediscan.ml.Model24;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ScanCheck extends AppCompatActivity {

    private ImageView imageView;
    private Button scan;
    private Button check;
    private TextView price;
    private TextView result;

    private final String medName1 = "Advil";
    private final String medName2 = "Alaxan";
    private final String medName3 = "Amlodipine";
    private final String medName4 = "Bioflu";
    private final String medName5 = "Citirizine";
    private final String medName6 = "Diatabs";
    private final String medName7 = "Diclofenac";
    private final String medName8 = "Neozep";
    private final String medName9 = "Pharex";
    private final String medName10 = "Vitamin B";

    private final String medDes1 = "Advil(Ibuprofen) - Blister pack";
    private final String medDes2 = "Alaxan (Ibuprofen + Paracetamol) - Blister pack";
    private final String medDes3 = "Amlodipine - Blister pack";
    private final String medDes4 = "Bioflu - Blister pack";
    private final String medDes5 = "Citirizine - Blister pack";
    private final String medDes6 = "Diatabs - Blister pack";
    private final String medDes7 = "Diclofenac(Volteran) - Blister pack";
    private final String medDes8 = "Neozep(Phenylephrine Hcl + Chlorphenamine Maleate + Paracetamol) - Blister pack";
    private final String medDes9 = "Pharex Vitamin B Complex - Blister pack";
    private final String medDes10 = "Vitamin B Complex - Blister pack";

    double price0 = 0.00;
    double price1 = 50.00;
    double price2 = 55.00;
    double price3 = 60.00;
    double price4 = 65.00;
    double price5 = 70.00;
    double price6 = 75.00;
    double price7 = 40.00;
    double price8 = 80.00;
    double price9 = 85.00;
    double price10 = 53.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_check);

        imageView = findViewById(R.id.imageView);
        price = findViewById(R.id.price);
        scan = findViewById(R.id.scan);
        result = findViewById(R.id.result);
        check = findViewById(R.id.check);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 101);
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent cameraIntent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                }else{
                    requestPermissions(new String[]{
                            Manifest.permission.CAMERA},100);
                    }
            }
        });


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                imageView.setDrawingCacheEnabled(true);
//                imageView.buildDrawingCache();
//                Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
                imageView.buildDrawingCache();
                Bitmap bmap = imageView.getDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
                bitmap = Bitmap.createScaledBitmap(bitmap, 180, 180, true);

                try {
//
                    Intent intent2 = new Intent(ScanCheck.this, Checkout.class);
                    Model24 model = Model24.newInstance(getApplicationContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 180, 180, 3}, DataType.FLOAT32);

                    TensorImage tensorImage = new TensorImage(DataType.FLOAT32);
                    tensorImage.load(bitmap);
                    ByteBuffer byteBuffer = tensorImage.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);
                    // Runs model inference and gets result.
                    Model24.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    // Releases model resources if no longer used.
                    model.close();

                    if (outputFeature0.getFloatArray()[0] == 1) {
                        result.setText(medName1);
                        price.setText(Double.toString(price1));
                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes1);


                    }
                    if (outputFeature0.getFloatArray()[1] == 1) {
                        result.setText(medName2);
                        price.setText(Double.toString(price2));
                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes2);

                    }
                    if (outputFeature0.getFloatArray()[2] == 1) {
                        result.setText(medName3);
                        price.setText(Double.toString(price3));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes3);


                    }
                    if (outputFeature0.getFloatArray()[3] == 1) {
                        result.setText(medName4);
                        price.setText(Double.toString(price4));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes4);


                    }
                    if (outputFeature0.getFloatArray()[4] == 1) {
                        result.setText(medName5);
                        price.setText(Double.toString(price5));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes5);

                    }
                    if (outputFeature0.getFloatArray()[5] == 1) {
                        result.setText(medName6);
                        price.setText(Double.toString(price6));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes6);

                    }
                    if (outputFeature0.getFloatArray()[6] == 1) {
                        result.setText(medName7);
                        price.setText(Double.toString(price7));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes7);


                    }
                    if (outputFeature0.getFloatArray()[7] == 1) {
                        result.setText(medName8);
                        price.setText(Double.toString(price9));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes8);

                    }
                    if (outputFeature0.getFloatArray()[8] == 1) {
                        result.setText(medName9);
                        price.setText(Double.toString(price9));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes9);

                    }
                    if (outputFeature0.getFloatArray()[9] == 1) {
                        result.setText(medName10);
                        price.setText(Double.toString(price10));

                        String name_med = result.getText().toString();
                        String price_med = price.getText().toString();

                        intent2.putExtra("keyname", name_med);
                        intent2.putExtra("keyprice", price_med);
                        intent2.putExtra("keydesc", medDes10);

                    }


                    startActivity(intent2);


                } catch (IOException e) {
                    // TODO Handle the exception
                }
            }
        });

    }


        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1 && resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);

            }


        }





    }
