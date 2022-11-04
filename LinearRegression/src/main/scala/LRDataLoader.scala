import breeze.linalg._
import java.io._

/*
Class for loading dataset and creating train/test data for regression problem
 */

class LRDataLoader(pathToData: File, trainSize: Double, valSize: Double) {
  private var data: DenseMatrix[Double] = _
  private var indices: List[Int] = _

  checkSize()
  loadDataFromFile()
  trainTestSplit()

  private def checkSize(): Unit = {
    if (trainSize + valSize >= 1) {
      throw new IllegalArgumentException("Wrong size of train/test split")
    }
  }

  private def loadDataFromFile(): Unit = {
    data = csvread(pathToData, ',', skipLines = 1)
  }

  // get random split indexes for train and test
  private def trainTestSplit(): Unit = {
    val seed = 42
    val randomGenerator = new scala.util.Random(seed)
    indices = randomGenerator.shuffle(Range(0, data.rows).toList)
  }

  def getY(dataSet: DenseMatrix[Double]): DenseMatrix[Double] = {
    val dataY = dataSet(::, 0)
    dataY.toDenseMatrix
  }

  def getX(dataSet: DenseMatrix[Double]): DenseMatrix[Double] = {
    val dataX = dataSet.delete(0, Axis._1)
    dataX
  }

  def getTrainDataSet: DenseMatrix[Double] = {
    val trainLength = (trainSize * data.rows).toInt
    val trainIndices = indices.slice(0, trainLength)
    val trainData: DenseMatrix[Double] = data(trainIndices, ::).toDenseMatrix
    trainData
  }

  def getValDataSet: DenseMatrix[Double] = {
    val trainLength = (trainSize * data.rows).toInt
    val valAndTrainLength = ((valSize + trainSize) * data.rows).toInt
    val valIndices = indices.slice(trainLength, valAndTrainLength)
    val valData: DenseMatrix[Double] = data(valIndices, ::).toDenseMatrix
    valData
  }

  def getTestDataSet: DenseMatrix[Double] = {
    val valAndTrainLength = ((valSize + trainSize) * data.rows).toInt
    val testIndices = indices.slice(valAndTrainLength, indices.length)
    val testData: DenseMatrix[Double] = data(testIndices, ::).toDenseMatrix
    testData
  }
}
