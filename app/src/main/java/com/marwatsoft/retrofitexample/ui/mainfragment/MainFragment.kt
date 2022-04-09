package com.marwatsoft.retrofitexample.ui.mainfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.marwatsoft.retrofitexample.R
import com.marwatsoft.retrofitexample.adapters.PostAdapter
import com.marwatsoft.retrofitexample.databinding.FragmentMainBinding
import com.marwatsoft.retrofitexample.helpers.GlobalHelper
import com.marwatsoft.retrofitexample.utils.ApiStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var binding:FragmentMainBinding
    lateinit var mContext:Context
    val mViewModel:MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContext = requireContext()
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mAdapter = PostAdapter()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
        lifecycleScope.launchWhenCreated {
            mViewModel.getPosts()
        }
        lifecycleScope.launchWhenCreated{
            mViewModel.mPosts.collect{
                when(it){
                    is ApiStatus.Empty -> {
                       binding.progressbar.visibility = View.VISIBLE
                    }
                    is ApiStatus.Error -> {
                        Log.e(GlobalHelper.TAG,"${it.error}")
                    }
                    is ApiStatus.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is ApiStatus.Success -> {
                        binding.progressbar.visibility = View.GONE
                        mAdapter.submitList(it.list)
                        Log.e(GlobalHelper.TAG,"Size ${it.list.size
                        }")
                    }
                }
            }
        }
    }
    object Math {
        fun addNumbers(num1:Int, num2:Int):Int = num1.plus(num2)
    }
}