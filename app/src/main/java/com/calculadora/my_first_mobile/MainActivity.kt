package com.calculadora.my_first_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.calculadora.my_first_mobile.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.hide()

        numberZero.setOnClickListener{addExpression("0", true)}
        numberOne.setOnClickListener{addExpression("1", true)}
        numberTwo.setOnClickListener{addExpression("2", true)}
        numberThree.setOnClickListener{addExpression("3", true)}
        numberFour.setOnClickListener{addExpression("4", true)}
        numberFive.setOnClickListener{addExpression("5", true)}
        numberSix.setOnClickListener{addExpression("6", true)}
        numberSeven.setOnClickListener{addExpression("7", true)}
        numberEight.setOnClickListener{addExpression("8", true)}
        numberNine.setOnClickListener{addExpression("9", true)}
        comma.setOnClickListener{addExpression(".", false)}
        potentiation.setOnClickListener{addExpression("^", false)}
        plusButton.setOnClickListener{ addExpression("+", false)}
        minusButton.setOnClickListener{addExpression("-", false)}
        multiplication.setOnClickListener{addExpression("*", false)}
        division.setOnClickListener{addExpression("/", false)}
        openParentheses.setOnClickListener{addExpression("(", false)}
        closeParentheses.setOnClickListener{addExpression(")", false)}

        clean.setOnClickListener{
            expression.text=""
            result.text=""
        }

        backButton.setOnClickListener{
            val string = expression.text.toString()

            if(string.isNotBlank()){
                expression.text = string.substring(0,string.length-1)
            }

            result.text = ""
        }

        equal.setOnClickListener{
            try {

                val resultAux = ExpressionBuilder(expression.text.toString()).build().evaluate()
                val longResult = resultAux.toLong()

                if(resultAux == longResult.toDouble())
                    result.text = longResult.toString()
                else
                    result.text = resultAux.toString()


            } catch(e: ArithmeticException){
                val message = Snackbar.make(view,"Division by zero!",5000)
                result.text=""
                expression.text=""
                message.show()
            }
            catch(e:Exception){
                val message = Snackbar.make(view,"Error!",5000)
                result.text=""
                expression.text=""
                message.show()
            }
        }

    }





    private fun addExpression(string: String, cleanData: Boolean){
        if(result.text.isNotEmpty() ){
            result.text=""
        }

        if(cleanData){
            expression.append(string)
        }else{
            expression.append(result.text)
            expression.append(string)
            result.text=""
        }

    }
}

