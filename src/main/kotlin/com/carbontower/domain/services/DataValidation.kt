package com.carbontower.domain.services

class DataValidation {
    fun validPersonData(dataPerson: String) : Boolean {
        return cpfValid(dataPerson) || cnpjValid(dataPerson)
    }

    fun cpfValid(CPF: String) : Boolean {
        if (CPF.length != 11) {
            return false
        }

        var numbers: MutableList<Int> = mutableListOf()

        for (number in CPF){
            try {
                numbers.add(numbers.count(),
                    number.toString().toInt())
            }catch(e: NumberFormatException){
                return false
            }
        }

        var sumNumbers = 0

        var index = 10

        for (i in 0 until 9 step 1){
            sumNumbers += numbers[i] * index
            index--
        }

        var rest = 0

        sumNumbers *= 10
        rest = sumNumbers % 11

        if(rest == 10) rest = 0

        if(rest != numbers[9]) return false

        index = 11

        sumNumbers = 0

        for (i in 0 until 10 step 1){
            sumNumbers += numbers[i] * index
            index--
        }

        sumNumbers *= 10
        rest = sumNumbers % 11

        if(rest == 10) rest = 0

        if(rest != numbers[10]) return false

        return true
    }

    fun cnpjValid(CNPJ: String): Boolean{
        if (CNPJ.length != 14) return false

        var numbers: MutableList<Int> = mutableListOf()

        for (number in CNPJ) {
            try{
                numbers.add(numbers.count(), number.toString().toInt())
            }catch(e:NumberFormatException){
                return false
            }
        }

        val firstMult: List<Int> = listOf(5,4,3,2,9,8,7,6,5,4,3,2)

        var sumNumbers: Int = 0

        for(i in 0 until 12 step 1){
            sumNumbers += firstMult[i] * numbers[i]
        }

        var rest: Int = sumNumbers % 11

        var firstNumber: Int = 0

        if (rest < 2)
            firstNumber = 0
        else
            firstNumber = 11 - rest

        val secondMult: List<Int> = listOf(6,5,4,3,2,9,8,7,6,5,4,3,2)
        val secondList: List<Int> = listOf(numbers[0],numbers[1],numbers[2],numbers[3],numbers[4],numbers[5],numbers[6],numbers[7],numbers[8],numbers[9],numbers[10],numbers[11], firstNumber)

        sumNumbers = 0

        for (i in 0 until 13 step 1){
            sumNumbers += secondMult[i] * secondList[i]
        }

        rest = sumNumbers % 11

        var secondNumber: Int = 0

        if (rest < 2)
            secondNumber = 0
        else
            secondNumber = 11 - rest

        if (numbers[12] != firstNumber || numbers[13] != secondNumber) return false

        return true
    }
}