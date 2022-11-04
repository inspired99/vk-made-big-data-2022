import breeze.linalg.{DenseMatrix, DenseVector, inv}

/*
Class that implements linear regression model using explicit formula
 */

class LRModel {
  private var weightsVector: DenseMatrix[Double] = _

  private def preprocessX(X: DenseMatrix[Double]): DenseMatrix[Double] = {
    val vectorOnes: DenseMatrix[Double] = DenseMatrix.ones(X.rows, 1)
    val tmpX: Array[Double] = vectorOnes.data ++ X.data
    val addedX = DenseMatrix.create(X.rows, X.cols + 1, tmpX)
    addedX
  }

  // W = (X^T X)^-1 X^T y
  def fit(X: DenseMatrix[Double], y: DenseMatrix[Double]): Unit = {
    val addedX = preprocessX(X)
    val inverseX = inv(addedX.t * addedX).toDenseMatrix
    weightsVector = (inverseX * addedX.t) * y.t
  }

  def predict(X: DenseMatrix[Double]): DenseMatrix[Double] = {
    val addedX = preprocessX(X)
    val result = addedX * weightsVector
    result
  }
}
