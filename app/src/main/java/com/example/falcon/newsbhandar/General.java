package com.example.falcon.newsbhandar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link General.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link General#newInstance} factory method to
 * create an instance of this fragment.
 */
public class General extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public General() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment General.
     */
    // TODO: Rename and change types and number of parameters
    public static General newInstance(String param1, String param2) {
        General fragment = new General();
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
        final String apiKey = "fd91ae1d98994c8382d88af8c0174773";
        final ProgressBar pbar;

        String selectedCountry = MainActivity.pref.getString("country", null);
        View view = inflater.inflate(R.layout.fragment_general, container, false);
        pbar=view.findViewById(R.id.pbar);
        pbar.setVisibility(View.VISIBLE);
        countryNameAndCodes cnac = new countryNameAndCodes();
        cnac.enter();
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Call<News> news1 = MainActivity.rt.others(cnac.data.get(selectedCountry), apiKey,"general");
        news1.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                }
                News news = response.body();
                List<Article> articles = news.getArticles();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                pbar.setVisibility(View.GONE);
                recyclerView.setAdapter(new newsAdapter(articles));

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Toast.makeText(getActivity(), "error" + t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
