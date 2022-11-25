package algonquin.cst2335.reza0036.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import algonquin.cst2335.reza0036.data.ChatMessage;
import algonquin.cst2335.reza0036.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {

    ChatMessage selected;

    public MessageDetailsFragment(ChatMessage m){
        selected = m;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);

        binding.messageView.setText(selected.getMessage());
        binding.timeView.setText(selected.getTimeSent());
        binding.databaseIdView.setText("Id = "+ selected.id);

        return binding.getRoot();

    }
}