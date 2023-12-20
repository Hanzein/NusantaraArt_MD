package com.capstone.bangkit.NusArt.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.bangkit.NusArt.data.pref.UserModel
import com.capstone.bangkit.NusArt.data.pref.UserPreference
import com.capstone.bangkit.NusArt.data.remote.response.ListArtItem
import com.capstone.bangkit.NusArt.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class StoryPagingSource(private val userPreference: UserPreference) : PagingSource<Int, ListArtItem>() {


    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int,ListArtItem>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun getSession (): Flow<UserModel>{
        return userPreference.getSession()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListArtItem> {
        return try {
            val userModel = getSession().first()
            val token = userModel.token
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = ApiConfig.getApiService(token).getArts(position, params.loadSize)


            LoadResult.Page(
                data = responseData.data,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.data.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}