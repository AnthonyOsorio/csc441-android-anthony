package edu.lemoyne.campusapp

import edu.lemoyne.campusapp.ShowcaseService.API_KEY
import edu.lemoyne.campusapp.ShowcaseService.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class ShowcaseRepository(private val api: ShowcaseApi = ShowcaseService.api) {
    suspend fun loadItems(token: String): List<ShowcaseItem> =
    withContext(Dispatchers.IO) {
        api.getShowcaseItems(
        ShowcaseService.API_KEY,
        "Bearer $token"
        )
    }
suspend fun signIn(email:String,password: String): String =
    withContext(Dispatchers.IO) {
        api.signIn(
            ShowcaseService.API_KEY,
            LoginRequest(email, password)
        ).accessToken
    }
}





