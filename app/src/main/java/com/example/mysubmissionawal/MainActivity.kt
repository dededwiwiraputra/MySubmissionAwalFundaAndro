package com.example.mysubmissionawal

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysubmissionawal.adapter.UserListAdapter
import com.example.mysubmissionawal.databinding.ActivityMainBinding
import com.example.mysubmissionawal.detail.DetailUser
import com.example.mysubmissionawal.detail.favorite.FavoriteFactory
import com.example.mysubmissionawal.model.ItemsItem
import com.example.mysubmissionawal.model.MainViewModel
import com.example.mysubmissionawal.model.UserModel
import com.example.mysubmissionawal.theme.ThemeActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private  var _binding: ActivityMainBinding? = null
    private lateinit var mainViewModel: MainViewModel
    private lateinit var searchUser: MainViewModel
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Github User"

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        mainViewModel = ViewModelProvider(
            this,
            FavoriteFactory.getInstance(this.application)
        )[MainViewModel::class.java]

        searchUser = ViewModelProvider(
            this,
            FavoriteFactory.getInstance(this.application)
        )[MainViewModel::class.java]


        mainViewModel.listUser.observe(this) { userDatas ->
            Log.d(TAG, "$userDatas")
            getUserData(userDatas)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun getUserData(userDatas: List<ItemsItem>) {
        val listUser = ArrayList<UserModel>()
        for (user in userDatas) {
            listUser.add(
                UserModel(
                    "Unknown Name",
                    user.id,
                    user.avatarUrl,
                    user.followersUrl,
                    user.followingUrl,
                    user.login
                )
            )
        }

        val adapter = UserListAdapter(listUser)
        binding.rvUser.adapter = adapter

        adapter.setOnItemClickCallback(object : UserListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserModel) {

                startActivity(
                        Intent(this@MainActivity, DetailUser::class.java).putExtra(
                            DetailUser.GET_USER,
                            data.login
                        )
                    )
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
                val searchView = item.actionView as SearchView

                searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

                searchView.queryHint = "Masukkan nama"

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {

                        if (query.isEmpty()) {
                            showLoading(true)
                        }

                        searchUser.setAllUser(query).observe(this@MainActivity) {
                            getUserData(it)
                        }

                        searchView.clearFocus()

                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        mainViewModel.listUser.observe(this@MainActivity) { userDatas ->
                            Log.d(TAG, "$userDatas")
                            getUserData(userDatas)
                        }
                        return false
                    }
                })
            }

            R.id.theme -> {
                startActivity(Intent(this, ThemeActivity::class.java))
            }

            R.id.favorites -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
