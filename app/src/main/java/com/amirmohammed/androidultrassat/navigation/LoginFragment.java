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

import com.amirmohammed.androidultrassat.R;
import com.amirmohammed.androidultrassat.databinding.ActivityFirebaseLoginBinding;

public class LoginFragment extends Fragment {

    ActivityFirebaseLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityFirebaseLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.firebaseEtEmail.getText().toString();

                LoginFragmentDirections.ActionLoginFragmentToHomeFragment action=
                        LoginFragmentDirections.actionLoginFragmentToHomeFragment(email);

                navController.navigate(action);
//                navController.navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);


            }
        });


    }


}
