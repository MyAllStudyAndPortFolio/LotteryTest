package com.example.lotterypart

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

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

    private val numberTextViewList: List<TextView> by lazy{
        listOf<TextView>(
            findViewById<TextView>(R.id.Ball1),

            findViewById<TextView>(R.id.Ball2),

            findViewById<TextView>(R.id.Ball3),

            findViewById<TextView>(R.id.Ball4),

            findViewById<TextView>(R.id.Ball5),

            findViewById<TextView>(R.id.Ball6),
        )
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
        initClearButton()
    }

    private fun initRunButton(){
        runButton.setOnClickListener {
            val list = getRandomNumber()
            didRun = true

            list.forEachIndexed{ index, number ->
                val textView = numberTextViewList[index]

                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number, textView)
            }

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
                return@setOnClickListener
            }
            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this, "이미 선택하신 번호 입니다 다시 입력해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible=true
            textView.text=numberPicker.value.toString()


            setNumberBackground(numberPicker.value, textView)

            pickNumberSet.add(numberPicker.value)
            
        }
        
    }

    private fun setNumberBackground(number:Int, textView: TextView){

        when(number){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this,R.drawable.ball1to10)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this,R.drawable.ball11to20)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this,R.drawable.ball21to30)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this,R.drawable.ball31to40)
            else -> textView.background = ContextCompat.getDrawable(this,R.drawable.ball41to50)
        }

    }
    private fun initClearButton(){
        clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTextViewList.forEach{
                it.isVisible=false

            }
            didRun=false
        }
    }

    private fun getRandomNumber() : List<Int>{
        val numberList = mutableListOf<Int>()
            .apply {
                for (i in 1..45){
                    if(pickNumberSet.contains(i)){
                        continue
                    }

                    this.add(i)
                }
            }

        numberList.shuffle()
        val newList = pickNumberSet.toList()+ numberList.subList(0,6-pickNumberSet.size)
        return newList.sorted()


    }
}