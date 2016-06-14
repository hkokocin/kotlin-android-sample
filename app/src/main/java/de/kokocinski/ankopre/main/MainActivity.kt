package de.kokocinski.ankopre.main;

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import de.kokocinski.ankopre.R
import de.kokocinski.ankopre.SampleApplication
import de.kokocinski.ankopre.databinding.ActivityMainBinding
import de.kokocinski.ankopre.di.ActivityModule
import de.kokocinski.ankopre.di.ActivityScope
import de.kokocinski.ankopre.di.DaggerActivityComponent
import de.kokocinski.ankopre.di.DaggerBindingComponent
import de.kokocinski.ankopre.view.action
import de.kokocinski.ankopre.view.snackBar
import javax.inject.Inject

@ActivityScope
class MainActivity @Inject constructor() : AppCompatActivity() {

    @Inject lateinit var mainViewModel: MainViewModel
    @Inject lateinit var mainPresenter: MainPresenter

    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        doInjections()
        setupDatabinding()

        // Configure Kovenant with standard dispatchers
        // startKovenant()
    }

    override fun onResume() {
        super.onResume()

        rootView?.snackBar("Welcome") {
            action("Close") {
                finish()
            }
        }
    }

    override fun onDestroy() {
        // Dispose of the Kovenant thread pools
        // for quicker shutdown you could use
        // force=true, which ignores all current
        // scheduled tasks
        // stopKovenant()
        super.onDestroy()
    }

    private fun setupDatabinding() {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        rootView = binding.root

        mainViewModel.setImageView(binding.imageView)
        mainPresenter.viewModel = mainViewModel

        mainPresenter.loadData()
    }

    private fun doInjections() {

        val application = applicationContext as SampleApplication

        DaggerActivityComponent.builder()
                .activityModule(ActivityModule())
                .applicationComponent(application.applicationComponent)
                .build()
                .injectTo(this)

        // to fix some issues with databinding
        DataBindingUtil.setDefaultComponent(DaggerBindingComponent.builder().build())
    }
}
