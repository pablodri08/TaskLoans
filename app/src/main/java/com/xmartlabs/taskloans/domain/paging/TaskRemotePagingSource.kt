package com.xmartlabs.taskloans.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.xmartlabs.taskloans.data.model.UserEntry
import com.xmartlabs.taskloans.domain.repository.SessionRepository
import com.xmartlabs.taskloans.domain.repository.TaskRepository

class TaskRemotePagingSource(
    private val taskRepository: TaskRepository,
    private val sessionRepository: SessionRepository,
) :
    PagingSource<Int, UserEntry>() {

  companion object {
    const val DEFAULT_INIT_KEY = 1
    const val PAGE_SIZE = 10
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserEntry> {
    val page = params.key ?: DEFAULT_INIT_KEY
    val user = sessionRepository.getSessionUser()
    val data = mutableListOf<UserEntry>()
    val response = taskRepository.getTaskEntries(user.id, page)
    response.data?.map {
      data.add(UserEntry(user.id, it))
    }
    return LoadResult.Page(
        data = data,
        prevKey = null,
        nextKey = if (response.meta.totalPages > page) page + 1 else null
    )
  }

  override fun getRefreshKey(state: PagingState<Int, UserEntry>): Int? {
    return state.anchorPosition?.let {
      state.closestPageToPosition(it)?.prevKey?.plus(DEFAULT_INIT_KEY)
          ?: state.closestPageToPosition(it)?.nextKey?.minus(DEFAULT_INIT_KEY)
    }
  }
}
