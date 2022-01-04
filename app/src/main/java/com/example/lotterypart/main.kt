package com.example.lotterypart
import java.util.*
// 여긴 여러가지 코드들 테스팅 해보는 파일 입니다 앱하고 직접적으로 영향 주는 부분은 존재하지 않습니다

fun main() {


    //1부터 45까지 섞은 뒤 앞에 6개 출력 / List.shuffle(),sublist 활용
    val random = Random()
    val list = mutableListOf<Int>().apply{
        for(i in 1..45){
            this.add(i)
        }
    }
    list.shuffle()
    println(list.subList(0,6))
    // 좀 고급스럽게 보이는 방법 2 List 활용 스택 느낌으로 쌓는다
    //
    /*val random = Random()
    val numberSet = mutableSetOf<Int>()

    while(numberSet.size<6){
        val randomNumber = random.nextInt(45)+1
        numberSet.add(randomNumber)
    }
    println(numberSet)*/
    // 아주 간단하게 random 돌리는 방법 1
    /*for(i in 1..6)
    {
    	println("${random.nextInt(45)+1}")
    }*/
}