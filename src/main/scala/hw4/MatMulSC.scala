package hw4

import chisel3._
import chisel3.internal.firrtl.Width
import chisel3.util._


case class MatMulParams(m: Int, k: Int, n: Int, parallelism: Int = 1, cyclesPerTransfer: Int = 1) {
  // A (m x k) X B (k x n) = C (m x n)
  val aRows: Int = m
  val aCols: Int = k
  val bRows: Int = k
  val bCols: Int = n
  val cRows: Int = m
  val cCols: Int = n
  // Implementation details
  val w: Width = 32.W
  // Only relevant for MatMulMC (multi-cycle transfer)
  require((aRows * aCols) % cyclesPerTransfer == 0)
  val aElementsPerTransfer: Int = (aRows * aCols) / cyclesPerTransfer
  if (cyclesPerTransfer != 1) {
    require(aElementsPerTransfer <= aCols)
    require(aCols % aElementsPerTransfer == 0)
  }
  require((bRows * bCols) % cyclesPerTransfer == 0)
  val bElementsPerTransfer: Int = (bRows * bCols) / cyclesPerTransfer
  if (cyclesPerTransfer != 1) {
    require(bElementsPerTransfer <= bCols)
    require(bCols % bElementsPerTransfer == 0)
  }
  if ((cRows * cCols) > cyclesPerTransfer)
    require((cRows * cCols) % cyclesPerTransfer == 0)
  val cElementsPerTransfer: Int = ((cRows * cCols) / cyclesPerTransfer).max(1)
  if (cyclesPerTransfer != 1) {
    require(cElementsPerTransfer <= cCols)
    require(cCols % cElementsPerTransfer == 0)
  }
  require(cCols >= parallelism)
  require(cCols % parallelism == 0)
}


class MatMulIO(p: MatMulParams) extends Bundle {
  val in = Flipped(Decoupled(new Bundle {
    val a = Vec(p.aRows, Vec(p.aCols, SInt(p.w)))
    val b = Vec(p.bRows, Vec(p.bCols, SInt(p.w)))
  }))
  val out = Valid(Vec(p.cRows, Vec(p.cCols, SInt(p.w))))
}


class MatMulSC(p: MatMulParams) extends Module {
  require(p.cyclesPerTransfer == 1)
  require(p.cCols >= p.parallelism)
  require(p.cCols % p.parallelism == 0)
  val io = IO(new MatMulIO(p))
  // BEGIN SOLUTION
  ???
}
