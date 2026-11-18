package edu.lemoyne.campusapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class ShowcaseRepository (private val api: ShowcaseApi = ShowcaseService.api){
    suspend fun loadItems(): List<ShowcaseItem> =
        withContext(Dispatchers.IO) {
            api.getShowcaseItems(ShowcaseService.API_KEY)

        }
}

