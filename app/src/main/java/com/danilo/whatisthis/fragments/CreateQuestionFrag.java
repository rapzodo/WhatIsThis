package com.danilo.whatisthis.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.danilo.whatisthis.R;
import com.danilo.whatisthis.activities.LandingActivity;
import com.danilo.whatisthis.network.NetWorkHelperSingleton;
import com.google.android.gms.plus.PlusOneButton;

/**
 * A fragment with a Google +1 button.
 * Use the {@link CreateQuestionFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateQuestionFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button addPhoto;
    private String defaultPhotoUrl = "https://i.ytimg.com/vi/hacltWC_zjc/hqdefault.jpg";
    private NetworkImageView imageView;


    public CreateQuestionFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateQuestionFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateQuestionFrag newInstance(String param1, String param2) {
        CreateQuestionFrag fragment = new CreateQuestionFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_question, container, false);
        ImageLoader imageLoader = NetWorkHelperSingleton.newInstance(getContext()).getImageLoader();
        imageView = (NetworkImageView) view.findViewById(R.id.main_image);
        addPhoto = (Button) view.findViewById(R.id.new_photo_bt);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getContext().getPackageManager()) != null){
                    startActivityForResult(cameraIntent, LandingActivity.TAKE_PHOTO_REQ);
                }
            }
        });

        if(imageView == null){
            Toast.makeText(getContext(), "Nao achou a imageView", Toast.LENGTH_SHORT).show();
        }
        else{
            imageView.setImageUrl(defaultPhotoUrl, imageLoader);
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LandingActivity.TAKE_PHOTO_REQ){
            if(resultCode == getActivity().RESULT_OK){
                imageView.setImageBitmap((Bitmap) data.getExtras().get("data"));
                imageView.invalidate();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
