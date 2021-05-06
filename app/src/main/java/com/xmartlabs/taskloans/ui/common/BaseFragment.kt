package com.xmartlabs.taskloans.ui.common

import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

/**
 * Created by mirland on 25/04/20.
 */
abstract class BaseFragment : Fragment() {
  protected val router: NavRouter by lazy { FragmentNavRouter(this) }

  protected fun setupScreenToolbar(toolbar: Toolbar, title: String? = null, hasUpButton: Boolean = true) {
    (activity as? AppCompatActivity)?.apply {
      setSupportActionBar(toolbar)
      if (title != null) {
        supportActionBar?.title = title
      }
      if (hasUpButton) {
        setHasOptionsMenu(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(hasUpButton)
      }
    }
  }

  override fun onOptionsItemSelected(item: MenuItem) =
      if (android.R.id.home == item.itemId) onToolbarNavigationClicked() else false

  protected open fun onToolbarNavigationClicked(): Boolean = router.onUpButtonTapped()

  protected fun displayError(error: String) = Toast.makeText(
      requireContext(),
      error,
      Toast.LENGTH_SHORT
  ).show()
}
