package com.loongwind.ardf.base

import androidx.lifecycle.MutableLiveData
import com.loongwind.ardf.base.event.Event
import com.loongwind.ardf.base.event.ToastRes
import org.junit.Test

import org.junit.Assert.*
import java.lang.reflect.ParameterizedType

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testType(){


        val value =  Event<Int>(0)
        val cls = value::class
        val eventCls =  Event::class
        val type = value.get().javaClass


        value.let {

        }

        value.run {

        }

        println(type)
        println(value.get() is Int)
        println(type == Int::class)



    }

    @Test
    fun testSubscribe(){
        val test = TestClass()

        val map = test.javaClass.methods.filter { method ->
            method.getAnnotation(Subscribe::class.java) != null
        }.filter { method ->
            method.parameterTypes.size == 1
        }.associateBy { method ->
            method.parameterTypes[0]
        }
        println(map)

//        method.getAnnotation(Subscribe::class.java)?.let {
//                if(method.parameters.size == 1){
//                    val paramsType = method.parameters[0].type
//                    println("${method.name} -- $paramsType")
//                    if(paramsType == TestEvent::class.java){
//                        println("${method.name} -- $paramsType")
//                    }
//                }
//            }

//
//            if(method.getAnnotation(Subscribe::class.java) != null){
//                println(method.name)
//                println(method.parameters.size)
//                method.parameters.forEach {
//                    println(it.javaClass)
//                    println(it.type)
//                }
//            }

//            method.annotations.forEach {
//                println(method.name + "----->" + it)
//                println(method.name + "-----> isSubscribe : "+ (it == Subscribe()))
//            }
//        }
    }
}

class TestEvent
class TestClass{

    @Subscribe
    fun test(age : TestEvent){

    }

    fun test2(){

    }

    @Subscribe
    fun eq(obj : Any, a : String){

    }
    @Subscribe
    fun eq1(obj : String){

    }
}