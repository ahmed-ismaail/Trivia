package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController()
                    .navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.winner_menu, menu)
    }

    private fun getShareIntent(): Intent {
        val args = arguments?.let { GameWonFragmentArgs.fromBundle(it) }
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_success_text, args!!.numCorrect,args!!.numQuestions))

        return intent
    }

    private fun share(){
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> share()
        }
        return super.onOptionsItemSelected(item)
    }
}
