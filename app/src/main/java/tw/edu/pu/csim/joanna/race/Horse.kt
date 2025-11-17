package tw.edu.pu.csim.joanna.race

class Horse(n:Int) {
        var horseX = 0
        var horseY = 100 + 220 * n

        var numberNo = 0

        fun HorseRun(){
            numberNo ++
            if (numberNo>3) {
                numberNo = 0}
            horseX += (10..30).random()
        }
    }