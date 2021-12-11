package com.amirmohammed.androidultrassat.navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amirmohammed.androidultrassat.R;
import com.amirmohammed.androidultrassat.databinding.ActivityDatabaseBinding;
import com.amirmohammed.androidultrassat.firebase.User;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    ActivityDatabaseBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityDatabaseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        HomeFragmentArgs homeFragmentArgs = HomeFragmentArgs.fromBundle(getArguments());
        Log.i(TAG, "onViewCreated: " + homeFragmentArgs.getEmail());


        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("Amir", "011", "amir@gmail.com", "https://firebasestorage.googleapis.com/v0/b/chat-8bb29.appspot.com/o/julyProfileImages%2Ftest%2FRSRxAw9SRUVF6c5eadTBcVF7GKM2?alt=media&token=b035164b-95b9-41da-8d0f-f422bebbb03b");

                HomeFragmentDirections.ActionHomeFragmentToProfileFragment action =
                        HomeFragmentDirections.actionHomeFragmentToProfileFragment(user);

                navController.navigate(action);
//                navController.navigate(R.id.action_homeFragment_to_profileFragment);
            }
        });

    }


}
