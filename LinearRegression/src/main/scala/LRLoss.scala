import breeze.linalg.{DenseMatrix, DenseVector, sum}

/*
Class for computing loss functions (metrics) like MSE and RMSE
 */

object LRLoss {
  def MSELoss(y_target: DenseVector[Double], y_pred: DenseVector[Double]): Double = {
    var result = y_target - y_pred
    result = result.map(x => math.pow(x, 2))
    var loss = sum(result)
    loss /= y_target.length
    loss
  }

  def RMSELoss(y_target: DenseVector[Double], y_pred: DenseVector[Double]): Double = {
    var loss = MSELoss(y_target, y_pred)
    loss = math.sqrt(loss)
    loss
  }
}
