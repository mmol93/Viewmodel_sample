package com.example.viewmodel_sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
enum class ActionType{
    PLUS, MINUS
}
// 데이터 변경 관련
// 뷰모델은 데이터의 변경사항을 알려주는 라이브데이터를 가짐
class MyNumberViewModel : ViewModel() {
    // Mutable - 수정가능한 데이터
    // 일반 LiveData - 한번 지정하면 변경이 안되는 데이터

    // 내부에서 설정하는 자료형은 Mutable로 변경가능하게 설정
    private val _currentValue = MutableLiveData<Int>()

    // 변경되지 않는 데이터를 가져올 때 이름은 _ 없이 설정
    // 직접 liveData에 접근하는게 아니라 viewModel을 통해 가져오도록 설정
    val currentValue : LiveData<Int>
        get() = _currentValue

    // 초기값 설정
    init {
        // .value : liveData 안에 있는 데이터에 접속하는 방법임
        _currentValue.value = 0
    }

    fun updateValue(actionType : ActionType, input : Int){
        when(actionType){
            ActionType.PLUS -> {
                _currentValue.value = _currentValue.value?.plus(input)
            }
            ActionType.MINUS -> {
                _currentValue.value = _currentValue.value?.minus(input)
            }
        }
    }
}