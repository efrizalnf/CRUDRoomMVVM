package id.zlz.crudroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import id.zlz.crudroom.databinding.ActivityEditBinding
import id.zlz.crudroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addNotes()
    }

    private fun addNotes() {
        binding.buttonCreate.setOnClickListener {
            val i = Intent(this, EditActivity::class.java)
            startActivity(i)
        }
    }
}