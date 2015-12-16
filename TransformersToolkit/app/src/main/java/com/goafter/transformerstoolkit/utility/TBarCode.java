package com.goafter.transformerstoolkit.utility;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.goafter.transformerstoolkit.R;
import com.google.zxing.WriterException;


public class TBarCode extends Fragment {

    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;

    public TBarCode() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tbar_code, container, false);


        resultTextView = (TextView) view.findViewById(R.id.tv_scan_result);
        qrStrEditText = (EditText) view.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) view.findViewById(R.id.iv_qr_image);

        Button scanBarCodeButton = (Button) view.findViewById(R.id.btn_scan_barcode);


        Button generateQRCodeButton = (Button) view.findViewById(R.id.btn_add_qrcode);

        return view;
    }




}
