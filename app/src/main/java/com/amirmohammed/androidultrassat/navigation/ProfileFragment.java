package com.amirmohammed.androidultrassat.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.amirmohammed.androidultrassat.databinding.ActivityFirebaseProfileBinding;
import com.amirmohammed.androidultrassat.databinding.ActivityFirebaseRegisterBinding;
import com.amirmohammed.androidultrassat.firebase.User;
import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {

    ActivityFirebaseProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityFirebaseProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        ProfileFragmentArgs args = ProfileFragmentArgs.fromBundle(getArguments());
        User user = args.getUser();

        binding.firebaseEtName.setText(user.getName());
        binding.firebaseEtPhone.setText(user.getPhone());
        binding.firebaseEtEmail.setText(user.getEmail());

        Glide.with(view).load(user.getImageUrl()).into(binding.ivProfile);
    }


}
