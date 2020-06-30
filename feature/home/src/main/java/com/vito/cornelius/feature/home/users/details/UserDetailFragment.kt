package com.vito.cornelius.feature.home.users.details

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.vito.cornelius.feature.home.R
import com.vito.cornelius.feature.home.databinding.FragmentUserDetailsBinding
import com.vito.cornelius.feature.home.users.load

private const val ONE_SECOND_MS = 1_000L

class UserDetailFragment : Fragment() {

    private val handler = Handler(Looper.getMainLooper())
    private val args: UserDetailFragmentArgs by navArgs()

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.move)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.userImageView) {
            transitionName = args.url
            load(
                    url = args.url,
                    placeHolderDrawable = R.drawable.ic_outline_person_outline_24,
                    errorDrawable = R.drawable.ic_outline_person_outline_24,
                    loadOnlyFromCache = false
            ) {
                startPostponedEnterTransition()
            }
        }
        // Watchdog just in case Glide takes too long to load the image
        handler.postDelayed({ startPostponedEnterTransition() },
                ONE_SECOND_MS
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


