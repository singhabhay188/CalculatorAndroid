package com.example.mycalculatorfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var stfloat = Stack<Float>()
        var stchar = Stack<Char>()

        val infix = StringBuilder("")
        //buttons
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val sub: Button = findViewById(R.id.buttonsub)
        val add: Button = findViewById(R.id.buttonadd)
        val mul: Button = findViewById(R.id.buttonmul)
        val divide: Button = findViewById(R.id.buttondivide)
        val power: Button = findViewById(R.id.buttonpower)
        val equal:Button = findViewById(R.id.buttoneq)
        val bracketopen:Button = findViewById(R.id.buttonbopen)
        val bracketclose:Button = findViewById(R.id.buttonbclose)
        val del:Button = findViewById(R.id.buttondel)
        val clr:Button = findViewById(R.id.buttonclr)
        val output:TextView = findViewById(R.id.textView)

        equal.setOnClickListener{
            stfloat.clear();    stchar.clear()
            for(i in 0 until infix.length){
                var cchar:Char = infix[i]
                if(cchar>='0' && cchar<='9'){
                    stfloat.push((cchar-'0').toFloat())
                }
                else if(cchar==')'){
                    while(stchar.peek()!='('){
                        performOperation(stfloat,stchar)
                    }
                    stchar.pop()
                }
                else{
                    //^ / * + - (
                    while(!stchar.isEmpty() && stchar.peek()!='(' && priority(stchar.peek())>=priority(cchar)){
                        performOperation(stfloat,stchar)
                    }
                    stchar.push(cchar)
                }
            }

            while(!stchar.isEmpty())    performOperation(stfloat,stchar)

            //our final answer is at the top of the stack of float
            if(stfloat.peek().toInt().toFloat()==stfloat.peek()){
                output.text=stfloat.peek().toInt().toString()
            }
            else    output.text = stfloat.peek().toString()
        }
        bracketopen.setOnClickListener{
            infix.append('(')
            output.text = infix.toString()
        }
        bracketclose.setOnClickListener{
            infix.append(')')
            output.text = infix.toString()
        }
        clr.setOnClickListener{
            infix.clear()
            output.text=infix.toString()
        }
        del.setOnClickListener{
            if(infix.isNotEmpty()){
                infix.deleteCharAt(infix.length-1)
                output.text=infix.toString()
            }
        }
        button0.setOnClickListener{
            infix.append('0')
            output.text=infix.toString()
        }
        button0.setOnClickListener{
            infix.append('0')
            output.text=infix.toString()
        }
        button1.setOnClickListener{
            infix.append('1')
            output.text=infix.toString()
        }
        button2.setOnClickListener{
            infix.append('2')
            output.text=infix.toString()
        }
        button3.setOnClickListener{
            infix.append('3')
            output.text=infix.toString()
        }
        button4.setOnClickListener{
            infix.append('4')
            output.text=infix.toString()
        }
        button5.setOnClickListener{
            infix.append('5')
            output.text=infix.toString()
        }
        button6.setOnClickListener{
            infix.append('6')
            output.text=infix.toString()
        }
        button7.setOnClickListener{
            infix.append('7')
            output.text=infix.toString()
        }
        button8.setOnClickListener{
            infix.append('8')
            output.text=infix.toString()
        }
        button9.setOnClickListener{
            infix.append('9')
            output.text=infix.toString()
        }
        add.setOnClickListener{
            infix.append('+')
            output.text=infix.toString()
        }
        sub.setOnClickListener{
            infix.append('-')
            output.text=infix.toString()
        }
        mul.setOnClickListener{
            infix.append('x')
            output.text = infix.toString()
        }
        sub.setOnClickListener{
            infix.append('-')
            output.text = infix.toString()
        }
        mul.setOnClickListener{
            infix.append('x')
            output.text=infix.toString()
        }
        divide.setOnClickListener{
            infix.append('/')
            output.text=infix.toString()
        }
        power.setOnClickListener{
            infix.append('^')
            output.text=infix.toString()
        }


    }


    private fun performOperation(stfloat: Stack<Float>, stchar:Stack<Char>){
        var op2=stfloat.pop()
        var op1=stfloat.pop()
        var pop:Char = stchar.pop()
        when(pop){
            '^'->stfloat.push((Math.pow(op1.toDouble(), op2.toDouble())).toFloat())
            '/'->stfloat.push(op1/op2)
            'x'->stfloat.push(op1*op2)
            '+'->stfloat.push(op1+op2)
            '-'->stfloat.push(op1-op2)
        }
    }

    private fun priority(peek: Char): Int{
        when (peek) {
            '(' -> return 3
            '^' -> return 2
            '/', 'x' -> return 1
            else -> return 0
        }
    }
}