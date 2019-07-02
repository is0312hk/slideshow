package com.example.myslideshow

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val resources = listOf( //写真のリスト
        R.drawable.centralpark,
        R.drawable.golden,
        R.drawable.grand,
        R.drawable.hawaii,
        R.drawable.hollywood,
        R.drawable.lasvegas,
        R.drawable.bucks
    )

    private var position = 0 //どの画像を表示してるか記憶

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSwitcher.setFactory {
            ImageView(this)
        }
        imageSwitcher.setImageResource(resources[0])

        prevButton.setOnClickListener{movePosition(-1)}
        nextButton.setOnClickListener{movePosition(1)}

    }

    private fun movePosition(move: Int){
        position += move
        if(position >= resources.size){
            position = 0
        }else if(position < 0){
            position = resources.size - 1
        }
        imageSwitcher.setImageResource(resources[position])
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.main, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.airplane -> {
                val url: String = "https://www.skyscanner.jp/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }

            R.id.hotel -> {
                val url: String = "https://www.yahoo.co.jp/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }


        }
        return super.onContextItemSelected(item)
    }
}
