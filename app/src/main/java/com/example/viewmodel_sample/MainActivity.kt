package com.example.viewmodel_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var myNumberViewModel: MyNumberViewModel

    lateinit var binder : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        // 뷰 모델 제공자 생성 - 라이브 데이터에 접근 가능해짐
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        // currentValue라는 변수가 바뀔 때마다(observe) 함수가 실행되게 한다
        myNumberViewModel.currentValue.observe(this, Observer {
            binder.numberTextView.text = it.toString()
        })

        binder.plusButton.setOnClickListener {
            userInput(it as Button)
        }

        binder.minusButton.setOnClickListener {
            userInput(it as Button)
        }
    }
    private fun userInput(view : Button){
        val userInput = binder.userEditText.text.toString().toInt()
        when(view){
            binder.plusButton -> myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            binder.minusButton -> myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}