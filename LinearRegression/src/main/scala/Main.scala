import breeze.linalg.DenseMatrix

import java.io.File

object Main {
  def main(args: Array[String]): Unit = {
    val pathToDataset: String = "src/data/song_data.csv"
    val pathToPredictions: String = "src/data/results/pred_y.txt"
    val pathToTestScore: String = "src/data/results/test.txt"
    val dataFile: File = new File(pathToDataset)

    val dataLoader: LRDataLoader = new LRDataLoader(dataFile, trainSize = 0.7, valSize = 0.1)
    val trainData: DenseMatrix[Double] = dataLoader.getTrainDataSet
    val valData: DenseMatrix[Double] = dataLoader.getValDataSet
    val testData: DenseMatrix[Double] = dataLoader.getTestDataSet

    val linearRegressionModel: LRModel = new LRModel()

    linearRegressionModel.fit(dataLoader.getX(trainData), dataLoader.getY(trainData))

    val y_pred_validation: DenseMatrix[Double] = linearRegressionModel.predict(dataLoader.getX(valData))
    val scoreMSEVal: Double = LRLoss.MSELoss(y_pred_validation.toDenseVector, dataLoader.getY(valData).toDenseVector)
    val scoreRMSEVal: Double = LRLoss.RMSELoss(y_pred_validation.toDenseVector, dataLoader.getY(valData).toDenseVector)
    LRLogger.logValidationScore(scoreMSEVal, scoreRMSEVal)

    val y_pred_test: DenseMatrix[Double] = linearRegressionModel.predict(dataLoader.getX(testData))
    LRLogger.logPredictToFile(new File(pathToPredictions), y_pred_test)
    LRLogger.logTestScoreToFile(new File(pathToTestScore),
      LRLoss.MSELoss(y_pred_test.toDenseVector, dataLoader.getY(testData).toDenseVector),
      LRLoss.RMSELoss(y_pred_test.toDenseVector, dataLoader.getY(testData).toDenseVector))
  }
}
