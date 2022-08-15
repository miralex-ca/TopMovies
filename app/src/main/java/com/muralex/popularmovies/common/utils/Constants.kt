package com.muralex.topmovies.common.utils

object Constants {
    const val ARTICLE_URL = "article_url"
    const val ARTICLE_EXTRA = "selected_article"
    const val HOME_LIST_SIZE = 50

    const val IMAGE_DIR = "https://image.tmdb.org/t/p/w500/"

    enum class DataErrors {CONNECTION, SERVER, REQUEST, GENERIC}
    const val DATA_FETCH_ERROR = "unknown_error"

    enum class Action {Click}

    enum class NaviAction {DISPLAY}

    const val LIST_SCREEN = "list/{action}"
    const val DETAIL_SCREEN = "detail/{detailId}"

    const val LIST_ARGUMENT_KEY = "action"
    const val DETAIL_ARGUMENT_KEY = "detailId"

}