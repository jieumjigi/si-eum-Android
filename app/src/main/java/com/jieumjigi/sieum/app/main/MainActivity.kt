package com.jieumjigi.sieum.app.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jieumjigi.sieum.api.model.Poem
import com.jieumjigi.sieum.R
import com.jieumjigi.sieum.api.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PoetsAdapter.OnClickListener {

    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("poems")

        var poem : Poem = Poem(
                reservation_date = "2018-11-11",
                title = "혼자만의 방",
                content = "하루종일 시 쓰고\n그림만 그렸으면 좋겠어\n\n그러다보면 또\n당연한 하루가 지겨워질까\n\n아무도 나를\n찾지 않는 긴 \n겨울잠을 자고싶어\n\n그렇지만 한 번은\n누군가 방문을\n조심스레 두드려주길 기다릴까\n\n긴 슬픔에 눈물로 온 방이 가득찰 즈음 \n나는 살려달라는 외침을 겨우 \n들릴 듯 \n말 듯 \n하게 될까",
                abbrev = "그래도 생각나면 한 번쯤,\n닫힐 듯 말 듯한 내 방 문을 두드려 줄래요.",
                register_date = "2018-11-11",
                uid = "1558731524212950"
        )

        writeNewPoem(poem)
        initMainAdapter()


    }

    fun initMainAdapter(){
        rvPoet.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val poetsAdapter = PoetsAdapter(ArrayList())
        poetsAdapter.setListener(this)
        rvPoet.adapter = poetsAdapter
    }

    fun writeNewPoem (poem : Poem){
        databaseReference.push().setValue(poem)
    }

    override fun onItemClick(poet: User, position: Int) {
        TODO("When the poet adapter item clicked") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemDelete(poet: User) {
        TODO("When the poet adapter item deleted") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
