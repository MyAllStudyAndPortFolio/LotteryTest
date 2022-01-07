package com.example.lotterypart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val clearButton: Button by lazy{
        findViewById<Button>(R.id.clearButton)
    }

    private val addButton: Button by lazy{
        findViewById<Button>(R.id.addButton)
    }

    private val runButton: Button by lazy{
        findViewById<Button>(R.id.runButton)
    }

    private val numberPicker: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)
    }

    private var didRun = false

    private var pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
    }

    private fun initRunButton(){
        runButton.setOnClickListener {
            val list = getRandomNumber()

            Log.d("MainActivity",list.toString())
        }
    }
    private fun initAddButton(){
        addButton.setOnClickListener {
            if(didRun){
                Toast.makeText(this, "초기화 후 다시 시도 바랍니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.size>=5){
                Toast.makeText(this, "번호는 최대 5개 입니다.",Toast.LENGTH_SHORT).show()

            }
            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this, "이미 선택하신 번호 입니다 다시 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
            
        }
        
        
    }

    private fun getRandomNumber() : List<Int>{
        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45){
                    this.add(i)
                }
            }

        numberList.shuffle()
        val newList = numberList.subList(0,6)
        return newList.sorted()


    }
}