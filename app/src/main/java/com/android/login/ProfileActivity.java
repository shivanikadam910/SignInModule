package com.android.login;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import model.UserModel;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvFName, tvLName, tvAddress, tvCountry, tvZipcode, tvEMail, tvPhone;
    private Button btnCamera, btnGallary;
    private ImageView ivProfilePicture;
    private static final int PIC_ID_CAMERA = 100;
    private static final int PIC_ID_GALLARY = 101;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 200;
    public static final int MY_PERMISSIONS_REQUEST_GALLARY = 201;
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher, galleryActivityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        setData();
        setListeners();
        registerActivityCallBack();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted",Toast.LENGTH_SHORT).show();
                handlePermissionCamera();
            }
            else{
                Toast.makeText(this, "Permission Denied",Toast.LENGTH_SHORT).show();
            }

        }
        else if(requestCode == MY_PERMISSIONS_REQUEST_GALLARY){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted",Toast.LENGTH_SHORT).show();
                handlePermissonImageChooser();
            }
            else{
                Toast.makeText(this, "Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initViews() {
        tvFName = findViewById(R.id.tvFName_profile);
        tvLName = findViewById(R.id.tvLname_profile);
        tvAddress = findViewById(R.id.tvAddress_profile);
        tvCountry = findViewById(R.id.tvCountry_profile);
        tvZipcode = findViewById(R.id.tvZipcode_profile);
        tvEMail = findViewById(R.id.tvEmail_profile);
        tvPhone = findViewById(R.id.tvPhone_profile);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);

    }


    private void setData() {
        UserModel user = new UserModel("", "");
        user = (UserModel) getIntent().getSerializableExtra("userModel");
        tvFName.setText(user.getfName());
        tvLName.setText(user.getlName());
        tvAddress.setText(user.getAddress());
        tvCountry.setText(user.getCountry());
        tvZipcode.setText(Integer.toString(user.getZipcode()));
        tvEMail.setText(user.getEmail());
        tvPhone.setText(Integer.toString(user.getPhone()));

//        Intent intent = getIntent();
//        String fName = intent.getStringExtra("firstName");
//        tvFName.setText(fName);
//        String lName = intent.getStringExtra("lastName");
//        tvLName.setText(lName);
//        String address_string = intent.getStringExtra("address_string");
//        tvAddress.setText(address_string);
//        String country_string = intent.getStringExtra("country_string");
//        tvCountry.setText(country_string);
//        String zipcode_string = intent.getStringExtra("zipcode_string");
//        tvZipcode.setText(zipcode_string);
//        String email_string = intent.getStringExtra("email_string");
//        tvEMail.setText(email_string);
//        String phone_string = intent.getStringExtra("phone_string");
//        tvPhone.setText(phone_string);
    }

    private void setListeners() {
        ivProfilePicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivProfilePicture:
                ViewGroup viewGroup = findViewById(android.R.id.content);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_uploadmedia, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btnCamera = alertDialog.findViewById(R.id.btnCamera);
                btnGallary = alertDialog.findViewById(R.id.btnGallary);
                btnCamera.setOnClickListener(v -> {
                    handlePermissionCamera();
                    alertDialog.dismiss();
                });
                btnGallary.setOnClickListener(v -> {
                    handlePermissonImageChooser();
                    alertDialog.dismiss();
                });
                break;
        }
    }
    private void registerActivityCallBack() {
            cameraActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
                            ivProfilePicture.setImageBitmap(photo);
                        }
                    });

            galleryActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Uri selectedImageUri = result.getData().getData();
                            if (null != selectedImageUri) {
                                ivProfilePicture.setImageURI(selectedImageUri);
                            }

                        }
                    });
    }

    private void handlePermissionCamera(){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED){
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraActivityResultLauncher.launch(camera_intent);
            }
            else{
                 ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
    }
    private void handlePermissonImageChooser() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_GRANTED){
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            galleryActivityResultLauncher.launch(Intent.createChooser(i, "Select Picture"));
        }
        else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_GALLARY);
        }
    }


}