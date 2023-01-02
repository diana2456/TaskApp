@file:Suppress("DEPRECATION")

package space.lobanovi.taskapp.ui.onBoard

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import space.lobanovi.taskapp.R
import space.lobanovi.taskapp.ui.onBoard.OnBoardPageFragment.Companion.ON_BOARD

class BoardAdapter(fm: FragmentManager,
                private var listenerSkip: () -> Unit,
                private var listenerNext: () -> Unit,
): FragmentStatePagerAdapter(fm) {

    private val  listBoarding = arrayOf(
        BoardModel(
            R.drawable.img1,
            "To-do list!",
            "Here you can write down something important or make a schedule for tomorrow:)",
            false
        ),
        BoardModel(
            R.drawable.img2,
            "Share your crazy idea ^_^",
            "You can easily share with your report, list or schedule and it's convenient",
            false
        ),
        BoardModel(
            R.drawable.img3,
            "Flexibility",
            "Your note with you at home, at work, even at the resort",
            true
        )
    )

    override fun getCount(): Int = listBoarding.size

    override fun getItem(position: Int): Fragment {
        val data = bundleOf(ON_BOARD to listBoarding[position])
        val fragment = OnBoardPageFragment(listenerSkip,listenerNext)
        fragment.arguments = data
        return fragment

    }
}

