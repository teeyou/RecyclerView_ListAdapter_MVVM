package teeu.android.recyclerview_listadapter

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

class MainViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.d("MYTAG", "viewModel cleared!")
    }
//    var dataList = MutableLiveData<MutableList<MyData>>().apply {
//        value = getList()
//    }

    var dataList = MutableLiveData<MutableList<MyData>>()

    init {
        dataList.value = getList()
    }

    fun getList(): MutableList<MyData> =
        mutableListOf(
            MyData(1001, "title 1", "text 1 ..."),
            MyData(1002, "title 2", "text 2 ..."),
            MyData(1003, "title 3", "text 3 ..."),
            MyData(1004, "title 4", "text 4 ..."),
            MyData(1005, "title 5", "text 5 ..."),
            MyData(1006, "title 6", "text 6 ..."),
            MyData(1007, "title 7", "text 7 ..."),
            MyData(1008, "title 8", "text 8 ..."),
            MyData(1009, "title 9", "text 9 ..."),
            MyData(1010, "title 10", "text 10 ..."),
            MyData(1011, "title 11", "text 11 ..."),
            MyData(1012, "title 12", "text 12 ...")
        )

    fun reverseList() {
        dataList.value = dataList.value!!.asReversed()
        dataList.postValue(dataList.value)
    }

    fun showSnackBar(view : View, item : MyData) {
        Snackbar.make(view, "${item.title} touched!", Snackbar.LENGTH_SHORT).show()
    }
}


