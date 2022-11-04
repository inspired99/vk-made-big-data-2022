import breeze.linalg.DenseMatrix

import java.io.{BufferedWriter, File, FileWriter}

/*
Class for logging results to files and stdout
 */

object LRLogger {
  def logPredictToFile(fileToWrite: File, predict: DenseMatrix[Double]): Unit = {
    breeze.linalg.csvwrite(fileToWrite, predict, separator = ' ')
  }

  def logValidationScore(scoreMSE: Double, scoreRMSE: Double): Unit = {
    println("Validation score:")
    println("-----------------")
    println(f"MSE = $scoreMSE%2.4f")
    println(f"RMSE = $scoreRMSE%2.4f")
  }

  def logTestScoreToFile(fileToWrite: File, scoreMSE: Double, scoreRMSE: Double): Unit = {
    val bw = new BufferedWriter(new FileWriter(fileToWrite))
    var output = "Test Scores: \nMSE = "
    output += scoreMSE + "\nRMSE = " + scoreRMSE
    bw.write(output)
    bw.close()
  }
}
