package de.kokocinski.ankopre.main

import de.kokocinski.ankopre.data.Webservice
import de.kokocinski.ankopre.di.ActivityScope
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject constructor(
        private val webservice: Webservice
) {
    lateinit var viewModel: MainViewModel

    companion object {
        private const val ID = "0B36RBuJ0yFLFYWJ4TkE0R3RwZDQ"
    }

    fun loadData() {

        // anko
        async() {
            val data = webservice.getData(ID).execute().body()

            uiThread {
                viewModel.setData(data)
            }
        }

        // kovenant
//        task {
//            webservice.getData(ID)
//                    .execute()
//                    .body()
//        } successUi {
//            viewModel.setData(it)
//        } fail {
//            it.printStackTrace()
//        }

    }
}